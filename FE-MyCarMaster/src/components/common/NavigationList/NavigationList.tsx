import { styled } from "styled-components";
import NavigationItem from "./NavigationItem";
import EstimatedPrice from "./EstimatedPrice";
import { useQuotationState } from "../../../contexts/QuotationContext";
import useFadeAnimation, {
  FadeAnimationType,
} from "../../../hooks/useFadeAnimation";
import { useEffect, useState } from "react";
import { ConfirmType } from "../../../types/quotation.types";

function NavigationList({ confirm }: ConfirmType) {
  const quotationState = useQuotationState();
  const { pathname } = window.location;
  const [isAnimation, setIsAnimation] = useState(false);
  const appearAnimation = useFadeAnimation(1, 1000);

  useEffect(() => {
    setIsAnimation(true);
    return () => {
      setIsAnimation(false);
    };
  }, [pathname]);

  return (
    <Container $style={appearAnimation} $isStart={isAnimation}>
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

const Container = styled.ul<{ $style: FadeAnimationType; $isStart: boolean }>`
  display: flex;
  flex-direction: column;
  gap: 0.25rem;

  ${(props) =>
    props.$isStart
      ? props.$style
      : "opacity: 0; transition: opacity 1s ease-in-out;"}
  will-change: opacity;
`;

export default NavigationList;
