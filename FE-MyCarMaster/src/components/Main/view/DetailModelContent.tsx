import { Flex } from "@styles/core.style";
import { CategoryList } from "@common/index";
import DetailModelWrapper from "./DetailModelView/DetailModelWrapper";
import { useQuotationDispatch } from "@contexts/QuotationContext";

export default function DetailModelContent() {
  const quotationDispatch = useQuotationDispatch();

  const onClickHandler = (index: number) => {
    const nextNavigationId = 1 + index;
    quotationDispatch({
      type: "NAVIGATE",
      payload: {
        navigationId: nextNavigationId,
      },
    });
  };
  return (
    <Flex $flexDirection="column">
      <CategoryList
        categories={["엔진", "구동방식", "바디타입"]}
        onClickHandler={(index) => onClickHandler(index as number)}
        indexSetter={1}
        $switch={"detail"}
      />
      <DetailModelWrapper />
    </Flex>
  );
}
