import styled from "styled-components";
import { Flex, FlexProps } from "@styles/core.style";

export const OptionFlex = styled(Flex)<FlexProps>`
  overflow: hidden;
  flex-wrap: nowrap;
  overflow-x: scroll;
  &::-webkit-scrollbar {
    display: none;
  }
`;

export const BlurFlex = styled(Flex)<FlexProps>`
  // 미구현
`;

export const ScrollButton = styled.button<{ $direction: "left" | "right" }>`
  width: 5%;
  height: 12.25rem;
  position: absolute;
  align-items: flex-start;
  left: ${(props) => (props.$direction === "left" ? "-5%" : "100%")};
`;

export { Flex } from "@styles/core.style";
