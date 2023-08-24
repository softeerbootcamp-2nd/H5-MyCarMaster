import { useState } from "react";
import { useNavigate } from "react-router-dom";
import theme from "@styles/Theme";
import { CategoryList, Button } from "@common/index";
import ModelBox from "./ModelBox";
import {
  Container,
  TopContainer,
  ModelContainer,
  ButtonContainer,
  HomeText,
} from "./style";
import { typeData } from "@constants/Model.constatns";

const data = {
  model: [
    {
      id: 0,
      name: "팰리세이드",
      price: 3000000,
      type: ["SUV", "승용", "수소 / 전기차"],
      image: "/images/car_model/palisade-24my-45side.png",
    },
    {
      id: 1,
      name: "싼타페",
      price: 3000000,
      type: ["SUV", "승용"],
      image: "/images/car_model/santafe-23my-45side.png",
    },
    {
      id: 2,
      name: "싼타페 Hybrid",
      price: 3000000,
      type: ["SUV", "승용", "수소 / 전기차"],
      image: "/images/car_model/santafe-hybrid-45side.png",
    },
    {
      id: 3,
      name: "베뉴",
      price: 3000000,
      type: ["SUV", "승용"],
      image: "/images/car_model/venue-23my-45side.png",
    },
    {
      id: 4,
      name: "투싼",
      price: 3000000,
      type: ["SUV", "승용", "N"],
      image: "/images/car_model/tucson-23my-45side.png",
    },
    {
      id: 5,
      name: "투싼 Hybrid",
      price: 3000000,
      type: ["SUV", "승용", "수소 / 전기차", "N"],
      image: "/images/car_model/tucson-hybrid-23my-45side.png",
    },
    {
      id: 6,
      name: "디 올 뉴 코나",
      price: 3000000,
      type: ["SUV", "승용"],
      image: "/images/car_model/the-all-new-kona-45side.png",
    },
    {
      id: 7,
      name: "디 올 뉴 코나 Hybrid",
      price: 3000000,
      type: ["SUV", "승용", "수소 / 전기차"],
      image: "/images/car_model/the-all-new-kona-hybrid-45side.png",
    },
  ],
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

  return (
    <Container $animation={isFold}>
      <TopContainer>
        <HomeText $color={theme.colors.BLACK} $font={theme.fonts.Bold20}>
          모델을 선택해주세요.
        </HomeText>

        <CategoryList
          categories={typeData}
          onClickHandler={(index) => setCategorySelect(index as number)}
          indexSetter={categorySelect}
          $switch="model"
          $gap={2.5}
          $font={theme.fonts.Medium12_13}
        />
      </TopContainer>

      <ModelContainer>
        {filteredModelData.length === 0 && (
          <HomeText
            $color={theme.colors.BLACK}
            $font={theme.fonts.Bold25}
            $align={true}
          >
            해당되는 모델이 없습니다.
          </HomeText>
        )}
        {filteredModelData.map((model, index) => (
          <ModelBox
            key={index}
            id={model.id}
            name={model.name}
            price={model.price}
            imgUrl={model.image}
            active={model.id === modelSelect.selected}
            onClick={(e, id) => handleModelSelect(e, id)}
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
