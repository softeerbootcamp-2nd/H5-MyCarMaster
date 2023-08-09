import { styled } from "styled-components";
import { useDetailState } from "../../../../contexts/DetailContext";

function WheelDriveView() {
  const { wheelDriveId, wheelDriveList } = useDetailState();
  console.log(wheelDriveId, wheelDriveList);

  return <WheelDriveImg src={wheelDriveList[wheelDriveId].imgUrl} />;
}

const WheelDriveImg = styled.img`
  width: 100%;
  height: 100%;
  object-fit: contain;
  object-position: center;
`;

export default WheelDriveView;
