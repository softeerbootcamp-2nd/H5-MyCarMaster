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
      {!isHome && (
        <>
          <ModelSelector>
            <ModelName>{modelName}</ModelName>
            <ModelButton src={ArrowBottom} />
          </ModelSelector>
        </>
      )}
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 25%;
  margin: 0 3%;
  gap: 60rem;
`;

const Img = styled.img`
  width: 20%;
  min-width: 12rem;
  max-width: 16rem;
`;

const ModelSelector = styled.div`
  display: flex;
  align-items: center;
  gap: 0.5rem;
`;

const ModelName = styled.div`
  font-size: 1.2rem;
  font-weight: 500;
  color: ${({ theme }) => theme.colors.NAVYBLUE5};
`;

const ModelButton = styled.img`
  cursor: pointer;
`;

export default Header;
