import React from "react";
import { useQuotationState } from "../../../contexts/QuotationContext";
import { styled } from "styled-components";
import QuotationItem from "./QuotationItem";
import QuotationOptionList from "./QuotationOptionList";

function QuotationList() {
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
      />
      <QuotationItem
        category={"엔진"}
        name={detailQuotation.engineQuotation.name}
        price={detailQuotation.engineQuotation.price}
        id={1}
      />
      <QuotationItem
        category={"구동 방식"}
        name={detailQuotation.wheelDriveQuotation.name}
        price={detailQuotation.wheelDriveQuotation.price}
        id={2}
      />
      <QuotationItem
        category={"바디 타입"}
        name={detailQuotation.bodyTypeQuotation.name}
        price={detailQuotation.bodyTypeQuotation.price}
        id={3}
      />
      <QuotationItem
        category={"외장 색상"}
        name={carPaintQuotation.exteriorColorQuotation.name}
        price={carPaintQuotation.exteriorColorQuotation.price}
        imgUrl={carPaintQuotation.exteriorColorQuotation.imgUrl}
        id={4}
      />
      <QuotationItem
        category={"내장 색상"}
        name={carPaintQuotation.interiorColorQuotation.name}
        price={carPaintQuotation.interiorColorQuotation.price}
        imgUrl={carPaintQuotation.interiorColorQuotation.imgUrl}
        id={5}
      />
      <QuotationOptionList />
    </QuotationSummary>
  );
}

export default QuotationList;

const QuotationSummary = styled.div`
  width: 51.5rem;
  display: flex;
  flex-direction: column;
  gap: 3rem;

  margin-top: 2rem;
`;

const QuotationText = styled.p`
  font-family: "HyundaiSansRegular";
  font-size: 2.25rem;
  font-style: normal;
  font-weight: 700;
  line-height: 1.75rem; /* 77.778% */
`;
