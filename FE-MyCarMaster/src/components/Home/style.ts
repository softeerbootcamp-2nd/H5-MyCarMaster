import styled from "styled-components";
import { Text, TextProps } from "@styles/core.style";

interface HomeTextProps extends TextProps {
  $size?: number;
  $align?: boolean;
}

export const HomeText = styled(Text)<HomeTextProps>`
  ${(props) => props.theme.fonts.Display}
  color: ${(props) => props.$color || "#FFFFFF"};

  ${(props) =>
    props.$align &&
    `
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
  `}

  @media screen and (max-width: 1400px) {
    font-size: 0;
  }

  @keyframes textAppear {
    0% {
      font-size: ${(props) => props.$size}rem;
      opacity: 0;
    }
    100% {
      font-size: ${(props) => props.$size}rem;
      opacity: 1;
    }
  }
`;

export const Container = styled.div<{ $animation?: boolean }>`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  gap: 2rem;

  margin: 2rem 0;

  animation: ${({ $animation }) =>
    $animation ? "homeAppear 2s ease-in-out forwards" : ""};

  @keyframes homeAppear {
    0% {
      opacity: 0;
    }
    100% {
      opacity: 1;
    }
  }
`;

export const TopContainer = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
`;

export const ModelContainer = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;

  gap: 1rem;
`;

export const ButtonContainer = styled.div`
  width: 100%;
  display: flex;
  justify-content: center;
`;
