import React, { createElement } from "react";
import { styled } from "styled-components";
import { Routes, Route } from "react-router-dom";
import { ThemeProvider } from "styled-components";
import { ModelProvider } from "./contexts/ModelContext";
import { TrimProvider } from "./contexts/TrimContext";
import { DetailProvider } from "./contexts/DetailContext";
import { CarPaintProvider } from "./contexts/CarPaintContext";
import { OptionProvider } from "./contexts/OptionContext";
import { QuotationProvider } from "./contexts/QuotationContext";
import theme from "./styles/Theme";
import Home from "./pages/Home";
import Estimation from "./pages/Estimation";
import Quotation from "./pages/Quotation";

type ContextProvider = React.ComponentType<{ children: React.ReactNode }>;

const AppProvider = ({
  providers,
  children,
}: {
  providers: ContextProvider[];
  children: React.ReactNode;
}) =>
  providers.reduce(
    (prev: React.ReactNode, context: ContextProvider) =>
      createElement(context, {
        children: prev,
      }),
    children
  );

function App() {
  const { pathname } = window.location;
  return (
    <ThemeProvider theme={theme}>
      <AppProvider
        providers={[
          ModelProvider,
          TrimProvider,
          DetailProvider,
          CarPaintProvider,
          OptionProvider,
          QuotationProvider,
        ]}
      >
        <Container>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/estimation" element={<Estimation />} />
            <Route path="/quotation" element={<Quotation />} />
          </Routes>
        </Container>
        {pathname === "/estimation" && <Filler />}
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
`;

const Filler = styled.div`
  position: absolute;
  top: 64%;
  width: 100%;
  height: 36%;
  background-color: #f2f2f2;
  z-index: -1;
`;

export default App;
