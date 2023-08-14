import styled from "styled-components";
import SelectListWrapper from "./SelectListWrapper";
import Button from "../common/Button/Button";
import theme from "../../styles/Theme";
import FoldScreen from "./FoldScreen";
import {
  useQuotationState,
  useQuotationDispatch,
} from "../../contexts/QuotationContext";
import indexNameSwitching from "../../utils/indexNameSwitching";
import { useNavigate } from "react-router-dom";

function Footer() {
  const { navigationId, isFirst } = useQuotationState();
  const quotationDispatch = useQuotationDispatch();
  const name = indexNameSwitching(navigationId) as string;
  const navigate = useNavigate();

  const buttonHandler = (cal: number) => {
    const navigationIndex = navigationId + cal;
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

  const navigateQuotationHandler = () => {
    // validation api code
    navigate("/quotation");
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
                $backgroundcolor={`${theme.colors.WHITE}`}
                $textcolor={`${theme.colors.NAVYBLUE5}`}
                $bordercolor={`${theme.colors.NAVYBLUE5}`}
                text={"이전"}
                handleClick={() => buttonHandler(-1)}
              />
            )}

            <Button
              $x={9.625}
              $y={2.25}
              $backgroundcolor={`${theme.colors.NAVYBLUE5}`}
              $textcolor={`${theme.colors.WHITE}`}
              $bordercolor={`${theme.colors.NAVYBLUE5}`}
              text={navigationId === 6 ? "견적서 완성" : "다음"}
              handleClick={
                navigationId === 6
                  ? navigateQuotationHandler
                  : () => buttonHandler(1)
              }
            />
          </ButtonContainer>
        </HeightFittingContainer>
      </RightContainer>
      <FoldScreen text={"내게 맞는 트림 찾기"} $switch={"searchTrim"} />
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  position: relative;
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
