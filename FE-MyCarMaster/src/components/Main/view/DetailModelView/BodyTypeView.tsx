import { styled } from "styled-components";
import {
  BodyTypes,
  useDetailDispatch,
  useDetailState,
} from "../../../../contexts/DetailContext";
import { useModelState } from "../../../../contexts/ModelContext";
import useFetch from "../../../../hooks/useFetch";
import { useEffect } from "react";

function BodyTypeView() {
  const SERVER_URL = import.meta.env.VITE_APP_SERVER_URL;

  const { modelId } = useModelState();
  const { bodyTypeId, bodyTypeList } = useDetailState();
  const bodyTypeDispatch = useDetailDispatch();

  const { data } = useFetch<BodyTypes[]>(
    `${SERVER_URL}/body-types/?modelId=${modelId}`,
    {
      method: "GET",
    }
  );

  useEffect(() => {
    if (data) {
      bodyTypeDispatch({
        type: "SET_DETAIL_LIST",
        payload: { bodyTypeList: data.result.bodyTypes },
      });
    }
  }, [data, bodyTypeDispatch]);

  return (
    bodyTypeList && <BodyTypeImg src={bodyTypeList[bodyTypeId - 1].imgUrl} />
  );
}

const BodyTypeImg = styled.img`
  width: 100%;
  height: 100%;
  object-fit: scale-down;
  object-position: center;
`;

export default BodyTypeView;
