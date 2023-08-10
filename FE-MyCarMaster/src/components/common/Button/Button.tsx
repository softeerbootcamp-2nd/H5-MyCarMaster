import styled, { RuleSet } from "styled-components";
import { Text, DefaultStyle, OptionStyle, StyleKey } from "./style";

type ContainerProp = {
  $x: number;
  $y: number;
  $backgroundcolor?: string;
  $textcolor?: string;
  $bordercolor?: string;
  text?: string | undefined;
  $tool?: string;
  $style?: RuleSet | string;
  handleClick?: () => void;
};

function Button({
  $x,
  $y,
  $backgroundcolor,
  $textcolor,
  $bordercolor,
  text,
  $tool,
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
      $style={$tool && OptionStyle[$tool as StyleKey]}
    >
      <Text
        $style={
          $tool === "Qutoation"
            ? DefaultStyle.Qutoation
            : $tool
            ? DefaultStyle.Option
            : DefaultStyle.General
        }
      >
        {text}
      </Text>
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
  border: 1px solid ${(props) => props.$bordercolor};
  background: ${(props) => props.$backgroundcolor};
  color: ${(props) => props.$textcolor};
  ${(props) => props.$style}
`;

export default Button;
