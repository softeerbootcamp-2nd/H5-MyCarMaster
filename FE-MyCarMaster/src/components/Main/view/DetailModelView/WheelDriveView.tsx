import { styled } from "styled-components";
import {
  useDetailDispatch,
  useDetailState,
} from "../../../../contexts/DetailContext";
import { WheelDrives } from "../../../../types/detail.types";
import { useTrimState } from "../../../../contexts/TrimContext";
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

  const wheelDriveDispatch = useDetailDispatch();

  const { data } = useFetch<FetchWheelDriveProps>(
    `${SERVER_URL}/wheel-drives/?trimId=${trimId}&engineId=${engineId}`,
    {
      method: "GET",
    }
  );

  useEffect(() => {
    if (data) {
      wheelDriveDispatch({
        type: "SET_DETAIL_LIST",
        payload: { wheelDriveList: data.result.wheelDrives },
      });
    }
  }, [data, wheelDriveDispatch]);

  return (
    wheelDriveList?.length && (
      <WheelDriveImg src={wheelDriveList[wheelDriveId - 1].imgUrl} />
    )
  );
}

const WheelDriveImg = styled.img`
  width: 100%;
  height: 100%;
  object-fit: contain;
  object-position: center;
`;

export default WheelDriveView;
