import { Fragment, useState } from "react";
import styled from "styled-components";
import { useTrimState, useTrimDispatch } from "../../../contexts/TrimContext";
import {
  useQuotationDispatch,
  useQuotationState,
} from "../../../contexts/QuotationContext";
import OptionBox from "../../common/OptionBox/OptionBox";
import OptionList from "../../common/OptionList/OptionList";
import { Modals } from "../../common/Modals/Modals";
import { ModalType } from "../../../constants/Modal.constants";

export default function TrimSelect() {
  const { trimList, trimId } = useTrimState();
  const { trimQuotation } = useQuotationState();
  const [showDetail, setShowDetail] = useState(false);
  const [isOpen, setIsOpen] = useState(false);

  const [reselectId, setReselectId] = useState<number>(1);

  const trimDispatch = useTrimDispatch();
  const quotationDispatch = useQuotationDispatch();

  const selectTrim = (id: number) => {
    quotationDispatch({
      type: "SET_TRIM_QUOTATION",
      payload: {
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
                handleClick={() => {
                  if (
                    trimQuotation.trimQuotation.name !== "" &&
                    trimQuotation.trimQuotation.name !== trim.name
                  ) {
                    setIsOpen(true);
                    setReselectId(trim.id);
                  } else {
                    selectTrim(trim.id);
                  }
                }}
                handleClickDetail={() => setShowDetail(!showDetail)}
              />
            );
          })}
        <Exam>
          {showDetail && (
            <>
              <OptionList $name="파워트레인/성능" />
              <OptionList $name="지능형 안전 기술" />
              <OptionList $name="안전" />
              <OptionList $name="외관" />
              <OptionList $name="내장" />
              <OptionList $name="시트" />
              <OptionList $name="편의" />
            </>
          )}
        </Exam>
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

const Exam = styled.div`
  width: 100%;
  align-self: center;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
`;
