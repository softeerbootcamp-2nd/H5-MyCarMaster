import { styled } from "styled-components";
import { useCarPaintState } from "../../../../contexts/CarPaintContext";

function InteriorColorView() {
  const { interiorId, interiorList } = useCarPaintState();
  return <InteriorColorImg src={interiorList[interiorId].coloredImgUrl} />;
}

const InteriorColorImg = styled.img`
  width: 100%;
  height: 100%;
  object-fit: contain;
  object-position: center;
`;

export default InteriorColorView;
