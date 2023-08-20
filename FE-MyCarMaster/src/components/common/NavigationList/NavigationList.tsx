import { styled } from "styled-components";
import NavigationItem from "./NavigationItem";
import EstimatedPrice from "./EstimatedPrice";
import { useQuotationState } from "../../../contexts/QuotationContext";
import { ConfirmType } from "../../../types/quotation.types";

function NavigationList({ confirm }: ConfirmType) {
  const quotationState = useQuotationState();
  const { pathname } = window.location;

  return (
    <Container>
      <NavigationItem
        name={"트림"}
        quotation={quotationState.trimQuotation}
        confirm={confirm}
      />
      <NavigationItem
        name={"세부모델"}
        quotation={quotationState.detailQuotation}
        confirm={confirm}
      />
      <NavigationItem
        name={"색상"}
        quotation={quotationState.carPaintQuotation}
        confirm={confirm}
      />
      <NavigationItem
        name={"옵션"}
        quotation={quotationState.optionQuotation}
        confirm={confirm}
      />
      <NavigationItem name={"견적서 완성"} confirm={confirm} />
      {pathname === "/estimation" && <EstimatedPrice />}
    </Container>
  );
}

const Container = styled.ul`
  display: flex;
  flex-direction: column;
  gap: 0.25rem;
`;

export default NavigationList;
