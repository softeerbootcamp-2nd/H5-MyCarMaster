import { styled } from "styled-components";

function ExteriorColorView() {
  return <ExteriorColorImg src="/images/exterior/black/image_001.png" />;
}

const ExteriorColorImg = styled.img`
  width: 100%;
  height: 100%;
  object-fit: contain;
  object-position: center;
`;

export default ExteriorColorView;
