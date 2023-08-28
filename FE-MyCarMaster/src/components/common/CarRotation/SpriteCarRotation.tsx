import React, { useState, useEffect } from "react";
import { styled } from "styled-components";
import UnderBar from "@assets/icons/UnderBar.svg";

type SpriteCarRotationProps = {
  $imgUrl: string | undefined;
  onLoad?: () => void;
};

export default function SpriteCarRotation({
  $imgUrl,
  onLoad,
}: SpriteCarRotationProps) {
  const frameWidth = 940;
  const frameHeight = 515;
  const gap = 515;
  const totalFrames = 59;
  const scale = 0.8;

  const [currentFrame, setCurrentFrame] = useState(0);
  const [isMouseDown, setIsMouseDown] = useState(false);
  const [beforeX, setBeforeX] = useState(0);
  const [showText, setShowText] = useState(true);

  const turnRight = () => {
    setShowText(false);
    currentFrame === totalFrames
      ? setCurrentFrame(0)
      : setCurrentFrame((currentFrame) => currentFrame + 1);
  };

  const turnLeft = () => {
    setShowText(false);
    currentFrame === 0
      ? setCurrentFrame(totalFrames)
      : setCurrentFrame((currentFrame) => currentFrame - 1);
  };

  const turnCar = (e: React.MouseEvent) => {
    if (isMouseDown && e.clientX != beforeX) {
      e.clientX > beforeX ? turnLeft() : turnRight();
      setBeforeX(e.clientX);
    }
  };

  useEffect(() => {
    const spriteImage = new Image();
    spriteImage.src = `${$imgUrl}`; // Replace with the actual URL

    spriteImage.onload = () => {
      onLoad && onLoad();
    };
  }, [$imgUrl, onLoad]);

  useEffect(() => {
    if (!$imgUrl) return;
    setShowText(true);
    return () => {
      setShowText(false);
    };
  }, [$imgUrl]);

  return (
    <Container>
      <ImageContainer
        onMouseUp={() => setIsMouseDown(false)}
        onMouseDown={() => setIsMouseDown(true)}
        onMouseMove={turnCar}
        onMouseLeave={() => setIsMouseDown(false)}
      >
        <UnderBarContainer src={UnderBar} />
        {showText && <Text>360도 돌려보세요!</Text>}
        <SpriteImage
          onLoad={onLoad}
          $src={$imgUrl}
          $width={frameWidth * scale}
          $height={frameHeight * scale}
          $backgroundPositionY={-currentFrame * (gap * scale)}
        />
      </ImageContainer>
    </Container>
  );
}

const Container = styled.div`
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
`;

const Text = styled.div`
  position: absolute;
  bottom: 10%;
  left: 50%;
  transform: translateX(-50%);
  color: ${(props) => props.theme.colors.NAVYBLUE5};
  ${(props) => props.theme.fonts.Regular12};

  animation: fadeIn 0.3s ease-in-out;
`;

const UnderBarContainer = styled.img`
  position: absolute;
  bottom: 16.66%;
  left: 50%;
  transform: translateX(-50%);
  z-index: -1;
  width: 70%;
`;

const ImageContainer = styled.div``;

const SpriteImage = styled.div<{
  $src: string | undefined;
  $width: number;
  $height: number;
  $backgroundPositionY: number;
}>`
  width: ${({ $width }) => $width}px;
  height: ${({ $height }) => $height}px;
  background-image: url(${({ $src }) => $src});
  background-repeat: no-repeat;
  background-position-y: ${({ $backgroundPositionY }) =>
    $backgroundPositionY}px;
  background-position-x: -0px;

  background-size: 100%;
  transition: background-position-x 0.1s linear;
`;
