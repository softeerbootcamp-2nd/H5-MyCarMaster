import { styled } from "styled-components";

function AlertModal({ text }: { text: string[] }) {
  return (
    <Container>
      <MainText>{text[0]}</MainText>
      <SubText>{text[1] && text[1]}</SubText>
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

  ${(props) => props.theme.fonts.Regular15};
`;

const MainText = styled.p`
  ${(props) => props.theme.fonts.Regular15};
`;

const SubText = styled.p`
  margin-top: 2rem;
  ${(props) => props.theme.fonts.Regular10};
`;
