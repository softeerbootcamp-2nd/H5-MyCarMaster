import { styled } from "styled-components";
import { useQuotationState } from "../../../contexts/QuotationContext";
import theme from "../../../styles/Theme";
import Button from "../Button/Button";
import {
  BLACK,
  GREY2,
  GREY3,
  NAVYBLUE4,
  NAVYBLUE5,
} from "../../../styles/Color";

type OptionBoxProp = {
  $id: number;
  $name?: string;
  $description?: string;
  $price?: number;
  $ratio?: number;
  $imgUrl?: string;
  $selected?: boolean;
  $considered?: boolean;
  handleClick?: () => void;
};

function OptionBox({
  $id,
  $name,
  $description,
  $price,
  $ratio,
  $imgUrl,
  handleClick,
  $selected,
  $considered,
}: OptionBoxProp) {
  const { navigationId } = useQuotationState();
  const navindex = 0;

  const considerButtonHandler = () => {
    console.log("considerButtonHandler");
  };

  const addButtonHandler = () => {
    console.log("addButtonHandler");
  };

  return (
    <Container>
      <TopContainer>
        {navigationId === 0 || navigationId === 6 ? (
          <>
            <Decoration navindex={navindex}>New</Decoration>
            <OptionName navindex={navindex}>{$name}</OptionName>

            {navigationId !== 6 && <Description>{$description}</Description>}

            {navigationId === 6 ? (
              <Price navindex={navindex}>
                + {$price?.toLocaleString("ko-KR")} 원
              </Price>
            ) : (
              <PriceContainer>
                <Price navindex={navindex}>
                  {$price?.toLocaleString("ko-KR")} 원
                </Price>
                {/* {navindex === 0 ? <Tag>기본 제공</Tag> : <></>} */}
              </PriceContainer>
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

      <BottomContainer>
        {navigationId === 6 ? (
          <>
            <ButtonContainer>
              <Button
                $x={4.875}
                $y={1.5}
                $backgroundcolor={`${theme.colors.White}`}
                $textcolor={`${theme.colors.NavyBlue5}`}
                $bordercolor={`${theme.colors.NavyBlue5}`}
                text={"고민해보기"}
                handleClick={considerButtonHandler}
              />
              <Button
                $x={4.875}
                $y={1.5}
                $backgroundcolor={`${theme.colors.White}`}
                $textcolor={`${theme.colors.NavyBlue5}`}
                $bordercolor={`${theme.colors.NavyBlue5}`}
                text={"추가하기"}
                handleClick={addButtonHandler}
              />
            </ButtonContainer>
          </>
        ) : (
          <>
            <Detail>자세히보기 &gt;</Detail>
          </>
        )}
      </BottomContainer>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  gap: 0.5rem;

  border: 1px solid ${GREY2};
  width: 12.5rem;
  height: 10.25rem;
  padding: 0.75rem 1rem;
`;

const TopContainer = styled.div`
  height: 5.75rem;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: space-between;
`;

const BottomContainer = styled.div`
  height: 1.5rem;

  display: flex;
  justify-content: space-around;
`;

const Decoration = styled.p<{ navindex: number }>`
  color: ${({ navindex }) => (navindex ? `${NAVYBLUE4}` : `${NAVYBLUE5}`)};
  font-size: 0.625rem;
  font-weight: 400;
`;

const OptionName = styled.p<{ navindex: number }>`
  font-size: ${({ navindex }) => (navindex ? "1rem" : "1.375rem")};
  font-weight: 700;
  line-height: 1.5rem;
`;

const Description = styled.p`
  color: ${GREY3};
  font-size: 0.8125rem;
  font-weight: 400;
  line-height: 165%;
`;

const PriceContainer = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 0.5rem;
`;

const Price = styled.p<{ navindex: number }>`
  font-size: ${({ navindex }) => (navindex === 3 ? "0.875rem" : "1rem")};
  font-weight: 500;
  line-height: 1.5rem;
  padding-top: ${({ navindex }) => (navindex === 3 ? "1rem" : "0rem")};
`;

const Tag = styled.button`
  width: 3.25rem;
  height: 1.25rem;
  font-size: 0.625rem;
`;

const DetailModelOptionContainer = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 0.5rem;
`;

const Name = styled.p`
  color: ${BLACK};
  font-size: 1rem;
  font-weight: 700;
  line-height: 1.5rem;
`;

const Ratio = styled.p`
  color: ${NAVYBLUE5};
  font-size: 0.625rem;
  font-weight: 400;
  line-height: 1rem;
`;

const Detail = styled.p`
  color: ${BLACK};
  font-size: 0.625rem;
  font-weight: 400;
  line-height: 150%;
  margin-top: 1rem;
`;

const ButtonContainer = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 0.75rem;
`;

export default OptionBox;
