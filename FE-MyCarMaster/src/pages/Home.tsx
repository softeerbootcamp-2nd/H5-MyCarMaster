import React from "react";
import styled from "styled-components";
import Button from "../components/common/Button/Button.tsx";
import homeVideo from "../assets/video/homeVideo.mp4";
import white_logo from "../assets/images/white_logo.svg";
import { Header } from "../components";

type TextProp = {
  $size: number;
  $weight: number;
  $lineHeight: number;
};

function Home() {
  return (
    <>
      <Video autoPlay muted loop>
        <source src={homeVideo} type="video/mp4" />
      </Video>
      <Container>
        <Header logo={white_logo} isHome={true} />
        <IntroduceBox>
          <Text $size={2} $weight={500} $lineHeight={2.5}>
            내게 맞는 견적부터 카마스터 연결까지
          </Text>
          <Text $size={1} $weight={400} $lineHeight={1.5}>
            마이 카마스터와 함께해요
          </Text>

          <Button
            $x={15}
            $y={2.75}
            $backgroundcolor="#FFFFFF"
            $textcolor="#222222"
            $bordercolor="#FFFFFF"
            text="마이 카마스터 시작하기"
          />
        </IntroduceBox>
      </Container>
    </>
  );
}

const Video = styled.video`
  object-fit: fill;

  position: fixed;
  min-width: 100%;
  min-height: 100vh;
`;

const Container = styled.div`
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 80rem;
  height: 45rem;
  margin: 0 8rem;
`;

const IntroduceBox = styled.div`
  width: 241px;
  height: 220px;
  position: absolute;
  top: 250px;
  left: 803px;

  button {
    position: absolute;
    bottom: 0;
  }
`;

const Text = styled.p<TextProp>`
  font-size: ${(props) => props.$size}rem;
  font-style: normal;
  font-weight: ${(props) => props.$weight};
  line-height: ${(props) => props.$lineHeight}rem;
  letter-spacing: -0.06rem;
  text-align: left;
  color: #ffffff;
`;
export default Home;
