import React from "react";
import { styled } from "styled-components";
import Button from "../Button/Button";
import theme from "../../../styles/Theme";

interface QuotationOptionProps {
  imgUrl: string;
  category: string;
  name: string;
  price: number;
  isSelected: boolean;
}

function QuotationOptionItem({
  imgUrl,
  category,
  name,
  price,
  isSelected,
}: QuotationOptionProps) {
  const confirmHandler = () => {};

  const considerHandler = () => {};

  return (
    <Container>
      <OptionImg src={imgUrl} />
      <OptionDetail>
        <OptionCategory>{category}</OptionCategory>
        <OptionName>{name}</OptionName>
      </OptionDetail>
      <Button
        $x={6}
        $y={2}
        text={isSelected ? "고민해보기" : "확정하기"}
        $backgroundcolor={
          isSelected ? `${theme.colors.GOLD5}` : `${theme.colors.NAVYBLUE5}`
        }
        $bordercolor={
          isSelected ? `${theme.colors.GOLD5}` : `${theme.colors.NAVYBLUE5}`
        }
        $textcolor={`${theme.colors.WHITE}`}
        handleClick={isSelected ? considerHandler : confirmHandler}
      />
      <OptionPrice>+{price.toLocaleString("ko-KR")}원</OptionPrice>
    </Container>
  );
}

export default QuotationOptionItem;

const Container = styled.div`
  width: 42.25rem;
  height: 7.5rem;

  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const OptionImg = styled.img`
  width: 9.625rem;
  height: 100%;
`;

const OptionDetail = styled.div`
  width: 14.625rem;
  height: 100%;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  gap: 0.5rem;
`;

const OptionCategory = styled.p``;

const OptionName = styled.p`
  font-family: "HyundaiSansMedium";
  font-size: 1.375rem;
  font-style: normal;
  font-weight: 700;
  line-height: 1.75rem;
`;

const OptionPrice = styled.p`
  width: 7.6875rem;
  font-family: "HyundaiSansMedium";
  font-size: 1.375rem;
  font-style: normal;
  font-weight: 700;
  line-height: 1.75rem;

  display: flex;
  justify-content: flex-end;
`;
