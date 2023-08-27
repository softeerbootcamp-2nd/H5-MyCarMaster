import styled from "styled-components";
import { Flex, Image } from "@styles/core.style";

export const FlexBlur = styled(Flex)`
  width: 100%;
  height: 100%;
  position: absolute;

  //center
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: rgba(255, 255, 255, 0.8);
`;

export const LoaderSpinner = styled.div`
  // size
  width: 4rem;
  height: 4rem;
  z-index: 999;
`;

export const LoaderImage = styled(Image)`
  animation: loading 1s linear infinite;
  @keyframes loading {
    0% {
      transform: rotate(0deg);
    }
    100% {
      transform: rotate(360deg);
    }
  }
`;
