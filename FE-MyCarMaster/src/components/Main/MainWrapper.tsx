import {
  TrimContent,
  DetailModelContent,
  ColorContent,
  OptionContent,
} from "./view/index";
import { useQuotationState } from "@contexts/QuotationContext";

export default function MainWrapper() {
  const { navigationId } = useQuotationState();
  if (navigationId === 0) return <TrimContent />;
  if (navigationId >= 1 && navigationId <= 3) return <DetailModelContent />;
  if (navigationId >= 4 && navigationId <= 5) return <ColorContent />;
  if (navigationId === 6) return <OptionContent />;
  return <></>;
}
