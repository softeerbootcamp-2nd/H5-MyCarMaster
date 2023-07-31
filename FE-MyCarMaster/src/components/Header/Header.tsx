import React from "react";
import styled from "styled-components";
import logo from "../../assets/images/logo.svg";
import ArrowBottom from "../../assets/icons/ArrowBottom.svg";

function Header() {
  return (
    <Container>
      <Img src={logo} />
      <ModelSelector>
        <ModelName>Palisade</ModelName>
        <ModelButton src={ArrowBottom} />
      </ModelSelector>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex: 1;
  justify-content: space-between;
  align-items: center;
`;

const Img = styled.img``;

const ModelSelector = styled.div`
  display: flex;
  align-items: center;
  gap: 0.5rem;
`;

const ModelName = styled.div``;

const ModelButton = styled.img``;

export default Header;
