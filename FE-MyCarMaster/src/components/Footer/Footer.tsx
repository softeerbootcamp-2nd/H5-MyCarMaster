import React from "react";
import styled from "styled-components";
import { GREY1 } from "../../styles/Color";
import InnerColorBox from "../common/ColorBox/InnerColorBox";

function Footer() {
  // api 연동 후 Props 재정의 필요
  const InnerColorProps = {
    id: 1,
    name: "퀼팅천연(블랙)",
    trim: "Le Blanc",
    ratio: 33,
    price: 100000,
    // colorImgUrl
    // coloredImgUrl
  };

  return (
    <Container>
      {/* 각 단계에 맞는 상품 아이템이 들어감 */}
      <LeftContainer>
        <InnerColorBox {...InnerColorProps} />
      </LeftContainer>
      <RightContainer>{/* 이전 혹은 다음 버튼이 들어감 */}</RightContainer>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  width: 100%;
  flex: 4;
  gap: 1.25rem;
  background-color: ${GREY1};
`;

const LeftContainer = styled.div`
  display: flex;
  flex-direction: row;
  gap: 2rem;

  width: 51.5rem;
`;

const RightContainer = styled.div``;

export default Footer;
