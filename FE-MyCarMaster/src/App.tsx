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
  height: 100vh;
`;

export default App;
