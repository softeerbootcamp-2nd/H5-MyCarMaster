import {
  useCarPaintDispatch,
  useCarPaintState,
} from "../../../../contexts/CarPaintContext";
import { ExteriorColors } from "../../../../types/carpaint.types";
import { useTrimState } from "../../../../contexts/TrimContext";
import useFetch from "../../../../hooks/useFetch";
import { useEffect } from "react";
import CarRotation from "../../../common/CarRotation/CarRotation";

interface FetchExteriorProps extends ExteriorColors {
  result: {
    colors: ExteriorColors[];
  };
}

function ExteriorColorView() {
  const SERVER_URL = import.meta.env.VITE_APP_SERVER_URL;

  const { trimId } = useTrimState();
  const { exteriorList } = useCarPaintState();
  const exteriorDispatch = useCarPaintDispatch();

  const { data } = useFetch<FetchExteriorProps>(
    `${SERVER_URL}/exterior-colors/?trimId=${trimId}`,
    {
      method: "GET",
    }
  );

  useEffect(() => {
    if (data) {
      exteriorDispatch({
        type: "SET_EXTERIOR_LIST",
        payload: { exteriorList: data.result.colors },
      });
    }
  }, [data, exteriorDispatch]);

  // 360도 회전에 사용될 이미지 배열을 <CarRotation />로 넘겨줘서 구현

  return (
    exteriorList?.length && (
      // <ExteriorColorImg src={exteriorList[exteriorId - 1].coloredImgUrl} />
      <CarRotation $isQuotation={false} />
    )
  );
}

export default ExteriorColorView;
