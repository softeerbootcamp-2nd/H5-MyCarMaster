import { styled } from "styled-components";
import {
  useQuotationState,
  useQuotationDispatch,
} from "../../../contexts/QuotationContext";
import { useOptionDispatch } from "../../../contexts/OptionContext";
import theme from "../../../styles/Theme";
import Button from "../Button/Button";

type OptionBoxProp = {
  $id: number;
  $name?: string;
  $description?: string;
  $price?: number;
  $ratio?: number;
  $imgUrl?: string;
  $selected?: boolean;
  $considered?: boolean;
  $choice?: boolean;
  handleClick?: () => void;
};

function OptionBox({
  $id,
  $name,
  $description,
  $price,
  $ratio,
  $imgUrl,
  $selected,
  $considered,
  handleClick,
}: OptionBoxProp) {
  const { navigationId } = useQuotationState();
  const quotationDispatch = useQuotationDispatch();
  const optionDispatch = useOptionDispatch();

  const considerButtonHandler = (id: number) => {
    optionDispatch({
      type: "SET_CHOICE_OPTION",
      payload: {
        where: "consideredOption",
        id: id,
      },
    });
    quotationDispatch({
      type: "SET_CONSIDER_QUOTATION",
      payload: {
        name: $name,
        price: $price,
      },
    });
  };

  const addButtonHandler = (id: number) => {
    optionDispatch({
      type: "SET_CHOICE_OPTION",
      payload: {
        where: "selectedOption",
        id: id,
      },
    });
    quotationDispatch({
      type: "SET_SELECT_QUOTATION",
      payload: {
        name: $name,
        price: $price,
      },
    });
  };

  return (
    <Container
      onClick={handleClick}
      $color={$selected ? "#1A3276" : $considered ? "#9B6D54" : "#ffffff"}
    >
      <TopContainer>
        {navigationId === 0 || navigationId === 6 ? (
          <>
            <Decoration>
              {$ratio === 0 ? "New" : `구매자 ${$ratio}%가 선택`}
            </Decoration>

            <OptionName>{$name}</OptionName>

            {navigationId !== 6 && (
              <>
                <Description>{$description}</Description>
                <Price>{$price?.toLocaleString("ko-KR")} 원</Price>
              </>
            )}
          </>
        ) : (
          <>
            <DetailModelOptionContainer>
              <Name>{$name}</Name>
              <Ratio>구매자 {$ratio}%가 선택</Ratio>
            </DetailModelOptionContainer>
            <Description>{$description}</Description>
          </>
        )}
      </TopContainer>
      {navigationId === 6 && (
        <Price>+ {$price?.toLocaleString("ko-KR")} 원</Price>
      )}
      <BottomContainer>
        {navigationId === 6 ? (
          <>
            <ButtonContainer>
              <Button
                $x={4.875}
                $y={1.5}
                $backgroundcolor={`${theme.colors.WHITE}`}
                $textcolor={`${theme.colors.NAVYBLUE5}`}
                $bordercolor={`${theme.colors.NAVYBLUE5}`}
                text={$considered ? "취소하기" : "고민해보기"}
                handleClick={() => considerButtonHandler($id)}
              />
              <Button
                $x={4.875}
                $y={1.5}
                $backgroundcolor={`${theme.colors.WHITE}`}
                $textcolor={`${theme.colors.NAVYBLUE5}`}
                $bordercolor={`${theme.colors.NAVYBLUE5}`}
                text={$selected ? "취소하기" : "추가하기"}
                handleClick={() => addButtonHandler($id)}
              />
            </ButtonContainer>
          </>
        ) : (
          <>
            {navigationId === 0 ? (
              <Detail>자세히보기 &gt;</Detail>
            ) : (
              <Price>+ {$price?.toLocaleString("ko-KR")} 원</Price>
            )}
          </>
        )}
      </BottomContainer>
    </Container>
  );
}

const Container = styled.div<{ $color: string }>`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: flex-start;
  gap: 0.5rem;
  background-color: ${({ $color }) => $color};

  border: 1px solid ${(props) => props.theme.colors.GREY2};
  width: 12.5rem;
  height: 10.25rem;
  padding: 0.75rem 1rem;
`;

const TopContainer = styled.div`
  height: 5.75rem;
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
`;

const BottomContainer = styled.div`
  display: flex;
  justify-content: space-around;
`;

const Decoration = styled.p`
  font-size: 0.625rem;
  font-weight: 400;
`;

const OptionName = styled.p`
  ${(props) => props.theme.fonts.titleLarge};
`;

const Description = styled.p`
  color: ${(props) => props.theme.colors.GREY3};
  ${(props) => props.theme.fonts.contentMedium};
`;

const Price = styled.p`
  ${(props) => props.theme.fonts.contentLarge};
`;

// const Tag = styled.button`
//   width: 3.25rem;
//   height: 1.25rem;
//   font-size: 0.625rem;
// `;

const DetailModelOptionContainer = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 0.5rem;
`;

const Name = styled.p`
  color: ${(props) => props.theme.colors.BLACK};
  ${(props) => props.theme.fonts.titleSmall};
`;

const Ratio = styled.p`
  color: ${(props) => props.theme.colors.NAVYBLUE5};
  font-size: 0.625rem;
  font-weight: 400;
  line-height: 1rem;
`;

const Detail = styled.p`
  color: ${(props) => props.theme.colors.BLACK};
  ${(props) => props.theme.fonts.subContent};
`;

const ButtonContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 0.5rem;
`;

export default OptionBox;
