import styled from "styled-components";

type ContainerProp = {
  name?: string;
  ratio?: number;
  price?: number;
  active?: boolean;
};

type TextColor = {
  color?: string;
};

function OuterColorBox(props: ContainerProp) {
  return (
    <Container active={props.active}>
      <TextContainer>
        <Text color="#fff">{props.name}</Text>
        <Text color="#7B7B7B">{props.ratio}</Text>
        <Text color="#fff">+{props.price}</Text>
      </TextContainer>
    </Container>
  );
}

const Container = styled.div<ContainerProp>`
  display: flex;
  width: 12.5rem;
  height: 5rem;
  padding: 0.75rem;
  align-items: center;
  flex-shrink: 0;
  background-color: black;
`;

const TextContainer = styled.div`
  height: 3.5rem;
  gap: 0.6875rem;
`;

const Text = styled.p<TextColor>`
  color: ${(props) => props.color};
  line-height: 1.25rem;
  font-size: 0.75rem;
`;

export default OuterColorBox;
