import styled from "styled-components";
import { GREY1 } from "../../styles/Color";
import InnerColorBox from "../common/ColorBox/InnerColorBox";
import Button from "../common/Button/Button";
import theme from "../../styles/Theme";

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

  const prevButtonHandler = () => {};

  const nextButtonHandler = () => {};

  return (
    <Container>
      {/* 각 단계에 맞는 상품 아이템이 들어감 */}
      <LeftContainer>
        <InnerColorBox {...InnerColorProps} />
        <InnerColorBox {...InnerColorProps} />
        <InnerColorBox {...InnerColorProps} />
        <InnerColorBox {...InnerColorProps} />
      </LeftContainer>
      <RightContainer>
        <HeightFittingContainer>
          <TextContainer>
            <HeadText>트림 선택</HeadText>
            <DescriptionText>원하는 트림을 선택해주세요.</DescriptionText>
          </TextContainer>
          <ButtonContainer>
            {/* {navigationIndex === 0 ? (
              <></>
            ) : ( */}
            <Button
              $x={9.625}
              $y={2.25}
              $backgroundcolor={`${theme.colors.White}`}
              $textcolor={`${theme.colors.NavyBlue5}`}
              $bordercolor={`${theme.colors.NavyBlue5}`}
              text={"이전"}
              handleClick={prevButtonHandler}
            />
            {/* )} */}
            <Button
              $x={9.625}
              $y={2.25}
              $backgroundcolor={`${theme.colors.NavyBlue5}`}
              $textcolor={`${theme.colors.White}`}
              $bordercolor={`${theme.colors.NavyBlue5}`}
              text={"다음"}
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
  background-color: ${GREY1};
`;

const LeftContainer = styled.div`
  display: flex;
  flex-direction: row;
  width: 51.5rem;
  gap: 0.5rem;
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
