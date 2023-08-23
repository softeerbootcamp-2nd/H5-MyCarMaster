import { styled } from "styled-components";

function AlertModal() {
  return (
    <Container>
      <MainText>구동 방식이 선택되지 않았습니다.</MainText>
      <SubText>구동 방식 선택 페이지로 이동합니다.</SubText>
    </Container>
  );
}

export default AlertModal;

const Container = styled.div`
  width: 100%;
  height: 60%;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  font-family: "HyundaiSansRegular";
  font-size: 1.5rem;
  font-style: normal;
  font-weight: 500;
`;

const MainText = styled.p`
  font-size: 1.5rem;
  font-style: normal;
  font-weight: 500;
`;

const SubText = styled.p`
  margin-top: 2rem;
  font-size: 1rem;
  font-style: normal;
  font-weight: 400;
`;
