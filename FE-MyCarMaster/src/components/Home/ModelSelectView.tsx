import styled from "styled-components";
import Button from "../common/Button/Button.tsx";
import { useNavigate } from "react-router-dom";

type TextProp = {
  $size: number;
  $weight: number;
  $lineHeight: number;
  $color?: string;
  $animation?: boolean;
  $delay?: number;
};

type HomeProp = {
  isFold: boolean;
};

export default function Home({ isFold }: HomeProp) {
  const navigate = useNavigate();
  const homeButtonHandler = () => {
    navigate("/estimation");
  };

  return (
    <>
      <Text
        $size={2.4}
        $weight={500}
        $lineHeight={2.5}
        $color={"#222222"}
        $animation={isFold}
        $delay={0.5}
      >
        모델을 선택해주세요.
      </Text>
      <Text
        $size={1.2}
        $weight={400}
        $lineHeight={1.5}
        $color={"#222222"}
        $animation={isFold}
        $delay={2.5}
      >
        내게 맞는 견적부터 카마스터 연결까지
      </Text>
      <Text
        $size={1.2}
        $weight={400}
        $lineHeight={1.5}
        $color={"#222222"}
        $animation={isFold}
        $delay={4.5}
      >
        마이 카마스터와 함께해요
      </Text>

      <Button
        $x={15}
        $y={2.75}
        $backgroundcolor="#FFFFFF"
        $textcolor="#222222"
        $bordercolor="#FFFFFF"
        text="여기 클릭하면 시작함"
        handleClick={homeButtonHandler}
      />
    </>
  );
}

const Text = styled.p<TextProp>`
  font-size: ${(props) => (props.$animation ? "0" : props.$size)}rem;
  font-style: normal;
  font-weight: ${(props) => props.$weight};
  line-height: ${(props) => props.$lineHeight}rem;
  text-align: left;
  color: ${(props) => props.$color || "#FFFFFF"};

  // appear transition add delay
  animation: ${(props) =>
    props.$animation ? "textAppear 3s ease-in-out forwards" : ""};
  animation-delay: ${(props) => props.$delay}s;

  @keyframes textAppear {
    0% {
      font-size: ${(props) => props.$size}rem;
      opacity: 0;
    }
    100% {
      font-size: ${(props) => props.$size}rem;
      opacity: 1;
    }
  }
`;
