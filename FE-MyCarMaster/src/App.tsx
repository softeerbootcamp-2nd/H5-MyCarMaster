import React, { createElement } from "react";
import { styled } from "styled-components";
import { Routes, Route } from "react-router-dom";
import { ThemeProvider } from "styled-components";
import theme from "./styles/Theme";
import Home from "./pages/Home";
import Estimation from "./pages/Estimation";
import Quotation from "./pages/Quotation";
import { ModelProvider } from "./contexts/ModelContext";
import { TrimProvider } from "./contexts/TrimContext";

const AppProvider = ({
  providers,
  children,
}: {
  providers: any[];
  children: React.ReactNode;
}) =>
  providers.reduce(
    (prev: React.ReactNode, context: any) =>
      createElement(context, {
        children: prev,
      }),
    children
  );

function App() {
  return (
    <ThemeProvider theme={theme}>
      <AppProvider providers={[ModelProvider, TrimProvider]}>
        <Container>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/estimation" element={<Estimation />} />
            <Route path="/quotation" element={<Quotation />} />
          </Routes>
        </Container>
      </AppProvider>
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
