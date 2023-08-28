import styled from "styled-components";
import ArrowRight from "@assets/icons/ArrowRight.svg";
import { Text, DefaultStyle } from "./style";
import { FontType } from "@/styles/Theme";

type TextProp = {
  gap?: string;
  text?: string;
  $font?: FontType;
  handleClick?: () => void;
};

function TextButton({ text, $font, handleClick }: TextProp) {
  return (
    <Container $font={$font} onClick={handleClick}>
      <Text $style={DefaultStyle.Text}>{text}</Text>
      <img src={ArrowRight} alt="arrow-right" />
    </Container>
  );
}

export default TextButton;

const Container = styled.div<TextProp>`
  display: inline-flex;
  justify-content: center;
  align-items: center;
  gap: 0.5rem;

  ${(props) => props.$font};

  img {
    width: 0.75rem;
    height: 0.75rem;
  }
  cursor: pointer;
`;
