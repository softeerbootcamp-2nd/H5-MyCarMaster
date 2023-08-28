import { useEffect } from "react";
import { Flex, Image } from "@styles/core.style";
import {
  useCarPaintDispatch,
  useCarPaintState,
} from "@contexts/CarPaintContext";
import {
  useQuotationDispatch,
  useQuotationState,
} from "@contexts/QuotationContext";
import { useTrimState } from "@contexts/TrimContext";
import { InteriorColors } from "types/carpaint.types";
import useFetch from "@hooks/useFetch";

interface FetchInteriorProps extends InteriorColors {
  result: {
    interiorColors: InteriorColors[];
  };
}

export default function InteriorColorView() {
  const SERVER_URL = import.meta.env.VITE_APP_SERVER_URL;

  const { trimId } = useTrimState();
  const { exteriorId, interiorId, interiorList } = useCarPaintState();
  const interiorDispatch = useCarPaintDispatch();
  const quotationDispatch = useQuotationDispatch();
  const { carPaintQuotation } = useQuotationState();

  const { data } = useFetch<FetchInteriorProps>(
    `${SERVER_URL}/interior-colors/?trimId=${trimId}&exteriorColorId=${exteriorId}`
  );

  useEffect(() => {
    if (data) {
      if (carPaintQuotation.interiorColorQuotation.id) return;

      interiorDispatch({
        type: "SET_INTERIOR_LIST",
        payload: { interiorList: data.result.interiorColors },
      });

      interiorDispatch({
        type: "SELECT_INTERIOR",
        payload: { interiorId: data.result.interiorColors[0].id },
      });

      quotationDispatch({
        type: "SET_CAR_PAINT_QUOTATION",
        payload: {
          type: "interiorColorQuotation",
          id: data.result.interiorColors[0].id,
          name: data.result.interiorColors[0].name,
          price: data.result.interiorColors[0].price,
          imgUrl: data.result.interiorColors[0].colorImgUrl,
        },
      });
    }
  }, [data]);

  if (!interiorList?.length) return null;

  return (
    <Flex>
      {interiorList?.length && (
        <Image
          $width="100%"
          $height="25rem"
          $padding="1rem"
          $objectFit="cover"
          src={
            interiorList.find((interior) => interior.id === interiorId)
              ?.coloredImgUrl
          }
        />
      )}
    </Flex>
  );
}
