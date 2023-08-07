import React from "react";
import { useQuotationState } from "../../../../contexts/QuotationContext";
import ExteriorColorView from "./ExteriorColorView";
import InteriorColorView from "./InteriorColorView";

function ColorWrapper() {
  const { navigationId } = useQuotationState();

  if (navigationId === 4) return <ExteriorColorView />;
  if (navigationId === 5) return <InteriorColorView />;
  return <></>;
}

export default ColorWrapper;
