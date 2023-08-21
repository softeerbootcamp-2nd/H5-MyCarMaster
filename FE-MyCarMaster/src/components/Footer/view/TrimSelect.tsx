import React, { Fragment, useState } from "react";
import styled from "styled-components";
import { useTrimState, useTrimDispatch } from "../../../contexts/TrimContext";
import {
  useQuotationDispatch,
  useQuotationState,
} from "../../../contexts/QuotationContext";
import OptionBox from "../../common/OptionBox/OptionBox";
import { Modals } from "../../common/Modals/Modals";
import { ModalType } from "../../../constants/Modal.constants";
import BasicOptionModal from "../../common/BasicOptionModal/BasicOptionModal";
import { Trims } from "../../../types/trim.types";

export default function TrimSelect() {
  const { trimList, trimId } = useTrimState();
  const { isFirst, optionQuotation } = useQuotationState();
  const [isBasicOptionModalOpen, setIsBasicOptionModalOpen] = useState(false);
  const [detailTrim, setDetailTrim] = useState<Trims>();
  const [isOpen, setIsOpen] = useState(false);

  const [reselectId, setReselectId] = useState<number>(1);

  const trimDispatch = useTrimDispatch();
  const quotationDispatch = useQuotationDispatch();

  const selectTrim = (id: number) => {
    quotationDispatch({
      type: "SET_TRIM_QUOTATION",
      payload: {
        id: id,
        name: trimList[id - 1].name,
        price: trimList[id - 1].price,
      },
    });
    trimDispatch({
      type: "SELECT_TRIM",
      payload: {
        trimId: id,
      },
    });
  };

  const handleTrimVaildation = (e: React.MouseEvent, id: number) => {
    e.stopPropagation();

    if (
      !isFirst[1] ||
      optionQuotation.selectedQuotation.length ||
      optionQuotation.consideredQuotation.length
    ) {
      setIsOpen(true);
      setReselectId(id);
      return;
    }
    selectTrim(id);
  };

  const handleShowDetail = (e: React.MouseEvent, trim: Trims) => {
    e.stopPropagation();
    setDetailTrim(trim);
    setIsBasicOptionModalOpen(true);
  };

  if (!trimList.length) return <Container>데이터가 없습니다.</Container>;
  const reselectTrim = (id: number) => {
    quotationDispatch({ type: "RESET_QUOTATION" });
    selectTrim(id);
    setIsOpen(false);
  };

  return (
    <Fragment>
      <Container>
        {trimList?.length &&
          trimList.map((trim) => {
            return (
              <OptionBox
                key={trim.id}
                $id={trim.id}
                $name={trim.name}
                $description={trim.description}
                $ratio={trim.ratio}
                $price={trim.price}
                $switch="trim"
                $choice={trimId === trim.id}
                handleClick={(e: React.MouseEvent) =>
                  handleTrimVaildation(e as React.MouseEvent, trim.id)
                }
                handleClickDetail={(e: React.MouseEvent) =>
                  handleShowDetail(e as React.MouseEvent, trim)
                }
              />
            );
          })}
        {isBasicOptionModalOpen && (
          <BasicOptionModal
            trimId={detailTrim!.id}
            trimName={detailTrim!.name}
            trimDescription={detailTrim!.description}
            setIsBasicOptionModalOpen={setIsBasicOptionModalOpen}
          />
        )}
      </Container>
      {isOpen && (
        <Modals
          type={ModalType.CHANGE_TRIM}
          onClick={() => reselectTrim(reselectId)}
          setIsOpen={setIsOpen}
        />
      )}
    </Fragment>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: row;
  width: 59.5rem;
  gap: 0.5rem;
`;
