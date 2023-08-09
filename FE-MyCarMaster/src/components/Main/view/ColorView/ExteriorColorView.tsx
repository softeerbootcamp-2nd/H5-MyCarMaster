import { styled } from "styled-components";
import { useCarPaintState } from "../../../../contexts/CarPaintContext";

function ExteriorColorView() {
  const { exteriorId, exteriorList } = useCarPaintState();
  return <ExteriorColorImg src={exteriorList[exteriorId].coloredImgUrl} />;
}

const ExteriorColorImg = styled.img`
  width: 100%;
  height: 100%;
  object-fit: contain;
  object-position: center;
`;

export default ExteriorColorView;
