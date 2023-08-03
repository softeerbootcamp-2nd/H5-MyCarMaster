import { styled } from "styled-components";
import {
  BLACK,
  GREY2,
  GREY3,
  NAVYBLUE4,
  NAVYBLUE5,
} from "../../../styles/Color";

function OptionBox() {
  const navindex = 0;
  return (
    <Container>
      <TopContainer>
        {navindex === 0 || navindex === 3 ? (
          <>
            <Decoration navindex={navindex}>New</Decoration>
            <OptionName navindex={navindex}>Le Blanc</OptionName>
            {navindex === 0 ? (
              <Description>
                실용적이고 기본적인 기능을 갖춘 베이직 트림
              </Description>
            ) : (
              <></>
            )}
            <PriceContainer>
              <Price navindex={navindex}>99,999,999 원</Price>
              {navindex === 0 ? <Tag>기본 제공</Tag> : <></>}
            </PriceContainer>
          </>
        ) : (
          <>
            <DetailModelOptionContainer>
              <Name>가솔린 3.8</Name>
              <Ratio>구매자 22%가 선택</Ratio>
            </DetailModelOptionContainer>
            <Description>
              우수한 가속 성능으로 안정적이고 엔진의 진동이 적어 조용한
              드라이빙이 가능합니다.
            </Description>
          </>
        )}
      </TopContainer>
      <BottomContainer>
        {navindex === 0 ? (
          // 내게 맞는 트림 찾기의 경우 자세히 보기는 없어야함.
          <Detail>자세히보기 &gt;</Detail>
        ) : navindex === 1 ? (
          <Price navindex={navindex}>+ 999,999 원</Price>
        ) : (
          <ButtonContainer>
            <button>고민해보기</button>
            <button>추가하기</button>
          </ButtonContainer>
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
  /* justify-content: space-between; */
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
