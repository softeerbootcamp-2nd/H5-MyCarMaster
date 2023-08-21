import { styled } from "styled-components";
import GraphList from "../../../common/Graph/GraphList";
import {
  useDetailDispatch,
  useDetailState,
} from "../../../../contexts/DetailContext";
import { Engines } from "../../../../types/detail.types";
import { useTrimState } from "../../../../contexts/TrimContext";
import {
  useQuotationDispatch,
  useQuotationState,
} from "../../../../contexts/QuotationContext";
import useFetch from "../../../../hooks/useFetch";
import { useEffect } from "react";

interface FetchEngineProps extends Engines {
  result: {
    engines: Engines[];
  };
}

function EngineView() {
  const SERVER_URL = import.meta.env.VITE_APP_SERVER_URL;

  const { trimId } = useTrimState();
  const { engineId, engineList } = useDetailState();
  const { detailQuotation } = useQuotationState();
  const engineDispatch = useDetailDispatch();
  const quotationDispatch = useQuotationDispatch();

  const { data } = useFetch<FetchEngineProps>(
    `${SERVER_URL}/engines/?trimId=${trimId}`,
    { method: "GET" }
  );

  useEffect(() => {
    if (data) {
      if (detailQuotation.engineQuotation.id) return;

      engineDispatch({
        type: "SET_DETAIL_LIST",
        payload: { engineList: data.result.engines },
      });

      engineDispatch({
        type: "SELECT_DETAIL",
        payload: { engineId: data.result.engines[0].id },
      });

      quotationDispatch({
        type: "SET_DETAIL_QUOTATION",
        payload: {
          type: "engineQuotation",
          id: data.result.engines[0].id,
          name: data.result.engines[0].name,
          price: data.result.engines[0].price,
        },
      });
    }
  }, [data]);

  if (!engineList?.length) return <Container></Container>;

  return (
    <Container>
      {engineList?.length && (
        <EngineImg
          src={engineList.find((engine) => engine.id === engineId)?.imgUrl}
          alt="엔진 이미지"
        />
      )}
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
