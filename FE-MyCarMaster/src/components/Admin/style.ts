import styled from "styled-components";

import { Flex, Text, TextProps } from "@styles/core.style";

export const QuotationBox = styled(Flex)`
  width: 100%;
  height: 100%;
`;
export const ClientBox = styled(Flex)`
  width: 20rem;
  height: 10rem;
  background-color: ${(props) => props.theme.colors.WHITE};
  padding: 2.5rem;
  margin: 2rem;

  border-radius: 1rem;
  box-shadow: 0 0 0.5rem 0.1rem rgba(0, 0, 0, 0.1);
  animation: showAdminText 0.1s ease-in-out;

  @keyframes showAdminText {
    0% {
      opacity: 0;
    }
    100% {
      opacity: 1;
    }
  }

  &:hover {
    cursor: pointer;
    box-shadow: 0 0 0.5rem 0.1rem rgba(0, 0, 0, 0.3);
  }
`;

interface ClientTextProps extends TextProps {
  $isHover?: boolean | null;
}

export const ClientBoxText = styled(Text)<ClientTextProps>`
  text-align: left;
  width: 100%;

  // underline
  &:hover {
    text-decoration: ${(props) => (props.$isHover ? "underline" : "none")};
    cursor: ${(props) => (props.$isHover ? "pointer" : "default")};
  }
`;

export const QuotationLookBox = styled(Flex)`
  width: 100%;
  height: 100%;
`;

export const QuotationIFrame = styled.iframe`
  width: 100%;
  height: 100%;
  border: none;
  border-radius: 1rem;
  box-shadow: 0 0 0.5rem 0.1rem rgba(0, 0, 0, 0.1);
`;

export const ClientQuotationDetailBox = styled(Flex)`
  width: 20rem;
  height: auto;
  position: absolute;
  gap: 1rem;
  top: 0;
  left: 0;
  background-color: ${(props) => props.theme.colors.WHITE};
  padding: 2.5rem;
  border-radius: 0 0 2rem 0;

  &:hover {
    box-shadow: 0 0 0.5rem 0.1rem rgba(0, 0, 0, 0.3);
  }
`;
