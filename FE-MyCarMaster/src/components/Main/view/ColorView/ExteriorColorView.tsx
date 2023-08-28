import { useState, useEffect } from "react";
import { Flex } from "@styles/core.style";
import {
  useCarPaintDispatch,
  useCarPaintState,
} from "@contexts/CarPaintContext";
import {
  useQuotationDispatch,
  useQuotationState,
} from "@contexts/QuotationContext";
import { useTrimState } from "@contexts/TrimContext";
import { ExteriorColors } from "types/carpaint.types";
import useFetch from "@hooks/useFetch";
import { SpriteCarRotation, Loader } from "@common/index";

interface FetchExteriorProps extends ExteriorColors {
  result: {
    exteriorColors: ExteriorColors[];
  };
}

export default function ExteriorColorView() {
  const SERVER_URL = import.meta.env.VITE_APP_SERVER_URL;

  const { trimId } = useTrimState();
  const { exteriorList, exteriorId } = useCarPaintState();
  const { carPaintQuotation } = useQuotationState();
  const [loading, setLoading] = useState(true);
  const exteriorDispatch = useCarPaintDispatch();
  const quotationDispatch = useQuotationDispatch();

  const { data } = useFetch<FetchExteriorProps>(
    `${SERVER_URL}/exterior-colors/?trimId=${trimId}`,
    { method: "GET" }
  );

  useEffect(() => {
    setLoading(true);
    if (data) {
      if (carPaintQuotation.exteriorColorQuotation.id) return;

      const option = "mid"; // low, mid, high

      data.result.exteriorColors.forEach((exterior) => {
        exterior.coloredImgUrl = `${exterior.coloredImgUrl}${option}/sprite.png`;
      });

      exteriorDispatch({
        type: "SET_EXTERIOR_LIST",
        payload: { exteriorList: data.result.exteriorColors },
      });

      exteriorDispatch({
        type: "SELECT_EXTERIOR",
        payload: { exteriorId: data.result.exteriorColors[0].id },
      });

      quotationDispatch({
        type: "SET_CAR_PAINT_QUOTATION",
        payload: {
          type: "exteriorColorQuotation",
          id: data.result.exteriorColors[0].id,
          name: data.result.exteriorColors[0].name,
          price: data.result.exteriorColors[0].price,
          imgUrl: `${data.result.exteriorColors[0].colorImgUrl}`,
          coloredImgUrl: `${data.result.exteriorColors[0].coloredImgUrl}`,
        },
      });
    }
  }, [data, carPaintQuotation.exteriorColorQuotation.id]);

  const handleSpriteLoad = () => {
    console.log("handleSpriteLoad");
    setLoading(false);
  };

  if (!exteriorList?.length) return null;
  return (
    <Flex $position="relative">
      {exteriorList?.length && (
        <>
          {loading && <Loader />}
          <SpriteCarRotation
            onLoad={handleSpriteLoad}
            $imgUrl={
              exteriorList.find((exterior) => exterior.id === exteriorId)
                ?.coloredImgUrl
            }
          />
        </>
      )}
    </Flex>
  );
}
