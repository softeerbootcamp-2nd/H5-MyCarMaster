import styled from "styled-components";
import { useQuotationState } from "../../../contexts/QuotationContext";
import ExteriorColorSelectView from "./ColorView/ExteriorColorSelectView";
import InteriorColorSelectView from "./ColorView/InteriorColorSelectView";

export default function TrimSelect() {
  const { navigationId } = useQuotationState();
  return (
    <>
      {navigationId === 4 && (
        <Container>
          <ExteriorColorSelectView />
        </Container>
      )}

      {navigationId === 5 && (
        <Container>
          <InteriorColorSelectView />
        </Container>
      )}
    </>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: row;
  width: 59.5rem;
  gap: 0.5rem;
  flex-wrap: wrap;
`;
