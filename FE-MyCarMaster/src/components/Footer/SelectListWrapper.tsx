import styled from "styled-components";
import TrimContent from "./view/TrimSelect";
import DetailModelSelect from "./view/DetailModelSelect";
import OptionSelect from "./view/OptionSelect";
import ColorSelect from "./view/ColorSelect";
import { useQuotationState } from "../../contexts/QuotationContext";

export default function SelectListWrapper() {
  const { navigationId } = useQuotationState();
  if (navigationId === 0) return <TrimContent />;
  if (navigationId >= 1 && navigationId <= 3) return <DetailModelSelect />;
  if (navigationId >= 4 && navigationId <= 5) return <ColorSelect />;
  if (navigationId === 6) return <OptionSelect />;
  return <Container>완성</Container>;
}

// 임시 컨테이너 => 완성 페이지로 이동
const Container = styled.div`
  display: flex;
  flex-direction: row;
  width: 51.5rem;
  gap: 0.5rem;
  flex-wrap: wrap;
`;
