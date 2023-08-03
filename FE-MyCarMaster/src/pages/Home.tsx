import React from "react";
import homeVideo from "../assets/video/homeVideo.mp4";
import white_logo from "../assets/images/white_logo.svg";
import { styled } from "styled-components";
import { Header } from "../components";

function Home() {
  return (
    <>
      <Video autoPlay muted loop>
        <source src={homeVideo} type="video/mp4" />
      </Video>

      <Container>
        <Header logo={white_logo} isHome={true} />
      </Container>
    </>
  );
}

const Video = styled.video`
  object-fit: fill;

  position: fixed;
  right: 0;
  bottom: 0;
  min-width: 100%;
  min-height: 100%;
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

export default Home;
