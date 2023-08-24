import { styled } from "styled-components";
import theme from "../../../styles/Theme";
import { useQuotationState } from "../../../contexts/QuotationContext";
import { QuotationState, QuotationType } from "@/types/quotation.types";

function EstimatedPrice() {
  const {
    trimQuotation,
    detailQuotation,
    carPaintQuotation,
    optionQuotation,
  }: QuotationState = useQuotationState();

  const sum: number =
    trimQuotation?.trimQuotation.price +
    detailQuotation?.engineQuotation.price +
    detailQuotation?.wheelDriveQuotation.price +
    detailQuotation?.bodyTypeQuotation.price +
    carPaintQuotation?.exteriorColorQuotation.price +
    carPaintQuotation?.interiorColorQuotation.price +
    optionQuotation?.selectedQuotation.reduce(
      (acc: number, cur: QuotationType) => acc + cur.price,
      0
    );

  return (
    <Container>
      <Text>예상 가격</Text>
      <Price>{sum.toLocaleString("ko-KR")}원</Price>
    </Container>
  );
}

const Container = styled.li`
  width: 12rem;
  padding: 0.75rem;
  gap: 0.5rem;

  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: space-between;

  color: ${theme.colors.BLACK};
  border: 1px solid ${theme.colors.GREY2};
`;

const Text = styled.p`
  ${(props) => props.theme.fonts.Medium12};
`;

const Price = styled.p`
  ${(props) => props.theme.fonts.Medium15};
`;

export default EstimatedPrice;
