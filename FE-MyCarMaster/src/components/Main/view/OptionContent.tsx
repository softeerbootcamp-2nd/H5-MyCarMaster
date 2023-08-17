import { styled } from "styled-components";
import CategoryList from "../../common/CategoryList/CategoryList";
import OptionDescription from "../../common/OptionDescription/OptionDescription";
import {
  useOptionDispatch,
  useOptionState,
} from "../../../contexts/OptionContext";
import useFetch from "../../../hooks/useFetch";
import { useEffect } from "react";
import { OptionType, OptionState } from "../../../types/options.types";
import filterOptionCategory from "../../../utils/Option/filterOptionCategory";
import { categories } from "../../../constants/Option.constants";
import { useTrimState } from "../../../contexts/TrimContext";
import { useDetailState } from "../../../contexts/DetailContext";
import { useCarPaintState } from "../../../contexts/CarPaintContext";

interface FetchOptionsProps extends OptionType {
  result: {
    options: OptionType[];
  };
}
function OptionContent() {
  const { optionList, optionId }: OptionState = useOptionState();
  const optionDispatch = useOptionDispatch();

  const { trimId } = useTrimState();
  const { engineId, wheelDriveId, bodyTypeId } = useDetailState();
  const { interiorId } = useCarPaintState();

  const SERVER_URL = import.meta.env.VITE_APP_SERVER_URL;

  const { data } = useFetch<FetchOptionsProps>(
    `${SERVER_URL}/options?trimId=${trimId}&engineId=${engineId}&wheelDriveId=${wheelDriveId}&bodyTypeId=${bodyTypeId}&interiorColorId=${interiorId}`,
    {
      method: "GET",
    }
  );

  useEffect(() => {
    if (data) {
      optionDispatch({
        type: "SET_OPTION_LIST",
        payload: { optionList: data.result.options },
      });

      optionDispatch({
        type: "SET_OPTION_CATEGORY_INDEX",
        payload: {
          optionCategoryId: 0,
          optionId: data.result.options[0].id,
        },
      });
    }
  }, [data, optionDispatch]);

  const onClickHandler = (index: number) => {
    const nextOptionCategoryId = index;

    const filteredList = filterOptionCategory(
      categories,
      nextOptionCategoryId,
      optionList
    );

    optionDispatch({
      type: "SET_OPTION_CATEGORY_INDEX",
      payload: {
        optionCategoryId: nextOptionCategoryId,
        optionId: filteredList[0].id,
      },
    });
  };

  return (
    optionList?.length !== 0 && (
      <Container>
        <OptionContainer>
          <OptionImg
            src={optionList.find((option) => option.id === optionId)?.imgUrl}
          />
          <OptionDescription
            option={
              optionList.find((option) => option.id === optionId) as OptionType
            }
          />
        </OptionContainer>
        <CategoryList
          categories={categories}
          onClickHandler={(index) => onClickHandler(index as number)}
          $switch={"option"}
        />
      </Container>
    )
  );
}

const Container = styled.div`
  width: 100%;
  height: 100%;

  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: flex-start;
`;

const OptionContainer = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 1rem;
`;

const OptionImg = styled.img`
  max-width: 37rem;
  width: 100%;
  flex-shrink: 0;
`;

export default OptionContent;
