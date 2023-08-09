import styled from "styled-components";
import { useQuotationState } from "../../../contexts/QuotationContext";
import BodyTypeSelectView from "./DetailModelView/BodyTypeSelectView";
import EngineSelectView from "./DetailModelView/EngineSelectView";
import WheelDriveSelectView from "./DetailModelView/WheelDriveSelectView";

export default function DetailModelSelect() {
  const { navigationId } = useQuotationState();

  return (
    <Container>
      {navigationId === 1 && <EngineSelectView />}
      {navigationId === 2 && <WheelDriveSelectView />}
      {navigationId === 3 && <BodyTypeSelectView />}
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: row;
  width: 51.5rem;
  gap: 0.5rem;
`;