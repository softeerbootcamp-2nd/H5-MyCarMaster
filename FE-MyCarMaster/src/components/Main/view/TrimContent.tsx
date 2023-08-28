import { useEffect } from "react";
import { Flex, Image } from "@styles/core.style";
import { useTrimDispatch, useTrimState } from "@contexts/TrimContext";
import { useQuotationDispatch } from "@contexts/QuotationContext";
import { useModelState } from "@contexts/ModelContext";
import { Trims } from "types/trim.types";
import useFetch from "@hooks/useFetch";

interface FetchTrimsProps extends Trims {
  result: {
    trims: Trims[];
  };
}

export default function TrimContent() {
  const SERVER_URL = import.meta.env.VITE_APP_SERVER_URL;

  const { modelId } = useModelState();
  const { trimId, trimList } = useTrimState();
  const trimDispatch = useTrimDispatch();
  const quotationDispatch = useQuotationDispatch();

  const { data } = useFetch<FetchTrimsProps>(
    `${SERVER_URL}/trims/?modelId=${modelId}`,
    { method: "GET" }
  );

  useEffect(() => {
    if (data) {
      trimDispatch({
        type: "SET_TRIM_LIST",
        payload: { trimList: data.result.trims },
      });

      quotationDispatch({
        type: "SET_TRIM_QUOTATION",
        payload: {
          id: data.result.trims[trimId - 1].id,
          name: data.result.trims[trimId - 1].name,
          price: data.result.trims[trimId - 1].price,
        },
      });
    }
  }, [data]);

  if (!trimList?.length) return null;

  return (
    <Flex $justifyContent="flex-start" $alignItems="center">
      {trimList?.length && (
        <Image
          $objectFit="contain"
          $height="25rem"
          $margin="0"
          src={trimList[trimId - 1].imgUrl}
        />
      )}
    </Flex>
  );
}
