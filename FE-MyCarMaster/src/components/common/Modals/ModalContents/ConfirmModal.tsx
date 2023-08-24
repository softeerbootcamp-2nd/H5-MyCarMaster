import { styled } from "styled-components";

function ConfirmModal() {
  return (
    <Container>
      견적서를 확정하시겠습니까?
      <P> 확정하시면 선택하신 것을 변경할 수 없습니다. </P>
    </Container>
  );
}

export default ConfirmModal;

const Container = styled.div`
  width: 100%;
  height: 60%;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  ${(props) => props.theme.fonts.Bold20};
`;

const P = styled.p`
  margin: 1rem;
  ${(props) => props.theme.fonts.Medium15};
  color: ${(props) => props.theme.colors.SMOOTH_RED};
`;
