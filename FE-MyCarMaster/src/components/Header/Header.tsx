import styled from "styled-components";
import ArrowBottom from "../../assets/icons/ArrowBottom.svg";

type HeaderProps = {
  isHome: boolean;
  logo: string;
};

function Header({ isHome, logo }: HeaderProps) {
  return (
    <Container>
      <Img src={logo} />
      {isHome ? (
        <></>
      ) : (
        <ModelSelector>
          <ModelName>Palisade</ModelName>
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

const Img = styled.img``;

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
