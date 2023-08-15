import { styled } from "styled-components";
import CategoryList from "../../common/CategoryList/CategoryList";
import optionImg from "../../../assets/images/option.png";
import OptionDescription from "../../common/OptionDescription/OptionDescription";
import {
  useOptionDispatch,
  useOptionState,
} from "../../../contexts/OptionContext";
import { OptionState } from "../../../types/options.types";
import filterOptionCategory from "../../../utils/Option/filterOptionCategory";
import { categories } from "../../../constants/Option.constants";

function OptionContent() {
  const { optionList, optionId }: OptionState = useOptionState();
  const optionDispatch = useOptionDispatch();

  const onClickHandler = (index: number) => {
    const nextOptionCategoryId = index;

    // 카테고리 이동시 첫 아이템으로 포커스 시키기 위한 리스트
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
    <Container>
      <OptionContainer>
        <OptionImg src={optionImg} />
        {/* API 완성 시, optionList[optionId].imgUrl 로 교체*/}
        <OptionDescription option={optionList[optionId - 1]} />
      </OptionContainer>
      <CategoryList categories={categories} onClickHandler={onClickHandler} />
    </Container>
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
