import styled from "styled-components";
import Button from "../components/common/Button/Button.tsx";
import homeVideo from "../assets/video/homeVideo.mp4";
import white_logo from "../assets/images/white_logo.svg";
import { Header } from "../components/index.tsx";
import { useNavigate } from "react-router-dom";
import { useState } from "react";

type TextProp = {
  $size: number;
  $weight: number;
  $lineHeight: number;
  $color?: string;
  $animation?: boolean;
  $delay?: number;
};

function Home() {
  const navigate = useNavigate();
  const [isFold, setIsFold] = useState(false);

  const modelSelectHandler = () => {
    setIsFold(true);
  };
  const homeButtonHandler = () => {
    navigate("/estimation");
  };

  return (
    <>
      <Video autoPlay muted loop>
        <source src={homeVideo} type="video/mp4" />
      </Video>
      <Container>
        <Header
          logo={white_logo}
          isHome={true}
          status={isFold ? "dark" : "default"}
        />
        <IntroduceBox>
          <Text $size={2.4} $weight={500} $lineHeight={2.5}>
            내게 맞는 견적부터 카마스터 연결까지
          </Text>
          <Text $size={1.2} $weight={400} $lineHeight={1.5}>
            마이 카마스터와 함께해요
          </Text>

          <Button
            $x={15}
            $y={2.75}
            $backgroundcolor="#FFFFFF"
            $textcolor="#222222"
            $bordercolor="#FFFFFF"
            text="마이 카마스터 시작하기"
            handleClick={modelSelectHandler}
          />
        </IntroduceBox>
        <Point $start={isFold}>
          <Text
            $size={2.4}
            $weight={500}
            $lineHeight={2.5}
            $color={"#222222"}
            $animation={isFold}
            $delay={0.5}
          >
            모델을 선택해주세요.
          </Text>
          <Text
            $size={1.2}
            $weight={400}
            $lineHeight={1.5}
            $color={"#222222"}
            $animation={isFold}
            $delay={2.5}
          >
            내게 맞는 견적부터 카마스터 연결까지
          </Text>
          <Text
            $size={1.2}
            $weight={400}
            $lineHeight={1.5}
            $color={"#222222"}
            $animation={isFold}
            $delay={4.5}
          >
            마이 카마스터와 함께해요
          </Text>

          <Button
            $x={15}
            $y={2.75}
            $backgroundcolor="#FFFFFF"
            $textcolor="#222222"
            $bordercolor="#FFFFFF"
            text="여기 클릭하면 시작함"
            handleClick={homeButtonHandler}
          />
        </Point>
      </Container>
    </>
  );
}

const Container = styled.div`
  position: relative;
  width: 100%;
  height: 100%;
`;

const Video = styled.video`
  object-fit: fill;
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: -1;
`;

const IntroduceBox = styled.div`
  width: 18rem;
  height: 13.75rem;
  position: absolute;

  top: 40%;
  left: 65%;
  z-index: 1;

  button {
    position: absolute;
    bottom: 0;
  }
`;

const Point = styled.div<{ $start: boolean }>`
  display: ${(props) => (props.$start ? "flex" : "none")};
  flex-direction: column;
  position: absolute;
  top: calc(40% + 11rem);
  left: 65%;
  z-index: 5;

  background-color: #fff;

  animation: ${(props) =>
    props.$start ? "point 0.5s ease-in-out forwards" : ""};

  @keyframes point {
    0% {
      top: calc(40% + 11rem);
      left: 65%;
      width: 15rem;
      height: 2.75rem;
    }
    100% {
      // 전체 화면으로 커지기
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      padding: 10rem;
    }
  }
`;

const Text = styled.p<TextProp>`
  font-size: ${(props) => (props.$animation ? "0" : props.$size)}rem;
  font-style: normal;
  font-weight: ${(props) => props.$weight};
  line-height: ${(props) => props.$lineHeight}rem;
  text-align: left;
  color: ${(props) => props.$color || "#FFFFFF"};

  // appear transition add delay
  animation: ${(props) =>
    props.$animation ? "textAppear 3s ease-in-out forwards" : ""};
  animation-delay: ${(props) => props.$delay}s;

  @keyframes textAppear {
    0% {
      font-size: ${(props) => props.$size}rem;
      opacity: 0;
    }
    100% {
      font-size: ${(props) => props.$size}rem;
      opacity: 1;
    }
  }
`;
export default Home;
