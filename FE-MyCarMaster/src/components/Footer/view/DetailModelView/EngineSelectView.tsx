import { Fragment, useState } from "react";
import {
  useDetailState,
  useDetailDispatch,
} from "../../../../contexts/DetailContext";
import {
  useQuotationDispatch,
  useQuotationState,
} from "../../../../contexts/QuotationContext";
import OptionBox from "../../../common/OptionBox/OptionBox";
import { Modals } from "../../../common/Modals/Modals";
import { ModalType } from "../../../../constants/Modal.constants";
import useFetch from "../../../../hooks/useFetch";
// import { useTrimState } from "../../../../contexts/TrimContext";
// import { useOptionState } from "../../../../contexts/OptionContext";
import { UnselectableOptionProps } from "../../../../types/options.types";

interface FetchUnselectableOptionProps extends UnselectableOptionProps {
  result: {
    unselectableOptions: UnselectableOptionProps[];
  };
}

export default function BodyTypeSelectView() {
  const SERVER_URL = import.meta.env.VITE_APP_SERVER_URL;

  // 주석 친 부분은 option 파트도 api 연결되면 변경
  // const { trimId } = useTrimState();
  const { engineList, wheelDriveId, bodyTypeId, engineId } = useDetailState();
  const { detailQuotation } = useQuotationState();
  // const { selectedOption } = useOptionState();

  const [isOpen, setIsOpen] = useState(false);
  const [reselectId, setReselectId] = useState(1);
  const [unselectableOption, setUnselectableOption] =
    useState<UnselectableOptionProps[]>();

  // const optionIds = selectedOption.join(",");

  // const {data} = useFetch<FetchUnselectableOptionProps>(`${SERVER_URL}/engines/${engineId}/unselectable-options?trimId=${trimId}?optionIds=${optionIds}`);

  const { data } = useFetch<FetchUnselectableOptionProps>(
    `${SERVER_URL}/engines/1/unselectable-options?trimId=1&optionIds=96`
  );

  const validateOptionHandler = (id: number) => {
    const unselectableLength = Array.isArray(data?.result.unselectableOptions)
      ? data?.result.unselectableOptions.length
      : 0;
    if (unselectableLength) {
      checkUnselectableOption(
        data?.result.unselectableOptions as UnselectableOptionProps[]
      );
      setReselectId(id);
    } else {
      selectEngine(id);
    }
  };

  const detailDispatch = useDetailDispatch();
  const quotationDispatch = useQuotationDispatch();

  const selectEngine = (id: number) => {
    quotationDispatch({
      type: "SET_DETAIL_QUOTATION",
      payload: {
        type: "engineQuotation",
        name: engineList[id - 1].name,
        price: engineList[id - 1].price,
      },
    });
    detailDispatch({
      type: "SELECT_DETAIL",
      payload: {
        engineId: id,
        wheelDriveId,
        bodyTypeId,
      },
    });
  };

  const checkUnselectableOption = (unselectableOption: object) => {
    setIsOpen(true);
    setUnselectableOption(unselectableOption as UnselectableOptionProps[]);
  };

  const reselectEngine = (id: number) => {
    // 해당 옵션 취소 로직 추가 필요
    selectEngine(id);
    setIsOpen(false);
  };

  return (
    <Fragment>
      {engineList?.length &&
        engineList.map((engine) => {
          return (
            <OptionBox
              key={engine.id}
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
                  selectEngine(engine.id);
                } else validateOptionHandler(engine.id);
              }}
            />
          );
        })}
      {isOpen && (
        <Modals
          type={ModalType.CHANGE_ENGINE}
          onClick={() => reselectEngine(reselectId)}
          setIsOpen={setIsOpen}
          unselectableOption={unselectableOption}
        />
      )}
    </Fragment>
  );
}
