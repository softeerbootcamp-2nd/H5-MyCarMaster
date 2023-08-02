import React from "react";
import styled from "styled-components";

function MainView() {
  return (
    <Container>
      <MainContent>Main Contents</MainContent>
      <NavContainer>Navigation</NavContainer>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex: 5;
  flex-direction: row;
`;

const MainContent = styled.div`
  flex: 6;
`;

const NavContainer = styled.div`
  flex: 1;
`;

export default MainView;
