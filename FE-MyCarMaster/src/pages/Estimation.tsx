import React from "react";
import { Footer, Header, MainView } from "../components";
import { styled } from "styled-components";

function Estimation() {
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
  width: 80rem;
  height: 45rem;
  margin: 4rem 8rem;
`;

export default Estimation;
