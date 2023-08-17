import { useState } from "react";
import theme from "../../styles/Theme";
import styled from "styled-components";
import { useNavigate } from "react-router-dom";
import CategoryList from "../common/CategoryList/CategoryList";
import Button from "../common/Button/Button";
import ModelBox from "./ModelBox";

const data = {
  model: [
    {
      name: "팰리세이드",
      price: 3000000,
      type: ["SUV", "승용", "수소 / 전기차"],
      image: "/images/car_model/palisade-24my-45side.png",
    },
    {
      name: "싼타페",
      price: 3000000,
      type: ["SUV", "승용"],
      image: "/images/car_model/santafe-23my-45side.png",
    },
    {
      name: "싼타페 Hybrid",
      price: 3000000,
      type: ["SUV", "승용", "수소 / 전기차"],
      image: "/images/car_model/santafe-hybrid-45side.png",
    },
    {
      name: "베뉴",
      price: 3000000,
      type: ["SUV", "승용"],
      image: "/images/car_model/venue-23my-45side.png",
    },
    {
      name: "투싼",
      price: 3000000,
      type: ["SUV", "승용", "N"],
      image: "/images/car_model/tucson-23my-45side.png",
    },
    {
      name: "투싼 Hybrid",
      price: 3000000,
      type: ["SUV", "승용", "수소 / 전기차", "N"],
      image: "/images/car_model/tucson-hybrid-23my-45side.png",
    },
    {
      name: "디 올 뉴 코나",
      price: 3000000,
      type: ["SUV", "승용"],
      image: "/images/car_model/the-all-new-kona-45side.png",
    },
    {
      name: "디 올 뉴 코나 Hybrid",
      price: 3000000,
      type: ["SUV", "승용", "수소 / 전기차"],
      image: "/images/car_model/the-all-new-kona-hybrid-45side.png",
    },
  ],
};

const typeData = [
  "전체",
  "수소 / 전기차",
  "N",
  "승용",
  "SUV",
  "MPV",
  "소형트럭&택시",
  "트럭",
  "버스",
];

type TextProp = {
  $color?: string;
  $animation?: boolean;
  $delay?: number;
};

type HomeProp = {
  isFold: boolean;
};

type SelectModelProp = {
  point: React.MouseEvent<HTMLDivElement, MouseEvent> | null;
  selected: number;
};

export default function Home({ isFold }: HomeProp) {
  const [categorySelect, setCategorySelect] = useState<number>(0);
  const [modelSelect, setModelSelect] = useState<SelectModelProp>({
    point: null,
    selected: 0,
  });
  const navigate = useNavigate();
  const homeButtonHandler = () => {
    // animation 추가 예정
    setTimeout(() => {
      navigate("/estimation");
    }, 0);
  };

  const handleModelSelect = (
    e: React.MouseEvent<HTMLDivElement, MouseEvent>,
    index: number
  ) => {
    setModelSelect({ point: e, selected: index });
  };

  const filteredModelData = data.model.filter((model) => {
    if (categorySelect === 0) return true;
    else return model.type.includes(typeData[categorySelect]);
  });

  console.log(filteredModelData);

  return (
    <Container $animation={isFold}>
      <TopContainer>
        <Text $color={"#222222"} $animation={isFold} $delay={0.5}>
          모델을 선택해주세요.
        </Text>

        <CategoryList
          categories={[
            "전체",
            "수소 / 전기차",
            "N",
            "승용",
            "SUV",
            "MPV",
            "소형트럭&택시",
            "트럭",
            "버스",
          ]}
          onClickHandler={(index) => setCategorySelect(index as number)}
          indexSetter={categorySelect}
          $switch="model"
          $gap={2.5}
        />
      </TopContainer>

      <ModelContainer>
        {filteredModelData.length === 0 && (
          <NotFoundText>해당되는 모델이 없습니다.</NotFoundText>
        )}
        {filteredModelData.map((model, index) => (
          <ModelBox
            key={index}
            id={index}
            name={model.name}
            price={model.price}
            imgUrl={model.image}
            active={index === modelSelect.selected}
            onClick={(e, index) => handleModelSelect(e, index)}
          />
        ))}
      </ModelContainer>

      <ButtonContainer>
        <Button
          $x={20}
          $y={2.75}
          $backgroundcolor={theme.colors.NAVYBLUE5}
          $textcolor={theme.colors.WHITE}
          $bordercolor={theme.colors.NAVYBLUE5}
          text="마이 카마스터 시작하기"
          handleClick={homeButtonHandler}
        />
      </ButtonContainer>
    </Container>
  );
}

const Container = styled.div<{ $animation?: boolean }>`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  gap: 2rem;

  margin: 2rem 0;

  animation: ${({ $animation }) =>
    $animation ? "homeAppear 2s ease-in-out forwards" : ""};

  @keyframes homeAppear {
    0% {
      opacity: 0;
    }
    100% {
      opacity: 1;
    }
  }
`;

const TopContainer = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
`;

const ModelContainer = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;

  gap: 1rem;
`;

const ButtonContainer = styled.div`
  width: 100%;
  display: flex;
  justify-content: center;
`;

const Text = styled.p<TextProp>`
  ${(props) => props.theme.fonts.Display}
  text-align: left;
  color: ${(props) => props.$color || "#FFFFFF"};

  @media screen and (max-width: 1400px) {
    font-size: 0;
  }
`;

const NotFoundText = styled.p`
  ${(props) => props.theme.fonts.ContentMedium1}
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
`;
