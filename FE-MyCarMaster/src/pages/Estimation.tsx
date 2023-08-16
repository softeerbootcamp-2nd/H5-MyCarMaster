import { Footer, Header, MainView } from "../components";
import { styled } from "styled-components";
import dark_logo from "../assets/images/dark_logo.svg";

function Estimation() {
  return (
    <Container>
      <Header logo={dark_logo} isHome={false} />
      <MainView />
      <Footer />
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
`;

export default Estimation;
