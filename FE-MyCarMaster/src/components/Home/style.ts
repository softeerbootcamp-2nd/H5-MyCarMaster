import styled from "styled-components";

type TextProp = {
  $color?: string;
  $animation?: boolean;
  $delay?: number;
};

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

export const Text = styled.p<TextProp>`
  ${(props) => props.theme.fonts.Display}
  text-align: left;
  color: ${(props) => props.$color || "#FFFFFF"};

  @media screen and (max-width: 1400px) {
    font-size: 0;
  }
`;

export const NotFoundText = styled.p`
  ${(props) => props.theme.fonts.ContentMedium1}
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
`;
