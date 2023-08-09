import { styled } from "styled-components";
import { useDetailState } from "../../../../contexts/DetailContext";

function BodyTypeView() {
  const { bodyTypeId, bodyTypeList } = useDetailState();

  return <BodyTypeImg src={bodyTypeList[bodyTypeId].imgUrl} />;
}

const BodyTypeImg = styled.img`
  width: 100%;
  height: 100%;
  object-fit: scale-down;
  object-position: center;
`;

export default BodyTypeView;
