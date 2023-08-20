import { useQuotationState } from "../../../contexts/QuotationContext";
import { styled } from "styled-components";
import QuotationItem from "./QuotationItem";
import QuotationOptionList from "./QuotationOptionList";
import { ConfirmType } from "../../../types/quotation.types";

function QuotationList({ confirm }: ConfirmType) {
  const { trimQuotation, detailQuotation, carPaintQuotation } =
    useQuotationState();

  return (
    <QuotationSummary>
      <QuotationText>최종 견적</QuotationText>
      <QuotationItem
        category={"트림"}
        name={trimQuotation.trimQuotation.name}
        price={trimQuotation.trimQuotation?.price}
        id={0}
        confirm={confirm}
      />
      <QuotationItem
        category={"엔진"}
        name={detailQuotation.engineQuotation.name}
        price={detailQuotation.engineQuotation.price}
        id={1}
        confirm={confirm}
      />
      <QuotationItem
        category={"구동 방식"}
        name={detailQuotation.wheelDriveQuotation.name}
        price={detailQuotation.wheelDriveQuotation.price}
        id={2}
        confirm={confirm}
      />
      <QuotationItem
        category={"바디 타입"}
        name={detailQuotation.bodyTypeQuotation.name}
        price={detailQuotation.bodyTypeQuotation.price}
        id={3}
        confirm={confirm}
      />
      <QuotationItem
        category={"외장 색상"}
        name={carPaintQuotation.exteriorColorQuotation.name}
        price={carPaintQuotation.exteriorColorQuotation.price}
        imgUrl={carPaintQuotation.exteriorColorQuotation.imgUrl}
        id={4}
        confirm={confirm}
      />
      <QuotationItem
        category={"내장 색상"}
        name={carPaintQuotation.interiorColorQuotation.name}
        price={carPaintQuotation.interiorColorQuotation.price}
        imgUrl={carPaintQuotation.interiorColorQuotation.imgUrl}
        id={5}
        confirm={confirm}
      />
      <QuotationOptionList confirm={confirm} />
    </QuotationSummary>
  );
}

export default QuotationList;

const QuotationSummary = styled.div`
  width: 80%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 3rem;

  margin-top: 2rem;
`;

const QuotationText = styled.p`
  width: 59.5rem;
  font-family: "HyundaiSansRegular";
  font-size: 2.25rem;
  font-style: normal;
  font-weight: 700;
  line-height: 1.75rem; /* 77.778% */
`;
