import { styled } from "styled-components";

function ChangeTrimModal({ isSearch }: { isSearch?: boolean }) {
  return (
    <Container>
      <MainText>
        {isSearch ? "이전 선택한 트림과 다릅니다." : "트림을 변경하시겠습니까?"}
      </MainText>
      <SubText>
        {isSearch
          ? "선택했던 옵션을 초기화하고 새롭게 저장됩니다."
          : "현재까지의 변경사항은 저장되지 않습니다."}
      </SubText>
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
`;

const MainText = styled.p`
  ${(props) => props.theme.fonts.Regular17};
`;

const SubText = styled.p`
  ${(props) => props.theme.fonts.Regular10};
  color: ${(props) => props.theme.colors.SMOOTH_RED};
`;
