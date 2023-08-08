import { styled } from "styled-components";
import { BLACK, COOLGREY2, GREY2, GREY3 } from "../../../styles/Color";
import InnerColor from "../../../assets/images/InnerColor.png";

type innerColorProps = {
  $id: number;
  $name: string;
  ratio?: number;
  price: number;
  trim: string;
  $active: boolean;
  $colorImgUrl?: string;
  $coloredImgUrl?: string[];
  onClick?: () => void;
};

function InnerColorBox(props: innerColorProps) {
  return (
    <Container>
      <InnerColorImage src={InnerColor} />
      <InnerColorText>
        <Text>
          <ColorName>{props.$name}</ColorName>
          <Ratio>
            {props.trim} 구매자의 {props.ratio}%가 선택
          </Ratio>
        </Text>
        <Price>+ {props.price}원</Price>
      </InnerColorText>
    </Container>
  );
}

const Container = styled.div`
  width: 12.5rem;
  height: 10.25rem;
  display: flex;
  flex-direction: column;
`;

const InnerColorImage = styled.img`
  height: 5.25rem;
`;

const InnerColorText = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: center;
  padding: 0.75rem 1rem;
  gap: 0.6875rem;
  width: 100%;
  height: 5rem;

  border: 1px solid ${GREY2};
`;

const Text = styled.div`
  height: 100%;
`;

const ColorName = styled.p`
  color: ${BLACK};
  font-size: 0.75rem;
  font-weight: 500;
`;

const Ratio = styled.p`
  color: ${GREY3};
  font-size: 0.625rem;
  font-weight: 400;
  line-height: 150%;
`;

const Price = styled.p`
  color: ${COOLGREY2};
  font-size: 0.75rem;
  font-weight: 500;
`;

export default InnerColorBox;
