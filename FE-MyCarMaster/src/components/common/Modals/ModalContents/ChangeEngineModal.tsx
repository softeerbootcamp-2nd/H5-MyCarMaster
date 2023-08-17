import { styled } from "styled-components";
import theme from "../../../../styles/Theme";
import { UnselectableOptionProps } from "../../../../types/options.types";

function ChangeEngineModal({ id, name, price }: UnselectableOptionProps) {
  return (
    <Container key={id}>
      <MainText>엔진을 변경하시면</MainText>
      <MainText>선택하신 아래 옵션이 해제돼요.</MainText>
      <OptionContainer>
        <OptionName>{name}</OptionName>
        <OptionPrice>+{price.toLocaleString("ko-KR")}원</OptionPrice>
      </OptionContainer>
    </Container>
  );
}

export default ChangeEngineModal;

const Container = styled.div`
  width: 100%;
  height: 60%;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 1rem;

  font-family: "HyundaiSansRegular";
`;

const MainText = styled.p`
  font-size: 1.5rem;
  font-style: normal;
  font-weight: 500;
`;

const OptionContainer = styled.div`
  width: 15rem;
  height: 7rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
  gap: 1.5rem;

  padding: 0.75rem 1.5rem;
  margin-top: 2rem;

  border: 1px solid ${theme.colors.GREY2};
`;

const OptionName = styled.p`
  font-size: 1rem;
  font-style: normal;
  font-weight: 700;
`;

const OptionPrice = styled.p`
  font-size: 0.875rem;
  font-style: normal;
  font-weight: 500;
`;
