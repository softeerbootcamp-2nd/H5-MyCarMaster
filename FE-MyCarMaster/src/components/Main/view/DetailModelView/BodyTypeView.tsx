import { useEffect } from "react";
import { Flex, Image } from "@styles/core.style";
import { useDetailDispatch, useDetailState } from "@contexts/DetailContext";
import {
  useQuotationDispatch,
  useQuotationState,
} from "@contexts/QuotationContext";
import { useModelState } from "@contexts/ModelContext";
import { BodyTypes } from "types/detail.types";
import useFetch from "@hooks/useFetch";

interface FetchBodyTypeProps extends BodyTypes {
  result: {
    bodyTypes: BodyTypes[];
  };
}

export default function BodyTypeView() {
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
    <Flex $justifyContent="flex-start" $alignItems="center">
      {bodyTypeList?.length && (
        <Image
          $width="100%"
          $height="25rem"
          $objectFit="contain"
          $margin="1rem"
          $shadow={
            "rgba(0, 0, 0, 0.07) 0px 1px 1px, rgba(0, 0, 0, 0.07) 0px 2px 2px, rgba(0, 0, 0, 0.07) 0px 4px 4px, rgba(0, 0, 0, 0.07) 0px 8px 8px, rgba(0, 0, 0, 0.07) 0px 16px 16px;"
          }
          src={bodyTypeList.find((item) => item.id === bodyTypeId)?.imgUrl}
        />
      )}
    </Flex>
  );
}
