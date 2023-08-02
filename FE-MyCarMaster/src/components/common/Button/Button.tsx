import styled from "styled-components";

type ContainerProp = {
  x: number;
  y: number;
  backgroundColor?: string;
  textColor?: string;
  borderColor?: string;
  text?: string;
};

const Container = styled.button<ContainerProp>`
  display: flex;
  width: ${(props) => props.x}rem;
  height: ${(props) => props.y}rem;
  padding: 0.25 0.75rem;
  justify-content: center;
  align-items: center;
  gap: 0.5rem;
  border: 0.5px solid ${(props) => props.borderColor};
  background: ${(props) => props.backgroundColor};
  color: ${(props) => props.textColor};
`;

const Text = styled.div`
  font-size: 0.75rem;
  font-style: normal;
  font-weight: 500;
  line-height: 1rem;
  letter-spacing: -0.0225rem;
`;

function Button({ option }: { option: ContainerProp }) {
  return (
    <Container
      x={option.x}
      y={option.y}
      backgroundColor={option.backgroundColor}
      textColor={option.textColor}
      borderColor={option.borderColor}
      text={option.text}
    >
      <Text>{option.text}</Text>
    </Container>
  );
}

export default Button;
