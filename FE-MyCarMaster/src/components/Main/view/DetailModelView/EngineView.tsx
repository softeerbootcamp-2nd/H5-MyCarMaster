import { styled } from "styled-components";
import GraphList from "../../../common/Graph/GraphList";
import {
  Engines,
  useDetailDispatch,
  useDetailState,
} from "../../../../contexts/DetailContext";
import { useTrimState } from "../../../../contexts/TrimContext";
import useFetch from "../../../../hooks/useFetch";
import { useEffect } from "react";

function EngineView() {
  const SERVER_URL = import.meta.env.VITE_APP_SERVER_URL;

  const { trimId } = useTrimState();
  const { engineId, engineList } = useDetailState();
  const engineDispatch = useDetailDispatch();

  const { data } = useFetch<Engines[]>(
    `${SERVER_URL}/engines/?trimId=${trimId}`,
    { method: "GET" }
  );

  useEffect(() => {
    if (data) {
      engineDispatch({
        type: "SET_DETAIL_LIST",
        payload: { engineList: data.result.engines },
      });
    }
  }, [data, engineDispatch]);

  return (
    <Container>
      {engineList.length && <EngineImg src={engineList[engineId - 1].imgUrl} />}
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
