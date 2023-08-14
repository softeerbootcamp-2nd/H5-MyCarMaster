import styled, { keyframes } from "styled-components";
import { Header } from "../components";
import dark_logo from "../assets/images/dark_logo.svg";
import NavigationList from "../components/common/NavigationList/NavigationList";
import CarRotation from "../components/common/CarRotation/CarRotation";
import Button from "../components/common/Button/Button";
import theme from "../styles/Theme";
import QuotationList from "../components/common/QuotationList/QuotationList";
import findCarmasterTooltip from "../assets/images/FindCarmasterTooltip.png";

function Quotation() {
  return (
    <Container>
      {/* <BlueBackground /> */}
      <Header logo={dark_logo} isHome={false} />
      <QuotationMain>
        <QuotationContent>
          <MainText>
            팰리세이드와 함께
            <br />
            드라이브 떠나볼까요?
          </MainText>
          <SubText>카마스터 찾기를 통해 구매 상담을 할 수 있어요</SubText>
          <AnimationContainer>
            <CarRotation $isQuotation={true} />
          </AnimationContainer>
        </QuotationContent>
        <NavigationList />
      </QuotationMain>
      <QuotationFooter>
        <PriceContainer>
          <Price>예상 가격</Price>
          <SumPrice>38,960,000 원</SumPrice>
        </PriceContainer>
        <ButtonContainer>
          <Button
            $x={9.625}
            $y={2.25}
            $backgroundcolor={`${theme.colors.WHITE}`}
            $textcolor={`${theme.colors.NAVYBLUE5}`}
            $bordercolor={`${theme.colors.NAVYBLUE5}`}
            text="공유하기"
            handleClick={() => console.log("공유!")}
          />
          <Button
            $x={9.625}
            $y={2.25}
            $backgroundcolor={`${theme.colors.NAVYBLUE5}`}
            $textcolor={`${theme.colors.WHITE}`}
            $bordercolor={`${theme.colors.NAVYBLUE5}`}
            text="카마스터 찾기"
            handleClick={() => console.log("카마스터 찾기!")}
          />
        </ButtonContainer>
      </QuotationFooter>
      <QuotationList />
      <ToolTip src={findCarmasterTooltip} />
    </Container>
  );
}

const appearAnimation = keyframes`
  0%{
    opacity: 0;
    transform: translateY(-50px);
  }
  100%{
    opacity: 1;
    transform: translateY(0);
  }
`;

const boundAnimation = keyframes`
  0%, 100%{
    transform: translateY(0);
  }
  50%{
    transform: translateY(-20px);
  }
`;

const fadeoutAnimation = keyframes`
  from {
    opacity: 1;
  }
  to{
    opacity: 0;
  }
`;

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 80rem;
  height: 45rem;
  margin: 0 8rem;
`;

// const BlueBackground = styled.div`
//   position: absolute;
//   top: 0;
//   left: 0;
//   width: 100%;
//   height: 600px;
//   margin: 0;
//   background: linear-gradient(180deg, #dde4f8 0%, rgba(231, 235, 246, 0) 100%);
//   z-index: -1;
// `;

const QuotationMain = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: flex-start;
  height: 32rem;
`;

const QuotationContent = styled.div`
  width: 51.5rem;

  display: flex;
  flex-direction: column;
  align-items: flex-start;
  gap: 1rem;
`;

const MainText = styled.p`
  font-family: "HyundaiSansBold";
  font-size: 2rem;
  font-style: normal;
  font-weight: 500;
  line-height: 2.5rem; /* 125% */
`;

const SubText = styled.p`
  font-family: "HyundaiSansRegular";
  font-size: 1rem;
  font-style: normal;
  font-weight: 400;
  line-height: 1.5rem; /* 150% */
`;

const AnimationContainer = styled.div`
  animation: ${appearAnimation} 2s ease-in-out;
  width: 100%;
`;

const QuotationFooter = styled.div`
  min-height: 4.75rem;
  display: flex;
  justify-content: space-between;
`;

const PriceContainer = styled.div`
  width: 51.5rem;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: flex-end;
`;

const Price = styled.p`
  font-family: "HyundaiSansRegular";
  font-size: 1rem;
  font-style: normal;
  font-weight: 700;
  line-height: 1.5rem;
`;

const SumPrice = styled.p`
  font-family: "HyundaiSansMedium";
  font-size: 1.75rem;
  font-style: normal;
  font-weight: 500;
  line-height: 2.25rem;
`;

const ButtonContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
`;

const ToolTip = styled.img`
  width: 12.4375rem;
  position: absolute;
  top: 510px;
  left: 955px;

  animation: ${boundAnimation} 1s ease-in-out 5 forwards,
    ${fadeoutAnimation} 1.5s ease-in-out 5s forwards;
  opacity: 1;
`;

export default Quotation;
