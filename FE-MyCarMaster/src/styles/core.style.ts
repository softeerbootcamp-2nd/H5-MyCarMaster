import styled from "styled-components";

interface FlexProps {
  $flexDirection?: "row" | "column";
  $width?: string;
  $height?: string;
  $padding?: string;
  $margin?: string;
  $justifyContent?:
    | "center"
    | "flex-start"
    | "flex-end"
    | "space-between"
    | "space-around";
  $alignItems?: string;
}

export const Flex = styled.div<FlexProps>`
  display: flex;
  flex-direction: ${(props) => props.$flexDirection || "row"};
  width: ${(props) => props.$width || "100%"};
  height: ${(props) => props.$height || "100%"};
  padding: ${(props) => props.$padding || "0"};
  margin: ${(props) => props.$margin || "0"};
  justify-content: ${(props) => props.$justifyContent || "flex-start"};
  align-items: ${(props) => props.$alignItems || "center"};
`;

interface TextProps {
  $fontSize?: string;
  $fontWeight?: string;
  $color?: string;
  $textAlign?: string;
}

export const Text = styled.p<TextProps>`
  font-size: ${(props) => props.$fontSize || "1rem"};
  font-weight: ${(props) => props.$fontWeight || "normal"};
  color: ${(props) => props.$color || "#000"};
  text-align: ${(props) => props.$textAlign || "left"};
`;
