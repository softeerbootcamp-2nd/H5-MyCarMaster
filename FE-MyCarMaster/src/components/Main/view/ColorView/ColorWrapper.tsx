import { useQuotationState } from "@contexts/QuotationContext";
import ExteriorColorView from "./ExteriorColorView";
import InteriorColorView from "./InteriorColorView";

export default function ColorWrapper() {
  const { navigationId } = useQuotationState();

  if (navigationId === 4) return <ExteriorColorView />;
  if (navigationId === 5) return <InteriorColorView />;
  return <></>;
}
