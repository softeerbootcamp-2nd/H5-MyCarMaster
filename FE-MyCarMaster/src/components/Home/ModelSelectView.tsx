import { useState, useEffect } from "react";
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
import useFetch from "@/hooks/useFetch";

type HomeProp = {
  isFold: boolean;
};

type SelectModelProp = {
  point: React.MouseEvent<HTMLDivElement, MouseEvent> | null;
  selected: number;
};

type ModelDataProps = {
  result: {
    models: {
      id: number;
      name: string;
      price: number;
      imgUrl: string;
      type: string;
      isNew: boolean;
    }[];
  };
};

export default function Home({ isFold }: HomeProp) {
  const [categorySelect, setCategorySelect] = useState<number>(0);
  const [modelSelect, setModelSelect] = useState<SelectModelProp>({
    point: null,
    selected: 0,
  });
  const navigate = useNavigate();
  const homeButtonHandler = () => {
    setTimeout(() => {
      navigate("/estimation");
    }, 0);
  };
  const SERVER_URL = import.meta.env.VITE_APP_SERVER_URL;

  const { data } = useFetch<ModelDataProps>(`${SERVER_URL}/models`, {
    method: "GET",
  });

  useEffect(() => {
    if (data) {
      setModelSelect({
        point: null,
        selected: data.result.models[0].id,
      });
    }
  }, [data]);
  const handleModelSelect = (
    e: React.MouseEvent<HTMLDivElement, MouseEvent>,
    index: number
  ) => {
    setModelSelect({ point: e, selected: index });
  };

  if (!data) return null;
  const filteredModelData = data.result.models.filter((model) => {
    if (categorySelect === 0) return true;
    else return model.type.includes(typeData[categorySelect]);
  });

  return (
    <Container $animation={isFold}>
      <TopContainer>
        <HomeText $color={theme.colors.BLACK} $font={theme.fonts.Medium20}>
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
            $font={theme.fonts.Regular25}
            $align={true}
          >
            해당되는 모델이 없습니다.
          </HomeText>
        )}
        {filteredModelData.length !== 0 &&
          filteredModelData.map(
            (model, index) =>
              // 8개만 렌더링
              index < 8 && (
                <ModelBox
                  key={index}
                  id={model.id}
                  name={model.name}
                  price={model.price}
                  imgUrl={model.imgUrl}
                  isNew={model.isNew}
                  active={model.id === modelSelect.selected}
                  onClick={(e, id) => handleModelSelect(e, id)}
                />
              )
          )}
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
