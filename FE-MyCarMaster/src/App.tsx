import { styled } from "styled-components";
import Estimation from "./pages/Estimation";
import { ThemeProvider } from "styled-components";
import theme from "./styles/Theme";

function App() {
  return (
    <ThemeProvider theme={theme}>
      <Container>
        <Estimation />
      </Container>
    </ThemeProvider>
  );
}

const Container = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;

  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);

  width: 80rem;
  height: 45rem;
`;

export default App;
