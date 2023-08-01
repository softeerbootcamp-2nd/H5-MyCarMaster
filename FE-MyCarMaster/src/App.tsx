import Estimation from "./pages/Estimation";
import { ThemeProvider } from "styled-components";
import theme from "./styles/Theme";

function App() {
  return (
    <ThemeProvider theme={theme}>
      <Estimation />
    </ThemeProvider>
  );
}

export default App;
