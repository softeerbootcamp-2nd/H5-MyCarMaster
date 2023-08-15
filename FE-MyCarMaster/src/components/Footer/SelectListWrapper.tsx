import TrimContent from "./view/TrimSelect";
import DetailModelSelect from "./view/DetailModelSelect";
import OptionSelect from "./view/OptionSelect";
import ColorSelect from "./view/ColorSelect";
import { useQuotationState } from "../../contexts/QuotationContext";
import { useNavigate } from "react-router-dom";

export default function SelectListWrapper() {
  const { navigationId } = useQuotationState();
  const navigate = useNavigate();
  if (navigationId === 0) return <TrimContent />;
  if (navigationId >= 1 && navigationId <= 3) return <DetailModelSelect />;
  if (navigationId >= 4 && navigationId <= 5) return <ColorSelect />;
  if (navigationId === 6) return <OptionSelect />;
  if (navigationId === 7) navigate("/quotation");
  return;
}
