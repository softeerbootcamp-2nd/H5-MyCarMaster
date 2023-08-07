import React from "react";
import EngineView from "./EngineView";
import WheelDriveView from "./WheelDriveView";
import BodyTypeView from "./BodyTypeView";

interface DetailModelWrapperProps {
  navIndex: number;
}

function DetailModelWrapper({ navIndex }: DetailModelWrapperProps) {
  if (navIndex === 1) return <EngineView />;
  if (navIndex === 2) return <WheelDriveView />;
  if (navIndex === 3) return <BodyTypeView />;
  return <></>;
}

export default DetailModelWrapper;
