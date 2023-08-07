import { styled } from "styled-components";
import BodyType_7 from "../../../../assets/images/BodyType_7.png";

function BodyTypeView() {
  return <BodyTypeImg src={BodyType_7} />;
}

const BodyTypeImg = styled.img`
  width: 100%;
  height: 100%;
  object-fit: scale-down;
  object-position: center;
`;

export default BodyTypeView;
