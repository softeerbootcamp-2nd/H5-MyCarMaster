import {
  TrimSelect,
  DetailModelSelect,
  ColorSelect,
  OptionSelect,
} from "./view/index";
import { useQuotationState } from "../../contexts/QuotationContext";

export default function SelectListWrapper() {
  const { navigationId } = useQuotationState();

  if (navigationId === 0) return <TrimSelect />;
  if (navigationId >= 1 && navigationId <= 3) return <DetailModelSelect />;
  if (navigationId >= 4 && navigationId <= 5) return <ColorSelect />;
  if (navigationId === 6) return <OptionSelect />;
  if (navigationId === 7) return;
}
