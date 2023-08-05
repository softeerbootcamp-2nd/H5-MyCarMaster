import TrimContent from "./view/TrimContent";
import DetailModelContent from "./view/DetailModelContent";
import ColorContent from "./view/ColorContent";
import OptionContent from "./view/OptionContent";
import { useQuotationState } from "../../contexts/QuotationContext";

function MainWrapper() {
  const { navigationId } = useQuotationState();
  if (navigationId === 0) return <TrimContent />;
  if (navigationId === 1) return <DetailModelContent />;
  if (navigationId === 2) return <ColorContent />;
  if (navigationId === 3) return <OptionContent />;
  return <></>;
}

export default MainWrapper;
