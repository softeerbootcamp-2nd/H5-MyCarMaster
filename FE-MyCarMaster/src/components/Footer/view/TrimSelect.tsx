import { useState } from "react";
import styled from "styled-components";
import { useTrimState, useTrimDispatch } from "../../../contexts/TrimContext";
import { useQuotationDispatch } from "../../../contexts/QuotationContext";
import OptionBox from "../../common/OptionBox/OptionBox";
import OptionList from "../../common/OptionList/OptionList";

export default function TrimSelect() {
  const { trimList, trimId } = useTrimState();
  const [showDetail, setShowDetail] = useState(false);
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

  if (!trimList.length) return <Container>데이터가 없습니다.</Container>;

  return (
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
              handleClick={() => selectTrim(trim.id)}
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
