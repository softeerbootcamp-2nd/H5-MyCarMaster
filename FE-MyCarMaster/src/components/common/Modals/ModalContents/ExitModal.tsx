import { styled } from "styled-components";

function ExitModal() {
  return <Container>마이 카마스터를 종료하시겠습니까?</Container>;
}

export default ExitModal;

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