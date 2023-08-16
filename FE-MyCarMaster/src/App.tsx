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
import { ModalProvider } from "./contexts/ModalContext";
import theme from "./styles/Theme";
import Home from "./pages/Home";
import Estimation from "./pages/Estimation";
import Quotation from "./pages/Quotation";
import { ModalPortal } from "./components/common/ModalPortal/ModalPortal";
import { Modals } from "./components/common/Modals/Modals";

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
        <ModalProvider>
          <ModalPortal>
            <Modals />
          </ModalPortal>
          <Container>
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/estimation" element={<Estimation />} />
              <Route path="/quotation" element={<Quotation />} />
            </Routes>
          </Container>
        </ModalProvider>
      </AppProvider>
    </ThemeProvider>
  );
}

const Container = styled.div`
  display: flex;
  align-items: center;
  width: 100%;
  height: 100%;
`;

export default App;
