import React from "react";
import { Footer, Header, MainView } from "../components";
import { styled } from "styled-components";

function MainPage() {
  return (
    <Container>
      <Header />
      <MainView />
      <Footer />
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  height: 100vh;
`;

export default MainPage;
