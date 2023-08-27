import { styled } from "styled-components";
import { Text, Color } from "./style";

type innerColorProps = {
  $id: number;
  $name: string;
  ratio?: number;
  price: number;
  trim?: string;
  $active: boolean;
  $colorImgUrl?: string;
  $coloredImgUrl?: string;
  handleClick?: () => void;
};

export default function InnerColorBox(props: innerColorProps) {
  return (
    <Container onClick={props.handleClick}>
      <InnerColorImage src={props.$colorImgUrl} />
      <InnerColorText>
        <TopTextBox>
          <Text $style={Color.Head}>{props.$name}</Text>
          <Text $style={Color.Content}>
            {props.trim} 구매자의 {props.ratio}%가 선택
          </Text>
        </TopTextBox>
        <Text $style={Color.InnerPrice}>+ {props.price}원</Text>
      </InnerColorText>
      {props.$active && <SelectedFrame />}
    </Container>
  );
}

const Container = styled.div`
  width: 14.5rem;
  height: 10.25rem;
  display: flex;
  flex-direction: column;
  position: relative;
  cursor: pointer;
`;

const TopTextBox = styled.div`
  display: flex;
  flex-direction: column;
  gap: 0.3rem;
`;

const InnerColorImage = styled.img`
  height: 5.25rem;
`;

const InnerColorText = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding: 0.75rem 1rem;
  gap: 0.6875rem;
  border: 1px solid ${(props) => props.theme.colors.GREY2};
`;

const SelectedFrame = styled.div`
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  width: 14.6rem;
  height: 10.35rem;
  border: 2px solid ${(props) => props.theme.colors.NAVYBLUE5};
  z-index: 1;
`;
