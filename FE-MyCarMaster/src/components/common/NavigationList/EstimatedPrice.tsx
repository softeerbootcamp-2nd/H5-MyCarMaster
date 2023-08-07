import { styled } from "styled-components";
import theme from "../../../styles/Theme";
import { useQuotationState } from "../../../contexts/QuotationContext";

function EstimatedPrice() {
  const {
    trimQuotation,
    detailQuotation,
    carPaintQuotation,
    optionQuotation,
  }: any = useQuotationState();

  const sum: number =
    trimQuotation?.trimQuotation.price +
    detailQuotation?.engineQuotation.price +
    detailQuotation?.wheelDriveQuotation.price +
    detailQuotation?.bodyTypeQuotation.price +
    carPaintQuotation?.exteriorColorQuotation.price +
    carPaintQuotation?.interiorColorQuotation.price +
    optionQuotation?.selectedQuotation.reduce(
      (acc: number, cur: any) => acc + cur.price,
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
  width: 9.625rem;
  height: 4.5rem;
  padding: 0.75rem;

  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: space-between;

  color: ${theme.colors.Black};
  border: 1px solid ${theme.colors.Grey2};
`;

const Text = styled.p`
  font-size: 0.75rem;
  font-style: normal;
  font-weight: 500;
`;

const Price = styled.p`
  font-size: 1.25rem;
  font-style: normal;
  font-weight: 700;
`;

export default EstimatedPrice;
