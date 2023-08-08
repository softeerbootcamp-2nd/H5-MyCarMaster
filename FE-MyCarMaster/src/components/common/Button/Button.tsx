import styled from "styled-components";
// import { useNavigate } from "react-router-dom";

type ContainerProp = {
  $x: number;
  $y: number;
  $backgroundcolor?: string;
  $textcolor?: string;
  $bordercolor?: string;
  text?: string | undefined;
  handleClick?: () => void;
};

function Button({
  $x,
  $y,
  $backgroundcolor,
  $textcolor,
  $bordercolor,
  text,
  handleClick,
}: ContainerProp) {
  return (
    <Container
      $x={$x}
      $y={$y}
      $backgroundcolor={$backgroundcolor}
      $textcolor={$textcolor}
      $bordercolor={$bordercolor}
      onClick={handleClick}
    >
      <Text>{text}</Text>
    </Container>
  );
}

const Container = styled.button<ContainerProp>`
  display: flex;
  width: ${(props) => props.$x}rem;
  height: ${(props) => props.$y}rem;
  padding: 0.25rem 0.75rem;
  justify-content: center;
  align-items: center;
  gap: 0.5rem;
  border: 0.5px solid ${(props) => props.$bordercolor};
  background: ${(props) => props.$backgroundcolor};
  color: ${(props) => props.$textcolor};
`;

const Text = styled.div`
  font-size: 0.75rem;
  font-style: normal;
  font-weight: 500;
  line-height: 1rem;
  letter-spacing: -0.0225rem;
`;

export default Button;
