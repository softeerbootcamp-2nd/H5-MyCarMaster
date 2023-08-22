import styled from "styled-components";

export interface FlexProps {
  $flexDirection?: "row" | "column";
  $width?: string;
  $height?: string;
  $padding?: string;
  $position?: "relative" | "absolute";
  $margin?: string;
  $justifyContent?:
    | "center"
    | "flex-start"
    | "flex-end"
    | "space-between"
    | "space-around";
  $alignItems?: "center" | "flex-start" | "flex-end";
  $gap?: string;
  $backgroundColor?: string;
  $frame?: string;
  $wrap?: boolean;
  $overflow?: "hidden" | "scroll" | "auto";
}

export const Flex = styled.div<FlexProps>`
  display: flex;
  flex-direction: ${(props) => props.$flexDirection || "row"};
  width: ${(props) => props.$width || "100%"};
  height: ${(props) => props.$height || "100%"};
  padding: ${(props) => props.$padding || "0"};
  margin: ${(props) => props.$margin || "0"};
  justify-content: ${(props) => props.$justifyContent || "flex-start"};
  align-items: ${(props) => props.$alignItems || "normal"};
  gap: ${(props) => props.$gap || "0"};
  background-color: ${(props) => props.$backgroundColor || "transparent"};
  flex-wrap: ${(props) => (props.$wrap ? "wrap" : "nowrap")};
  overflow: ${(props) => props.$overflow || "visible"};
  position: ${(props) => props.$position || "static"};
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

interface ImageProps {
  $width?: string;
  $height?: string;
  $margin?: string;
  $padding?: string;
  $objectFit?: "cover" | "contain" | "fill";
  $shadow?: string;
  $borderRadius?: string;
  $onHover?: boolean;
}

export const Image = styled.img<ImageProps>`
  width: ${(props) => props.$width || "100%"};
  height: ${(props) => props.$height || "100%"};
  object-fit: ${(props) => props.$objectFit || "cover"};
  object-position: center;
  margin: ${(props) => props.$margin || "auto"};
  padding: ${(props) => props.$padding || "0"};
  box-shadow: ${(props) => props.$shadow || "none"};

  user-select: none;
  -webkit-user-drag: none;
  -webkit-user-select: none;

  ${(props) =>
    props.$onHover &&
    `
    &:hover {
      animation: 0.5s hover_image ease-in-out forwards;

      @keyframes hover_image {
        0% {
          border-radius: ${props.$borderRadius || "0"};
        }
        100% {
          border-radius: 0;
        }
      }
    }
    
    &:not(:hover) {
      animation: 1.5s hover_image_back ease-in-out forwards;

      @keyframes hover_image_back {
        0% {
          border-radius: 0;
        }
        100% {
          border-radius: ${props.$borderRadius || "0"};
        }
      }
    }
    `}
  @keyframes hover_image_full {
    0% {
      border-radius: 0;
    }
    100% {
      width: 100%;
    }
  }

  @keyframes hover_image_full_back {
    0% {
      width: 100%;
    }
    100% {
      width: 50%;
    }
  }
`;
