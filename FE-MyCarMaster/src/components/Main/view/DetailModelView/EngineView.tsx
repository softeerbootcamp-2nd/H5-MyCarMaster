import { styled } from "styled-components";
import diesel from "../../../../assets/images/diesel.png";
import GraphList from "../../../common/Graph/GraphList";

function EngineView() {
  return (
    <Container>
      <EngineImg src={diesel} />
      <EngineGraph>
        <GraphList />
      </EngineGraph>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 2rem;
  height: 100%;
`;

const EngineImg = styled.img`
  flex: 1;
  width: calc(100% / 2);
  height: 100%;
  object-fit: contain;
  object-position: center;
`;

const EngineGraph = styled.div`
  flex: 1;
`;

export default EngineView;
