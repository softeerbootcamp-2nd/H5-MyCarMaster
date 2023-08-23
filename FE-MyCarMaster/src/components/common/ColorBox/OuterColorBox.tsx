import styled from "styled-components";
import { Text, Price, Color } from "./style";

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
  return (
    <Container onClick={props.handleClick}>
      <OuterColor $colorImgUrl={props.$colorImgUrl as string} />
      <OuterDiscription>
        <Text $style={Color.Head}>{props.$name}</Text>
        <Text $style={Color.Content}>
          {props.trim} 구매자의 {props.ratio}%가 선택
        </Text>
        <Price $style={Color.OuterPrice}>
          +{props.price.toLocaleString("ko-KR")}원
        </Price>
      </OuterDiscription>
      {props.$active && <SelectedFrame />}
    </Container>
  );
}

const OuterColor = styled.div<{ $colorImgUrl: string }>`
  background-image: url(${(props) => props.$colorImgUrl});
  background-size: cover;
  background-position: center;
  height: 3rem;
  border-bottom: 1px solid ${(props) => props.theme.colors.GREY2};
`;

const OuterDiscription = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  position: relative;
  padding: 0.5rem 1rem;
  gap: 0.5rem;
`;

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  position: relative;
  width: 14.5rem;
  flex-shrink: 0;
  border: 1px solid ${(props) => props.theme.colors.GREY2};
  cursor: pointer;
`;

const SelectedFrame = styled.div`
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  width: 14.6rem;
  height: 7rem;
  border: 3px solid ${(props) => props.theme.colors.NAVYBLUE5};
  z-index: 1;
`;
