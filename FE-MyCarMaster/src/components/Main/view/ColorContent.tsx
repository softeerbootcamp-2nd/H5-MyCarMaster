import { Flex } from "@styles/core.style";
import { CategoryList } from "@common/index";
import ColorWrapper from "./ColorView/ColorWrapper";
import { useQuotationDispatch } from "@contexts/QuotationContext";
import theme from "@styles/Theme";

export default function ColorContent() {
  const quotationDispatch = useQuotationDispatch();

  const onClickHandler = (index: number) => {
    const nextNavigationId = 4 + index;
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
        categories={["외장색상", "내장색상"]}
        onClickHandler={(index) => onClickHandler(index as number)}
        indexSetter={4}
        $font={theme.fonts.Medium12_15}
        $switch={"colors"}
      />
      <ColorWrapper />
    </Flex>
  );
}
