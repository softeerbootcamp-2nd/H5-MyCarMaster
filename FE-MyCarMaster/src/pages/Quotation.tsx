import styled, { keyframes } from "styled-components";
import { Header } from "../components";
import dark_logo from "../assets/images/dark_logo.svg";
import NavigationList from "../components/common/NavigationList/NavigationList";
import CarRotation from "../components/common/CarRotation/CarRotation";
import Button from "../components/common/Button/Button";
import theme from "../styles/Theme";
import QuotationList from "../components/common/QuotationList/QuotationList";
import findCarmasterTooltip from "../assets/images/FindCarmasterTooltip.png";
import { useQuotationState } from "../contexts/QuotationContext";
import { Fragment, useState } from "react";
import { Modals } from "../components/common/Modals/Modals";
import { ModalType } from "../constants/Modal.constants";
import { post } from "../utils/fetch";
import { QuotationType } from "../types/quotation.types";

function Quotation() {
  const { trimQuotation, detailQuotation, carPaintQuotation, optionQuotation } =
    useQuotationState();
  const [confirm, setConfirm] = useState<boolean>(false);
  const [isConfirmModalOpen, setIsConfirmModalOpen] = useState<boolean>(false);
  const [estimateId, setEstimateId] = useState<string>();

  const getTotalPrice = () => {
    let totalPrice = 0;
    totalPrice =
      trimQuotation.trimQuotation.price +
      detailQuotation.bodyTypeQuotation.price +
      detailQuotation.wheelDriveQuotation.price +
      detailQuotation.engineQuotation.price +
      carPaintQuotation.exteriorColorQuotation.price +
      carPaintQuotation.interiorColorQuotation.price;
    totalPrice += optionQuotation.selectedQuotation.reduce(
      (acc, item) => acc + item.price,
      0
    );
    return totalPrice;
  };

  const getOptionPrice = (option: Array<QuotationType>) => {
    const totalPrice = option.reduce((acc, cur) => {
      return acc + cur.price;
    }, 0);
    return totalPrice;
  };

  const confirmHandler = () => {
    const SERVER_URL = import.meta.env.VITE_APP_SERVER_URL;

    const dataToSubmit = {
      modelId: 1,
      trimId: trimQuotation.trimQuotation.id,
      trimPrice: trimQuotation.trimQuotation.price,
      engineId: detailQuotation.engineQuotation.id,
      enginePrice: detailQuotation.engineQuotation.price,
      wheelDriveId: detailQuotation.wheelDriveQuotation.id,
      wheelDrivePrice: detailQuotation.wheelDriveQuotation.price,
      bodyTypeId: detailQuotation.bodyTypeQuotation.id,
      bodyTypePrice: detailQuotation.bodyTypeQuotation.price,
      exteriorColorId: carPaintQuotation.exteriorColorQuotation.id,
      exteriorColorPrice: carPaintQuotation.exteriorColorQuotation.price,
      interiorColorId: carPaintQuotation.interiorColorQuotation.id,
      interiorColorPrice: carPaintQuotation.interiorColorQuotation.price,
      selectOptions: optionQuotation.selectedQuotation.map((option) => {
        const { id, price } = option;
        return { id, price };
      }),
      selectOptionPrice: getOptionPrice(optionQuotation.selectedQuotation),
      considerOptions: optionQuotation.consideredQuotation.map((option) => {
        const { id, price } = option;
        return { id, price };
      }),
      totalPrice: getTotalPrice(),
    };

    post(`${SERVER_URL}/estimates`, dataToSubmit).then((response) => {
      setEstimateId(response.result.estimateUuid);
    });

    setConfirm(true);
  };

  return (
    <Fragment>
      <Container>
        <BlueBackground />
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
          <NavigationList confirm={confirm} />
        </QuotationMain>
        <QuotationFooter>
          <PriceContainer>
            <Price>예상 가격</Price>
            <SumPrice>{getTotalPrice().toLocaleString("ko-KR")}원</SumPrice>
          </PriceContainer>
          <ButtonContainer>
            <ToolTip src={findCarmasterTooltip} />
            <Button
              $x={12}
              $y={2.25}
              $backgroundcolor={`${theme.colors.WHITE}`}
              $textcolor={`${theme.colors.NAVYBLUE5}`}
              $bordercolor={`${theme.colors.NAVYBLUE5}`}
              text="확정하기"
              handleClick={() => setIsConfirmModalOpen(true)}
            />
            <Button
              $x={12}
              $y={2.25}
              $backgroundcolor={`${theme.colors.NAVYBLUE5}`}
              $textcolor={`${theme.colors.WHITE}`}
              $bordercolor={`${theme.colors.NAVYBLUE5}`}
              text="카마스터 찾기"
              handleClick={() => console.log("카마스터 찾기!")}
            />
          </ButtonContainer>
        </QuotationFooter>
        <QuotationList confirm={confirm} />
      </Container>
      {isConfirmModalOpen && (
        <Modals
          type={ModalType.CONFIRM}
          setIsOpen={setIsConfirmModalOpen}
          onClick={confirmHandler}
        />
      )}
    </Fragment>
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
  width: 100%;
  height: 100%;
`;

const BlueBackground = styled.div`
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 37.5rem;
  margin: 0;
  background: linear-gradient(180deg, #dde4f8 0%, rgba(231, 235, 246, 0) 100%);
  z-index: -1;
`;

const QuotationMain = styled.div`
  width: 100%;
  height: 100%;

  display: flex;
  flex-direction: row;
  justify-content: center;
  gap: 10rem;
`;

const QuotationContent = styled.div`
  width: 59.5rem;

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
  justify-content: center;
  gap: 10rem;
`;

const PriceContainer = styled.div`
  width: 59.5rem;
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
  width: 12rem;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  position: relative;
`;

const ToolTip = styled.img`
  width: 12.4375rem;
  position: absolute;
  top: -80%;
  left: 0;

  animation: ${boundAnimation} 1s ease-in-out 5 forwards,
    ${fadeoutAnimation} 1.5s ease-in-out 5s forwards;
  opacity: 1;
`;

export default Quotation;
