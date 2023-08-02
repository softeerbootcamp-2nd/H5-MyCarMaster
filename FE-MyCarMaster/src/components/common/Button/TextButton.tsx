import styled from "styled-components";
import ArrowRight from "../../../assets/icons/ArrowRight.svg";

type TextProp = {
  size?: string;
  gap?: string;
  text?: string;
};

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
`;
function TextButton(props: TextProp) {
  return (
    <Container size={props.size}>
      <p>{props.text}</p>
      <img src={ArrowRight} alt="arrow-right" />
    </Container>
  );
}

export default TextButton;
