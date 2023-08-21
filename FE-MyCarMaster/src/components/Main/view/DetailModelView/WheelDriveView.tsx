import { styled } from "styled-components";
import {
  useDetailDispatch,
  useDetailState,
} from "../../../../contexts/DetailContext";
import { WheelDrives } from "../../../../types/detail.types";
import { useTrimState } from "../../../../contexts/TrimContext";
import {
  useQuotationDispatch,
  useQuotationState,
} from "../../../../contexts/QuotationContext";
import useFetch from "../../../../hooks/useFetch";
import { useEffect } from "react";

interface FetchWheelDriveProps extends WheelDrives {
  result: {
    wheelDrives: WheelDrives[];
  };
}

function WheelDriveView() {
  const SERVER_URL = import.meta.env.VITE_APP_SERVER_URL;

  const { trimId } = useTrimState();
  const { engineId, wheelDriveId, wheelDriveList } = useDetailState();
  const { detailQuotation } = useQuotationState();

  const wheelDriveDispatch = useDetailDispatch();
  const quotationDispatch = useQuotationDispatch();

  const { data } = useFetch<FetchWheelDriveProps>(
    `${SERVER_URL}/wheel-drives/?trimId=${trimId}&engineId=${engineId}`,
    { method: "GET" }
  );

  useEffect(() => {
    if (data) {
      if (detailQuotation.wheelDriveQuotation.id) return;
      wheelDriveDispatch({
        type: "SET_DETAIL_LIST",
        payload: { wheelDriveList: data.result.wheelDrives },
      });

      wheelDriveDispatch({
        type: "SELECT_DETAIL",
        payload: { wheelDriveId: data.result.wheelDrives[0].id },
      });

      quotationDispatch({
        type: "SET_DETAIL_QUOTATION",
        payload: {
          type: "wheelDriveQuotation",
          id: data.result.wheelDrives[0].id,
          name: data.result.wheelDrives[0].name,
          price: data.result.wheelDrives[0].price,
        },
      });
    }
  }, [data]);

  if (!wheelDriveList?.length) return null;

  return (
    wheelDriveList?.length && (
      <WheelDriveImg
        src={
          wheelDriveList.find((wheelDrive) => wheelDrive.id === wheelDriveId)
            ?.imgUrl
        }
      />
    )
  );
}

const WheelDriveImg = styled.img`
  width: 100%;
  max-width: 40rem;
  height: 100%;
  margin: 0 auto;
  object-fit: contain;
  object-position: center;
`;

export default WheelDriveView;
