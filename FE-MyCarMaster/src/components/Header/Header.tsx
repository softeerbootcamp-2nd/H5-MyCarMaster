import styled from "styled-components";
import ArrowBottom from "../../assets/icons/ArrowBottom.svg";
import { useModelState } from "../../contexts/ModelContext";

type HeaderProps = {
  isHome: boolean;
  logo: string;
};

function Header({ isHome, logo }: HeaderProps) {
  const { modelName } = useModelState();
  return (
    <Container>
      <Img src={logo} />
      {isHome ? (
        <></>
      ) : (
        <ModelSelector>
          <ModelName>{modelName}</ModelName>
          <ModelButton src={ArrowBottom} />
        </ModelSelector>
      )}
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 4rem;
`;

const Img = styled.img`
  width: 25%;
`;

const ModelSelector = styled.div`
  display: flex;
  align-items: center;
  gap: 0.5rem;
`;

const ModelName = styled.div``;

const ModelButton = styled.img`
  cursor: pointer;
`;

export default Header;
