import styled from "styled-components";
import { Flex, Image } from "@styles/core.style";

export const FlexBlur = styled(Flex)`
  width: 100%;
  height: 100%;
`;

export const LoaderSpinner = styled.div`
  // size
  width: 4rem;
  height: 4rem;
  z-index: 999;
  animation: loading 1s linear infinite;
  @keyframes loading {
    0% {
      transform: translate(-50%, -50%) rotate(0deg);
    }
    100% {
      transform: translate(-50%, -50%) rotate(360deg);
    }
  }
`;

export const LoaderImage = styled(Image)``;
