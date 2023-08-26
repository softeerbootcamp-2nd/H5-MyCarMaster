import { useEffect, useState } from "react";
import { styled } from "styled-components";
import Button from "../Button/Button";
import theme from "../../../styles/Theme";
import { useQuotationDispatch } from "../../../contexts/QuotationContext";
import { useOptionDispatch } from "../../../contexts/OptionContext";

interface QuotationOptionProps {
  id: number;
  imgUrl: string;
  category: string;
  name: string;
  price: number;
  isSelected: boolean;
  $isFinished: boolean;
  confirm: boolean;
}

function QuotationOptionItem({
  id,
  imgUrl,
  category,
  name,
  price,
  isSelected: initialIsSelected,
  $isFinished,
  confirm,
}: QuotationOptionProps) {
  const quotationDispatch = useQuotationDispatch();
  const optionDispatch = useOptionDispatch();

  const [isSelected, setIsSelected] = useState(initialIsSelected);

  useEffect(() => {
    setIsSelected(initialIsSelected);
  }, [initialIsSelected]);

  const toggleSelect = (id: number) => {
    const updatedIsSelected = !isSelected;
    const actionType = updatedIsSelected
      ? "SET_SELECT_QUOTATION"
      : "SET_CONSIDER_QUOTATION";
    const where = updatedIsSelected ? "selectedOption" : "consideredOption";

    quotationDispatch({
      type: actionType,
      payload: {
        id,
        name,
        price,
        category,
        imgUrl,
      },
    });

    optionDispatch({
      type: "SET_CHOICE_OPTION",
      payload: {
        where: where,
        id: id,
      },
    });

    setIsSelected(updatedIsSelected);
  };

  return (
    <Container $isFinished={$isFinished}>
      <OptionImg src={imgUrl} />
      <OptionDetail>
        <OptionCategory>{category}</OptionCategory>
        <OptionName>{name}</OptionName>
      </OptionDetail>
      {!confirm && (
        <Button
          $x={8}
          $y={2}
          text={isSelected ? "고민해보기" : "확정하기"}
          $backgroundcolor={
            isSelected ? `${theme.colors.GOLD5}` : `${theme.colors.NAVYBLUE5}`
          }
          $bordercolor={
            isSelected ? `${theme.colors.GOLD5}` : `${theme.colors.NAVYBLUE5}`
          }
          $textcolor={`${theme.colors.WHITE}`}
          handleClick={() => toggleSelect(id)}
        />
      )}

      <OptionPrice>+{price.toLocaleString("ko-KR")}원</OptionPrice>
    </Container>
  );
}

export default QuotationOptionItem;

const Container = styled.div<{ $isFinished: boolean }>`
  width: ${({ $isFinished }) => ($isFinished ? "100%" : "48rem")};
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
  ${(props) => props.theme.fonts.Regular13};
  line-height: 1.75rem;
`;

const OptionPrice = styled.p`
  font-family: "HyundaiSansMedium";
  ${(props) => props.theme.fonts.Regular13};
  line-height: 1.75rem;

  display: flex;
  justify-content: flex-end;
`;
