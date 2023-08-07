import styled from "styled-components";
import SelectListWrapper from "./SelectListWrapper";
import Button from "../common/Button/Button";
import theme from "../../styles/Theme";
import {
  useQuotationState,
  useQuotationDispatch,
} from "../../contexts/QuotationContext";
import indexNameSwitching from "../../utils/indexNameSwitching";

function Footer() {
  const { navigationId, isFirst } = useQuotationState();
  const quotationDispatch = useQuotationDispatch();
  const name = indexNameSwitching(navigationId) as string;

  const prevButtonHandler = () => {
    const navigationIndex = navigationId - 1;
    quotationDispatch({
      type: "NAVIGATE",
      payload: {
        navigationId: navigationIndex,
        isFirst: {
          ...isFirst,
          [navigationIndex]: false,
        },
      },
    });
  };

  const nextButtonHandler = () => {
    const navigationIndex = navigationId + 1;
    quotationDispatch({
      type: "NAVIGATE",
      payload: {
        navigationId: navigationIndex,
        isFirst: {
          ...isFirst,
          [navigationIndex]: false,
        },
      },
    });
  };

  return (
    <Container>
      <SelectListWrapper />
      <RightContainer>
        <HeightFittingContainer>
          <TextContainer>
            {name && (
              <>
                <HeadText>{name} 선택</HeadText>
                <DescriptionText>원하는 {name}을 선택해주세요.</DescriptionText>
              </>
            )}
          </TextContainer>
          <ButtonContainer>
            {navigationId !== 0 && (
              <Button
                $x={9.625}
                $y={2.25}
                $backgroundcolor={`${theme.colors.White}`}
                $textcolor={`${theme.colors.NavyBlue5}`}
                $bordercolor={`${theme.colors.NavyBlue5}`}
                text={"이전"}
                handleClick={prevButtonHandler}
              />
            )}

            <Button
              $x={9.625}
              $y={2.25}
              $backgroundcolor={`${theme.colors.NavyBlue5}`}
              $textcolor={`${theme.colors.White}`}
              $bordercolor={`${theme.colors.NavyBlue5}`}
              text={navigationId === 7 ? "견적서 완성" : "다음"}
              handleClick={nextButtonHandler}
            />
          </ButtonContainer>
        </HeightFittingContainer>
      </RightContainer>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  height: 16rem;
`;

const RightContainer = styled.div`
  width: 9.625rem;

  display: flex;
  flex-direction: column;
`;

const TextContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
`;

const HeadText = styled.p`
  font-size: 1rem;
  font-style: normal;
  font-weight: 700;
  line-height: 1.5rem;
`;

const DescriptionText = styled.p`
  font-size: 0.8125rem;
  font-style: normal;
  font-weight: 400;
  line-height: 165%;
`;

const ButtonContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 0.25rem;
`;

const HeightFittingContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  height: 10.25rem;
`;

export default Footer;
