import styled from "styled-components";
import ArrowRight from "../../../assets/icons/ArrowRight.svg";
import { Text, DefaultStyle } from "./style";

type TextProp = {
  size?: string;
  gap?: string;
  text?: string;
  handleClick?: () => void;
};

function TextButton({ text, size, handleClick }: TextProp) {
  return (
    <Container size={size} onClick={handleClick}>
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

  font-size: ${(props) => props.size}rem;

  img {
    width: 0.75rem;
    height: 0.75rem;
  }
  cursor: pointer;
`;
