import TrimContent from "./view/TrimContent";
import DetailModelContent from "./view/DetailModelContent";
import ColorContent from "./view/ColorContent";
import OptionContent from "./view/OptionContent";
import { useQuotationState } from "../../contexts/QuotationContext";

function MainWrapper() {
  const { navigationId } = useQuotationState();
  if (navigationId === 0) return <TrimContent />;
  if (navigationId >= 1 && navigationId <= 3) return <DetailModelContent />;
  if (navigationId >= 4 && navigationId <= 5) return <ColorContent />;
  if (navigationId === 6) return <OptionContent />;
  return <></>;
}

export default MainWrapper;
