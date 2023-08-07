import { styled } from "styled-components";
import NavigationItem from "./NavigationItem";
import EstimatedPrice from "./EstimatedPrice";
import { useQuotationState } from "../../../contexts/QuotationContext";

function NavigationList() {
  const quotationState = useQuotationState();

  return (
    <Container>
      <NavigationItem name={"트림"} quotation={quotationState.trimQuotation} />
      <NavigationItem
        name={"세부모델"}
        quotation={quotationState.detailQuotation}
      />
      <NavigationItem
        name={"색상"}
        quotation={quotationState.carPaintQuotation}
      />
      <NavigationItem
        name={"옵션"}
        quotation={quotationState.optionQuotation}
      />
      <NavigationItem name={"견적서 완성"} />
      <EstimatedPrice sumPrice={9999999} />
    </Container>
  );
}

const Container = styled.ul`
  display: flex;
  flex-direction: column;
  gap: 0.25rem;
`;

export default NavigationList;
