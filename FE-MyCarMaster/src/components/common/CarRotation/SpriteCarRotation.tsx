import React, { useState } from "react";
import { styled } from "styled-components";

type SpriteCarRotationProps = {
  $imgUrl: string | undefined;
};

export default function SpriteCarRotation({ $imgUrl }: SpriteCarRotationProps) {
  const frameWidth = 940;
  const frameHeight = 515;
  const gap = 515;
  const totalFrames = 60;
  const scale = 0.8;

  const [currentFrame, setCurrentFrame] = useState(0);
  const [isMouseDown, setIsMouseDown] = useState(false);
  const [beforeX, setBeforeX] = useState(0);

  const turnRight = () => {
    currentFrame === totalFrames
      ? setCurrentFrame(0)
      : setCurrentFrame((currentFrame) => currentFrame + 1);
  };

  const turnLeft = () => {
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

  return (
    <Container>
      <ImageContainer
        onMouseUp={() => setIsMouseDown(false)}
        onMouseDown={() => setIsMouseDown(true)}
        onMouseMove={turnCar}
        onMouseLeave={() => setIsMouseDown(false)}
      >
        <Image
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

const ImageContainer = styled.div``;

const Image = styled.div<{
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
