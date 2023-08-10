import React from "react";
import EngineView from "./EngineView";
import WheelDriveView from "./WheelDriveView";
import BodyTypeView from "./BodyTypeView";
import { useQuotationState } from "../../../../contexts/QuotationContext";

function DetailModelWrapper() {
  const { navigationId } = useQuotationState();
  if (navigationId === 1) return <EngineView />;
  if (navigationId === 2) return <WheelDriveView />;
  if (navigationId === 3) return <BodyTypeView />;
  return <></>;
}

export default DetailModelWrapper;
