import React from "react";
import styled from "styled-components";
import MainWrapper from "./MainWrapper";

function MainView() {
  const navIndex = 0;
  return (
    <Container>
      <MainContent>
        <MainWrapper navIndex={navIndex} />
      </MainContent>
      <NavContainer>Navigation</NavContainer>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  height: 400px;
`;

const MainContent = styled.div`
  width: 51.5rem;
`;

const NavContainer = styled.div`
  width: 9.625rem;
`;

export default MainView;
