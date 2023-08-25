import React, { useState, useEffect } from "react";
import { styled } from "styled-components";
import theme from "../../../styles/Theme";

interface CarRotationProps {
  $isQuotation: boolean;
  $imgUrl: string | undefined;
}

function CarRotation({ $isQuotation, $imgUrl }: CarRotationProps) {
  const [currentImg, setCurrentImg] = useState<number>(1);
  const [isMouseDown, setIsMouseDown] = useState<boolean>(false);
  const [beforeX, setBeforeX] = useState(0);
  const [colorImgUrl, setColorImgUrl] = useState<string[]>([]);

  const imgCount = colorImgUrl.length - 1;

  const turnRight = () => {
    currentImg === imgCount
      ? setCurrentImg(1)
      : setCurrentImg((currentImg) => currentImg + 1);
  };

  const turnLeft = () => {
    currentImg === 1
      ? setCurrentImg(imgCount)
      : setCurrentImg((currentImg) => currentImg - 1);
  };

  const turnCar = (e: React.MouseEvent) => {
    if (isMouseDown && e.clientX != beforeX) {
      e.clientX > beforeX ? turnLeft() : turnRight();
      setBeforeX(e.clientX);
    }
  };

  useEffect(() => {
    if (!$imgUrl) return;
    setColorImgUrl(
      Array.from({ length: 60 }, (_, i) => {
        const index = i + 1;
        return `${$imgUrl}image_${index < 10 ? "00" + index : "0" + index}.png`;
      })
    );

    return () => {
      setColorImgUrl([]);
    };
  }, [$imgUrl]);

  return (
    <Container>
      <ImgContainer
        onMouseUp={() => setIsMouseDown(false)}
        onMouseDown={() => setIsMouseDown(true)}
        onMouseMove={turnCar}
        onMouseLeave={() => setIsMouseDown(false)}
      >
        {colorImgUrl.map((img, index) => (
          <Image key={index} src={img} $display={currentImg === index} />
        ))}
        <Text $isQuotation={$isQuotation}>360도 돌려보세요!</Text>
      </ImgContainer>
      <Circle />
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

const ImgContainer = styled.div`
  width: 80%;
  height: auto;
`;

const Image = styled.img<{ $display: boolean }>`
  width: 100%;
  height: 100%;
  flex-shrink: 0;
  display: ${({ $display }) => ($display ? "" : "none")};
  -webkit-user-drag: none;
`;

const Circle = styled.div`
  position: absolute;
  width: 45rem;
  height: 4.4375rem;
  border: 1px solid transparent;
  border-radius: 50%;
  background-image: linear-gradient(#ffffff, #ffffff),
    linear-gradient(0deg, #14285e 0%, #14285e45 50%, white 100%);
  background-origin: border-box;
  background-clip: content-box, border-box;
  z-index: -1;

  margin-top: 18rem;
`;

const Text = styled.div<{ $isQuotation: boolean }>`
  position: absolute;
  bottom: 5%;
  left: 50%;
  transform: translateX(-50%);
  color: ${theme.colors.NAVYBLUE5};

  display: ${({ $isQuotation }) => ($isQuotation ? "none" : "")};
`;
