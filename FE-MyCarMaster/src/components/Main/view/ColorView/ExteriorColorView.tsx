import React from "react";
import exteriorImage from "../../../../assets/images/exteriorImage.png";
import { styled } from "styled-components";

function ExteriorColorView() {
  return <ExteriorColorImg src={exteriorImage} />;
}

const ExteriorColorImg = styled.img`
  width: 100%;
  height: 100%;
  object-fit: contain;
  object-position: center;
`;

export default ExteriorColorView;
