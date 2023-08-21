import styled from "styled-components";
import {
  useCarPaintDispatch,
  useCarPaintState,
} from "../../../../contexts/CarPaintContext";
import {
  useQuotationDispatch,
  useQuotationState,
} from "../../../../contexts/QuotationContext";
import { ExteriorColors } from "../../../../types/carpaint.types";
import { useTrimState } from "../../../../contexts/TrimContext";
import useFetch from "../../../../hooks/useFetch";
import { useEffect } from "react";
// import CarRotation from "../../../common/CarRotation/CarRotation";

interface FetchExteriorProps extends ExteriorColors {
  result: {
    exteriorColors: ExteriorColors[];
  };
}

function ExteriorColorView() {
  const SERVER_URL = import.meta.env.VITE_APP_SERVER_URL;

  const { trimId } = useTrimState();
  const { exteriorList, exteriorId } = useCarPaintState();
  const { carPaintQuotation } = useQuotationState();
  const exteriorDispatch = useCarPaintDispatch();
  const quotationDispatch = useQuotationDispatch();

  const { data } = useFetch<FetchExteriorProps>(
    `${SERVER_URL}/exterior-colors/?trimId=${trimId}`,
    { method: "GET" }
  );

  useEffect(() => {
    if (data) {
      if (carPaintQuotation.exteriorColorQuotation.id) return;

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
          imgUrl: data.result.exteriorColors[0].colorImgUrl,
        },
      });
    }
  }, [data]);

  if (!exteriorList?.length) return null;

  return (
    exteriorList?.length && (
      <ExteriorColorImg
        src={
          exteriorList.find((exterior) => exterior.id === exteriorId)
            ?.coloredImgUrl
        }
      />
      // <CarRotation $isQuotation={false} />
    )
  );
}

const ExteriorColorImg = styled.img`
  width: 100%;
  max-width: 50rem;
  margin: 0 auto;
  height: 100%;

  object-fit: scale-down;
  object-position: center;
`;

export default ExteriorColorView;
