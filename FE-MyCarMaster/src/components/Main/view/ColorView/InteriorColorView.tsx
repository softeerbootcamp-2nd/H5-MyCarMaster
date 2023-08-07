import React from "react";
import { styled } from "styled-components";
import interiorImage from "../../../../assets/images/interiorImage.png";

function InteriorColorView() {
  return <InteriorColorImg src={interiorImage} />;
}

const InteriorColorImg = styled.img`
  width: 100%;
  height: 100%;
  object-fit: contain;
  object-position: center;
`;

export default InteriorColorView;
