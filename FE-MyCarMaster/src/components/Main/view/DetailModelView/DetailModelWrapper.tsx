import { EngineView, WheelDriveView, BodyTypeView } from "./index";
import { useQuotationState } from "@contexts/QuotationContext";

export default function DetailModelWrapper() {
  const { navigationId } = useQuotationState();
  if (navigationId === 1) return <EngineView />;
  if (navigationId === 2) return <WheelDriveView />;
  if (navigationId === 3) return <BodyTypeView />;
  return <></>;
}
