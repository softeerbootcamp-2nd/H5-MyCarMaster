import React, { createElement } from "react";
import { Flex } from "@styles/core.style";
import { Routes, Route, useNavigate } from "react-router-dom";
import { ThemeProvider } from "styled-components";
import { ModelProvider } from "@contexts/ModelContext";
import { TrimProvider } from "@contexts/TrimContext";
import { DetailProvider } from "@contexts/DetailContext";
import { CarPaintProvider } from "@contexts/CarPaintContext";
import { OptionProvider } from "@contexts/OptionContext";
import { QuotationProvider } from "@contexts/QuotationContext";
import theme from "@styles/Theme";

import {
  Home,
  Estimation,
  Quotation,
  WrittenQuotation,
  ConsultComplete,
} from "@pages/index";
import ErrorBoundary from "./components/common/ErrorBoundary/ErrorBoundary";

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
  const navigate = useNavigate();
  const handleClick = () => {
    navigate("/");
    window.location.reload();
  };
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
        <ErrorBoundary handleClick={handleClick}>
          <Flex>
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/estimation" element={<Estimation />} />
              <Route path="/quotation" element={<Quotation />} />
              <Route
                path="/estimates/:estimateId"
                element={<WrittenQuotation />}
              />
              <Route path="/consult-complete" element={<ConsultComplete />} />
            </Routes>
          </Flex>
        </ErrorBoundary>
      </AppProvider>
    </ThemeProvider>
  );
}

export default App;
