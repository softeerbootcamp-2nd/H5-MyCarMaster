import styled from "styled-components";
import { Text, DarkColor, LightColor } from "./style";

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

export default function OuterColorBox(props: ContainerProp) {
  const isColorBright = 2 as number; // 임시로 2로 설정
  return (
    <Container
      onClick={props.handleClick}
      $colorImgUrl={props.$colorImgUrl as string}
    >
      <Text $style={isColorBright === 1 ? LightColor.Head : DarkColor.Head}>
        {props.$name}
      </Text>
      <Text
        $style={isColorBright === 1 ? LightColor.Content : DarkColor.Content}
      >
        {props.trim} 구매자의 {props.ratio}%가 선택
      </Text>
      <Text $style={isColorBright === 1 ? LightColor.Price : DarkColor.Price}>
        +{props.price.toLocaleString("ko-KR")}원
      </Text>
      {props.$active && <SelectedFrame />}
    </Container>
  );
}

const Container = styled.div<{ $colorImgUrl: string }>`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  position: relative;
  width: 12.5rem;
  height: 5rem;
  padding: 0.75rem;
  flex-shrink: 0;
  border: 1px solid ${(props) => props.theme.colors.BLACK}; // 수정 필요1
  background-image: url(${(props) => props.$colorImgUrl});
  background-repeat: round;
  cursor: pointer;
`;

const SelectedFrame = styled.div`
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  width: 12.1rem;
  height: 4.6rem;
  border: 3px solid ${(props) => props.theme.colors.WHITE}; // 수정 필요2
  z-index: 1;
`;
