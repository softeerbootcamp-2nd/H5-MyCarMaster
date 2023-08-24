import styled from "styled-components";
import { Text, TextProps } from "@styles/core.style";

interface HomeTextProps extends TextProps {
  $animation?: boolean;
  $delay?: number;
  $size?: number;
}

export const HomeText = styled(Text)<HomeTextProps>`
  font-size: ${(props) => (props.$animation ? "0" : props.$size)}rem;
  text-align: left;
  color: ${(props) => props.$color || "#FFFFFF"};

  animation: ${(props) =>
    props.$animation ? "textAppear 3s ease-in-out forwards" : ""};
  animation-delay: ${(props) => props.$delay}s;

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

export const Container = styled.div`
  position: relative;
  width: 100%;
  height: 100%;
`;

export const Video = styled.video`
  object-fit: fill;
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: -1;
`;

export const IntroduceBox = styled.div`
  width: 19rem;
  height: 13.75rem;
  position: absolute;
  display: flex;
  flex-direction: column;
  gap: 1rem;

  top: 40%;
  left: 65%;
  z-index: 1;

  button {
    position: absolute;
    bottom: 0;
  }
`;

export const Point = styled.div<{ $start: boolean }>`
  display: ${(props) => (props.$start ? "flex" : "none")};
  flex-direction: column;
  position: absolute;
  top: calc(40% + 11rem);
  left: 65%;
  z-index: 5;

  background-color: #fff;

  animation: ${(props) =>
    props.$start ? "point 0.5s ease-in-out forwards" : ""};

  @keyframes point {
    0% {
      top: calc(40% + 11rem);
      left: 65%;
      width: 15rem;
      height: 2.75rem;
    }
    100% {
      // 전체 화면으로 커지기
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      padding: 10rem;
    }
  }
`;
