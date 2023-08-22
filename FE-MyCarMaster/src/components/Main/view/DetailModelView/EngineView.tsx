import { useEffect } from "react";
import { Flex, Image } from "@styles/core.style";
import { GraphList } from "@common/index";
import { useDetailDispatch, useDetailState } from "@contexts/DetailContext";
import {
  useQuotationDispatch,
  useQuotationState,
} from "@contexts/QuotationContext";
import { Engines } from "types/detail.types";
import { useTrimState } from "@contexts/TrimContext";
import useFetch from "@hooks/useFetch";

interface FetchEngineProps extends Engines {
  result: {
    engines: Engines[];
  };
}

export default function EngineView() {
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

  if (!engineList?.length) return <Flex></Flex>;

  return (
    <Flex $justifyContent="space-between" $gap="5rem" $alignItems="center">
      {engineList?.length && (
        <Image
          $width="50%"
          $height="20rem"
          $objectFit="contain"
          $shadow={"0 0 10px #000"}
          src={engineList.find((engine) => engine.id === engineId)?.imgUrl}
          alt="엔진 이미지"
        />
      )}
      <GraphList />
    </Flex>
  );
}
