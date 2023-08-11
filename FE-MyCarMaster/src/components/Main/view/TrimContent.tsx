import styled from "styled-components";
import {
  Trims,
  useTrimDispatch,
  useTrimState,
} from "../../../contexts/TrimContext";
import useFetch from "../../../hooks/useFetch";
import { useModelState } from "../../../contexts/ModelContext";
import { useEffect } from "react";

interface FetchTrimsProps extends Trims {
  result: {
    trims: Trims[];
  };
}

function TrimContent() {
  const SERVER_URL = import.meta.env.VITE_APP_SERVER_URL;

  const { modelId } = useModelState();
  const { trimId, trimList } = useTrimState();
  const trimDispatch = useTrimDispatch();

  const { data } = useFetch<FetchTrimsProps>(
    `${SERVER_URL}/trims/?modelId=${modelId}`,
    {
      method: "GET",
    }
  );

  useEffect(() => {
    if (data) {
      trimDispatch({
        type: "SET_TRIM_LIST",
        payload: { trimList: data.result.trims },
      });
    }
  }, [data, trimDispatch]);

  return trimList?.length && <TrimImage src={trimList[trimId - 1].imgUrl} />;
}

const TrimImage = styled.img`
  width: 100%;
  height: 100%;
  object-fit: contain;
  object-position: center;
`;

export default TrimContent;
