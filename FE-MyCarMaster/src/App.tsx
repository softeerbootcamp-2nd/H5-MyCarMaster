import { styled } from "styled-components";
import Estimation from "./pages/Estimation";

function App() {
  return (
    <Container>
      <Estimation />
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
`;

export default App;
