import { Fragment, useState } from "react";
import { useDetailState, useDetailDispatch } from "@contexts/DetailContext";
import {
  useQuotationDispatch,
  useQuotationState,
} from "@contexts/QuotationContext";
import { useOptionState } from "@contexts/OptionContext";
import { useTrimState } from "@contexts/TrimContext";
import { OptionBox, Modals } from "@common/index";
import { ModalType } from "@constants/Modal.constants";
import { UnselectableOptionProps } from "types/options.types";
import { get } from "@utils/fetch";

export default function BodyTypeSelectView() {
  const SERVER_URL = import.meta.env.VITE_APP_SERVER_URL;

  const { engineList, engineId } = useDetailState();
  const { detailQuotation } = useQuotationState();
  const { selectedOption } = useOptionState();
  const { trimId } = useTrimState();

  const [isOpen, setIsOpen] = useState(false);
  const [reselectId, setReselectId] = useState<{ id: number; index: number }>({
    id: 0,
    index: 0,
  });

  const [unselectableOption, setUnselectableOption] =
    useState<UnselectableOptionProps[]>();

  const validateOptionHandler = (id: number, index: number) => {
    if (!selectedOption.length) return selectEngine(id, index);

    const optionIds = selectedOption.map((option) => option).join(",");

    get(
      `${SERVER_URL}/engines/${id}/unselectable-options?trimId=${trimId}&optionIds=${optionIds}`
    ).then((data) => {
      const unselectableLength = Array.isArray(data?.result.unselectableOptions)
        ? data?.result.unselectableOptions.length
        : 0;
      if (unselectableLength) {
        checkUnselectableOption(
          data?.result.unselectableOptions as UnselectableOptionProps[]
        );
        setReselectId({ id, index });
      } else {
        selectEngine(id, index);
      }
    });
  };

  const detailDispatch = useDetailDispatch();
  const quotationDispatch = useQuotationDispatch();

  const selectEngine = (id: number, index: number) => {
    quotationDispatch({
      type: "SET_DETAIL_QUOTATION",
      payload: {
        type: "engineQuotation",
        id: id,
        name: engineList[index].name,
        price: engineList[index].price,
      },
    });
    detailDispatch({
      type: "SELECT_DETAIL",
      payload: {
        engineId: id,
      },
    });
  };

  if (!engineList?.length) return null;

  const checkUnselectableOption = (unselectableOption: object) => {
    setIsOpen(true);
    setUnselectableOption(unselectableOption as UnselectableOptionProps[]);
  };

  const reselectEngine = (id: number, index: number) => {
    const unselectableOptionIds = unselectableOption?.map(
      (option) => option.id
    );

    quotationDispatch({
      type: "REMOVE_EXCEPT_SELECTED",
      payload: {
        ids: unselectableOptionIds,
      },
    });
    selectEngine(id, index);
    setIsOpen(false);
  };

  return (
    <Fragment>
      {engineList?.length &&
        engineList.map((engine, index) => {
          return (
            <OptionBox
              key={index}
              $id={engine.id}
              $name={engine.name}
              $description={engine.description}
              $ratio={engine.ratio}
              $price={engine.price}
              $switch="detail"
              $choice={engine.id === engineId}
              handleClick={() => {
                if (
                  detailQuotation.engineQuotation.name === "" ||
                  detailQuotation.engineQuotation.name === engine.name
                ) {
                  selectEngine(engine.id, index);
                } else validateOptionHandler(engine.id, index);
              }}
            />
          );
        })}
      {isOpen && (
        <Modals
          type={ModalType.CHANGE_ENGINE}
          onClick={() => reselectEngine(reselectId.id, reselectId.index)}
          setIsOpen={setIsOpen}
          unselectableOption={unselectableOption}
        />
      )}
    </Fragment>
  );
}
