import React from "react";
import { styled } from "styled-components";
import TextButton from "../Button/TextButton";
import { useQuotationState } from "../../../contexts/QuotationContext";
import QuotationOptionItem from "./QuotationOptionItem";
import theme from "../../../styles/Theme";

function QuotationOptionList() {
  const { optionQuotation } = useQuotationState();
  console.log(optionQuotation);

  return (
    <Container>
      <CategoryContainer>
        <Category>옵션</Category>
        <TextButton size={"1"} text={"변경하기"} />
      </CategoryContainer>
      <ItemContainer>
        <TextButton size={"1.25"} text={"기본 포함 옵션"} />
        <OptionContainer>
          <Option>추가 옵션</Option>
          {optionQuotation.selectedQuotation.map((option, index) => (
            <QuotationOptionItem
              key={index}
              imgUrl={option.imgUrl as string}
              category={option.category as string}
              name={option.name}
              price={option.price}
              isSelected={true}
            />
          ))}
          <Option>고민 옵션</Option>
          {optionQuotation.consideredQuotation.map((option, index) => (
            <QuotationOptionItem
              key={index}
              imgUrl={option.imgUrl as string}
              category={option.category as string}
              name={option.name}
              price={option.price}
              isSelected={false}
            />
          ))}
        </OptionContainer>
      </ItemContainer>
    </Container>
  );
}

export default QuotationOptionList;

const Container = styled.div`
  width: 51.5rem;

  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  padding-bottom: 2rem;
  border-bottom: 1px solid ${theme.colors.GREY3};
`;

const CategoryContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: flex-start;
  height: 4.5rem;
`;

const Category = styled.p`
  font-family: "HyundaiSansMedium";
  font-size: 1.5rem;
  font-style: normal;
  font-weight: 500;
  line-height: 2rem; /* 133.333% */
`;

const ItemContainer = styled.div`
  width: 42.25rem;
  display: flex;
  flex-direction: column;
  align-items: flex-end;
`;

const OptionContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-self: flex-start;
  gap: 1.5rem;
`;

const Option = styled.div`
  font-family: "HyundaiSansMedium";
  font-size: 1.375rem;
  font-style: normal;
  font-weight: 700;
  line-height: 1.75rem;
`;
