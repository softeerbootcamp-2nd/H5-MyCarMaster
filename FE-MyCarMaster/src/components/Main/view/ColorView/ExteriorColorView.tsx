import {
  useCarPaintDispatch,
  useCarPaintState,
} from "../../../../contexts/CarPaintContext";
import { useQuotationDispatch } from "../../../../contexts/QuotationContext";
import { ExteriorColors } from "../../../../types/carpaint.types";
import { useTrimState } from "../../../../contexts/TrimContext";
import useFetch from "../../../../hooks/useFetch";
import { useEffect } from "react";
import CarRotation from "../../../common/CarRotation/CarRotation";

interface FetchExteriorProps extends ExteriorColors {
  result: {
    exteriorColors: ExteriorColors[];
  };
}

function ExteriorColorView() {
  const SERVER_URL = import.meta.env.VITE_APP_SERVER_URL;

  const { trimId } = useTrimState();
  const { exteriorList } = useCarPaintState();
  const exteriorDispatch = useCarPaintDispatch();
  const quotationDispatch = useQuotationDispatch();

  const { data } = useFetch<FetchExteriorProps>(
    `${SERVER_URL}/exterior-colors/?trimId=${trimId}`,
    { method: "GET" }
  );

  useEffect(() => {
    if (data) {
      exteriorDispatch({
        type: "SET_EXTERIOR_LIST",
        payload: { exteriorList: data.result.exteriorColors },
      });

      quotationDispatch({
        type: "SET_CAR_PAINT_QUOTATION",
        payload: {
          type: "exteriorColorQuotation",
          name: data.result.exteriorColors[0].name,
          price: data.result.exteriorColors[0].price,
          imgUrl: data.result.exteriorColors[0].colorImgUrl,
        },
      });
    }
  }, [data, exteriorDispatch]);

  if (!exteriorList?.length) return null;

  // 360도 회전에 사용될 이미지 배열을 <CarRotation />로 넘겨줘서 구현

  return (
    exteriorList?.length && (
      // <ExteriorColorImg src={exteriorList[exteriorId - 1].coloredImgUrl} />
      <CarRotation $isQuotation={false} />
    )
  );
}

export default ExteriorColorView;
