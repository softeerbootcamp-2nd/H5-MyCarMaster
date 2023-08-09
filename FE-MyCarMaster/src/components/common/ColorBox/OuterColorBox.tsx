import styled from "styled-components";

type ContainerProp = {
  $id: number;
  $name: string;
  ratio: number;
  price: number;
  trim?: string;
  $active: boolean;
  $colorImgUrl?: string;
  $coloredImgUrl?: string;
  handleClick?: () => void;
};

type ActiveProp = {
  $active: boolean;
};

type TextProp = {
  color?: string;
  size?: number;
};

function OuterColorBox(props: ContainerProp) {
  return (
    <Container $active={props.$active} onClick={props.handleClick}>
      <TextContainer>
        <Text color="#fff" size={0.75}>
          {props.$name}
        </Text>
        <Text color="#7B7B7B" size={0.625}>
          {props.trim} 구매자의 {props.ratio}%가 선택
        </Text>
        <Text color="#fff" size={0.75}>
          +{props.price.toLocaleString("ko-KR")}원
        </Text>
      </TextContainer>
    </Container>
  );
}

const Container = styled.div<ActiveProp>`
  display: flex;
  width: 12.5rem;
  height: 5rem;
  padding: 0.75rem;
  align-items: center;
  flex-shrink: 0;
  background-color: black;

  cursor: pointer;
`;

const TextContainer = styled.div`
  height: 3.5rem;
  gap: 0.6875rem;
`;

const Text = styled.p<TextProp>`
  color: ${(props) => props.color};
  line-height: 1.25rem;
  font-size: ${(props) => props.size}rem;
`;

export default OuterColorBox;
