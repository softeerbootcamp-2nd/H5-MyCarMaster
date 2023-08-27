import styled from "styled-components";

export interface FlexProps {
  $flexDirection?: "row" | "column";
  $width?: string;
  $height?: string;
  $maxHeight?: string;
  $padding?: string;
  $position?: "relative" | "absolute";
  $margin?: string;
  $justifyContent?:
    | "center"
    | "flex-start"
    | "flex-end"
    | "space-between"
    | "space-around"
    | "normal";
  $alignItems?: "center" | "flex-start" | "flex-end" | "stretch" | "normal
  $alignContent?: "center" | "flex-start" | "flex-end" | "stretch" | "normal";
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
  max-height: ${(props) => props.$maxHeight || "100%"};
  padding: ${(props) => props.$padding || "0"};
  margin: ${(props) => props.$margin || "0"};
  justify-content: ${(props) => props.$justifyContent || "flex-start"};
  align-items: ${(props) => props.$alignItems || "normal"};
  align-content: ${(props) => props.$alignContent || "normal"};
  gap: ${(props) => props.$gap || "0"};
  background-color: ${(props) => props.$backgroundColor || "transparent"};
  flex-wrap: ${(props) => (props.$wrap ? "wrap" : "nowrap")};
  overflow: ${(props) => props.$overflow || "visible"};
  position: ${(props) => props.$position || "static"};
`;

export interface TextProps {
  $color?: string;
  $textAlign?: string;
  $font?: any;
}

export const Text = styled.p<TextProps>`
  ${(props) => props.$font || ""};
  color: ${(props) => props.$color || "#000"};
  text-align: ${(props) => props.$textAlign || "left"};
`;

export interface TooltipProps {
  $width?: string;
  $height?: string;
  $top?: string;
  $left?: string;
}

export const Tooltip = styled.img<TooltipProps>`
  position: absolute;
  width: ${(props) => props.$width || "100%"};
  height: ${(props) => props.$height || "100%"};
  top: ${(props) => props.$top || "0"};
  left: ${(props) => props.$left || "0"};

  animation: 0.5s showTooltip ease-in-out forwards;

  @keyframes showTooltip {
    0% {
      opacity: 0;
    }
    100% {
      opacity: 1;
    }
  }
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
