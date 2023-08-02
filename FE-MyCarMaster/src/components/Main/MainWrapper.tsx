import React from "react";
import TrimContent from "./view/TrimContent";
import DetailModelContent from "./view/DetailModelContent";
import ColorContent from "./view/ColorContent";
import OptionContent from "./view/OptionContent";

interface MainWrapperProps {
  navIndex: number;
}

function MainWrapper({ navIndex }: MainWrapperProps) {
  if (navIndex === 0) return <TrimContent />;
  if (navIndex === 1) return <DetailModelContent />;
  if (navIndex === 2) return <ColorContent />;
  if (navIndex === 3) return <OptionContent />;
  return <></>;
}

export default MainWrapper;
