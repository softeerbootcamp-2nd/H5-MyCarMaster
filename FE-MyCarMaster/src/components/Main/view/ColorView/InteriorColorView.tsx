import { styled } from "styled-components";
import {
  useCarPaintDispatch,
  useCarPaintState,
} from "../../../../contexts/CarPaintContext";
import { InteriorColors } from "../../../../types/carpaint.types";
import useFetch from "../../../../hooks/useFetch";
import { useTrimState } from "../../../../contexts/TrimContext";
import { useEffect } from "react";

interface FetchInteriorProps extends InteriorColors {
  result: {
    interiorColors: InteriorColors[];
  };
}

function InteriorColorView() {
  const SERVER_URL = import.meta.env.VITE_APP_SERVER_URL;

  const { trimId } = useTrimState();
  const { exteriorId, interiorId, interiorList } = useCarPaintState();
  const interiorDispatch = useCarPaintDispatch();

  const { data } = useFetch<FetchInteriorProps>(
    `${SERVER_URL}/interior-colors/?trimId=${trimId}&exteriorColorId=${exteriorId}`
  );

  useEffect(() => {
    if (data) {
      interiorDispatch({
        type: "SET_INTERIOR_LIST",
        payload: { interiorList: data.result.interiorColors },
      });
    }
  }, [data, interiorDispatch]);

  return (
    interiorList?.length && (
      <InteriorColorImg src={interiorList[interiorId].coloredImgUrl} />
    )
  );
}

const InteriorColorImg = styled.img`
  width: 100%;
  height: 100%;
  object-fit: contain;
  object-position: center;
`;

export default InteriorColorView;
