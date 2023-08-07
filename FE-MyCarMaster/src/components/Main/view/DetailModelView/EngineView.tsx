import { styled } from "styled-components";
import diesel from "../../../../assets/images/diesel.png";
import GraphList from "../../../common/Graph/GraphList";

function EngineView() {
  return (
    <Container>
      <EngineContainer>
        <EngineImg src={diesel} />
        <EngineGraph>
          <GraphList />
        </EngineGraph>
      </EngineContainer>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  height: 100%;
`;

const EngineContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
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
