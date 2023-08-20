import { styled } from "styled-components";

function ChangeTrimModal() {
  return (
    <Container>
      <MainText>트림을 변경하시겠습니까?</MainText>
      <SubText>현재까지의 변경사항은 저장되지 않습니다.</SubText>
    </Container>
  );
}

export default ChangeTrimModal;

const Container = styled.div`
  width: 100%;
  height: 60%;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 2rem;

  font-family: "HyundaiSansRegular";
`;

const MainText = styled.p`
  font-size: 1.5rem;
  font-style: normal;
  font-weight: 500;
`;

const SubText = styled.p`
  font-size: 1rem;
  font-style: normal;
  font-weight: 400;
`;