import { Flex } from "@styles/core.style";
import { useQuotationState } from "@/contexts/QuotationContext";
import ExteriorColorSelectView from "./ColorView/ExteriorColorSelectView";
import InteriorColorSelectView from "./ColorView/InteriorColorSelectView";

export default function TrimSelect() {
  const { navigationId } = useQuotationState();
  return (
    <Flex $justifyContent="center" $width="59.5rem">
      {navigationId === 4 && (
        <Flex $gap="0.5rem" $wrap={true}>
          <ExteriorColorSelectView />
        </Flex>
      )}

      {navigationId === 5 && (
        <Flex $gap="0.5rem" $wrap={true}>
          <InteriorColorSelectView />
        </Flex>
      )}
    </Flex>
  );
}
