import { styled } from "styled-components";
import {
  useCarPaintDispatch,
  useCarPaintState,
} from "../../../../contexts/CarPaintContext";
import { useQuotationDispatch } from "../../../../contexts/QuotationContext";
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
  const quotationDispatch = useQuotationDispatch();

  const { data } = useFetch<FetchInteriorProps>(
    `${SERVER_URL}/interior-colors/?trimId=${trimId}&exteriorColorId=${exteriorId}`
  );

  useEffect(() => {
    if (data) {
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
          name: data.result.interiorColors[0].name,
          price: data.result.interiorColors[0].price,
          imgUrl: data.result.interiorColors[0].coloredImgUrl,
        },
      });
    }
  }, [data, interiorDispatch]);

  if (!interiorList?.length) return null;

  return (
    <>
      {interiorList?.length && (
        <InteriorColorImg
          src={
            interiorList.find((interior) => interior.id === interiorId)
              ?.coloredImgUrl
          }
        />
      )}
    </>
  );
}

const InteriorColorImg = styled.img`
  width: 100%;
  max-width: 50rem;
  margin: 0 auto;
  height: 100%;

  object-fit: scale-down;
  object-position: center;
`;

export default InteriorColorView;
