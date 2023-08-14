import React, { useState } from "react";
import { styled } from "styled-components";
import theme from "../../../styles/Theme";

const coloredImgUrl: string[] = [];

for (let i = 1; i <= 60; i++) {
  coloredImgUrl.push(
    `/images/exterior/black/image_${i.toString().padStart(3, "0")}.png`
  );
}

interface CarRotationProps {
  $isQuotation: boolean;
  // coloredImgUrl: string[]; 추가 예정
}

function CarRotation({ $isQuotation }: CarRotationProps) {
  const [currentImg, setCurrentImg] = useState<number>(0);
  const [isMouseDown, setIsMouseDown] = useState<boolean>(false);
  const [beforeX, setBeforeX] = useState(0);

  const imgCount = coloredImgUrl.length - 1;

  const turnRight = () => {
    currentImg === imgCount
      ? setCurrentImg(0)
      : setCurrentImg((currentImg) => currentImg + 1);
  };

  const turnLeft = () => {
    currentImg === 0
      ? setCurrentImg(imgCount)
      : setCurrentImg((currentImg) => currentImg - 1);
  };

  const turnCar = (e: React.MouseEvent) => {
    if (isMouseDown && e.clientX != beforeX) {
      e.clientX > beforeX ? turnLeft() : turnRight();
      setBeforeX(e.clientX);
    }
  };

  return (
    <Container>
      <ImgContainer
        onMouseUp={() => setIsMouseDown(false)}
        onMouseDown={() => setIsMouseDown(true)}
        onMouseMove={turnCar}
        onMouseLeave={() => setIsMouseDown(false)}
      >
        {coloredImgUrl.map((img, index) => (
          <Image key={index} src={img} $display={currentImg === index} />
        ))}
      </ImgContainer>
      <Circle />
      <Text $isQuotation={$isQuotation}>360도 돌려보세요!</Text>
    </Container>
  );
}

export default CarRotation;

const Container = styled.div`
  position: relative;
  width: 100%;
  height: 100%;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

const ImgContainer = styled.div``;

const Image = styled.img<{ $display: boolean }>`
  width: 100%;
  height: 25rem;
  flex-shrink: 0;
  display: ${({ $display }) => ($display ? "" : "none")};
  -webkit-user-drag: none;
`;

const Circle = styled.div`
  position: absolute;
  width: 38.4375rem;
  height: 4.4375rem;
  border: 1px solid transparent;
  border-radius: 50%;
  background-image: linear-gradient(#ffffff, #ffffff),
    linear-gradient(0deg, #14285e 0%, #14285e45 50%, white 100%);
  background-origin: border-box;
  background-clip: content-box, border-box;
  z-index: -1;

  margin-top: 13rem;
`;

const Text = styled.div<{ $isQuotation: boolean }>`
  position: absolute;
  margin-top: 22rem;
  color: ${theme.colors.NAVYBLUE5};
  font: ${theme.fonts.contentMedium};
  display: ${({ $isQuotation }) => ($isQuotation ? "none" : "")};
`;
