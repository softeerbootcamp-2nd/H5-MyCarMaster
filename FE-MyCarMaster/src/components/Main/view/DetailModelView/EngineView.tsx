import { styled } from "styled-components";
import GraphList from "../../../common/Graph/GraphList";
import { useDetailState } from "../../../../contexts/DetailContext";

function EngineView() {
  const { engineId, engineList } = useDetailState();

  return (
    <Container>
      <EngineImg src={engineList[engineId].imgUrl} />
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
  height: 25rem;
  object-fit: contain;
  object-position: center;
`;

const EngineGraph = styled.div`
  flex: 1;
`;

export default EngineView;
