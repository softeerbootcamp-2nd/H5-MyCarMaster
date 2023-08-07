import { styled } from "styled-components";
import twoWD from "../../../../assets/images/2WD.png";

function WheelDriveView() {
  return <WheelDriveImg src={twoWD} />;
}

const WheelDriveImg = styled.img`
  width: 100%;
  height: 100%;
  object-fit: contain;
  object-position: center;
`;

export default WheelDriveView;
