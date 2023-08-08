import { styled } from "styled-components";
import CategoryList from "../../common/CategoryList/CategoryList";
import option from "../../../assets/images/option.png";
import OptionDescription from "../../common/OptionDescription/OptionDescription";
import {
  useOptionDispatch,
  useOptionState,
} from "../../../contexts/OptionContext";
import { OptionState, OptionType } from "../../../types/options.types";
import filterOptionCategory from "../../../utils/Option/filterOptionCategory";
import { useState } from "react";
import { categories } from "../../../constants/Option.constants";

function OptionContent() {
  const { optionList }: OptionState = useOptionState();
  const [filterdOptionList, setFilteredOptionList] =
    useState<OptionType[]>(optionList);
  const optionDispatch = useOptionDispatch();

  const onClickHandler = (index: number) => {
    const nextOptionCategoryId = index;
    optionDispatch({
      type: "SET_OPTION_CATEGORY_INDEX",
      payload: { optionCategoryId: nextOptionCategoryId },
    });
    // filter
    const filteredList = filterOptionCategory(
      categories,
      nextOptionCategoryId,
      optionList
    );

    setFilteredOptionList(filteredList);
  };

  return (
    <Container>
      <OptionContainer>
        <OptionImg src={option} />
        <OptionDescription option={filterdOptionList[0]} />
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
`;

const OptionImg = styled.img`
  width: 29.875rem;
  height: 20.875rem;
  flex-shrink: 0;
`;

export default OptionContent;
