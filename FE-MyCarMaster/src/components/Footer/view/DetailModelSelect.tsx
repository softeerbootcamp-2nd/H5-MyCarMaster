import { Flex } from "@styles/core.style";
import { useQuotationState } from "@contexts/QuotationContext";
import {
  BodyTypeSelectView,
  EngineSelectView,
  WheelDriveSelectView,
} from "./DetailModelView/index";

export default function DetailModelSelect() {
  const { navigationId } = useQuotationState();

  return (
    <Flex $width="59.5rem" $gap="0.5rem">
      {navigationId === 1 && <EngineSelectView />}
      {navigationId === 2 && <WheelDriveSelectView />}
      {navigationId === 3 && <BodyTypeSelectView />}
    </Flex>
  );
}
