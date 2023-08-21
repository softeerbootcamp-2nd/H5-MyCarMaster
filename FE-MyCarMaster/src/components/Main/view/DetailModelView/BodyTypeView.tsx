import { styled } from "styled-components";
import {
  useDetailDispatch,
  useDetailState,
} from "../../../../contexts/DetailContext";
import { BodyTypes } from "../../../../types/detail.types";
import {
  useQuotationDispatch,
  useQuotationState,
} from "../../../../contexts/QuotationContext";
import { useModelState } from "../../../../contexts/ModelContext";
import useFetch from "../../../../hooks/useFetch";
import { useEffect } from "react";

interface FetchBodyTypeProps extends BodyTypes {
  result: {
    bodyTypes: BodyTypes[];
  };
}

function BodyTypeView() {
  const SERVER_URL = import.meta.env.VITE_APP_SERVER_URL;

  const { modelId } = useModelState();
  const { bodyTypeId, bodyTypeList } = useDetailState();
  const bodyTypeDispatch = useDetailDispatch();
  const quotationDispatch = useQuotationDispatch();
  const { detailQuotation } = useQuotationState();

  const { data } = useFetch<FetchBodyTypeProps>(
    `${SERVER_URL}/body-types/?modelId=${modelId}`,
    { method: "GET" }
  );

  useEffect(() => {
    if (data) {
      if (detailQuotation.bodyTypeQuotation.id) return;

      bodyTypeDispatch({
        type: "SET_DETAIL_LIST",
        payload: { bodyTypeList: data.result.bodyTypes },
      });

      bodyTypeDispatch({
        type: "SELECT_DETAIL",
        payload: { bodyTypeId: data.result.bodyTypes[0].id },
      });

      quotationDispatch({
        type: "SET_DETAIL_QUOTATION",
        payload: {
          type: "bodyTypeQuotation",
          id: data.result.bodyTypes[0].id,
          name: data.result.bodyTypes[0].name,
          price: data.result.bodyTypes[0].price,
        },
      });
    }
  }, [data]);

  if (!bodyTypeList?.length) return null;

  return (
    bodyTypeList?.length && (
      <BodyTypeImg
        src={bodyTypeList.find((item) => item.id === bodyTypeId)?.imgUrl}
      />
    )
  );
}

const BodyTypeImg = styled.img`
  width: 100%;
  max-width: 40rem;
  margin: 0 auto;
  height: 100%;

  object-fit: scale-down;
  object-position: center;
`;

export default BodyTypeView;
