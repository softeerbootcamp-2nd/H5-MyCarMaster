import styled from "styled-components";
import {
  useOptionDispatch,
  useOptionState,
} from "../../../contexts/OptionContext";
import OptionBox from "../../common/OptionBox/OptionBox";
import { useEffect, useState } from "react";
import { OptionType } from "../../../types/options.types";
import filterOptionCategory from "../../../utils/Option/filterOptionCategory";
import { categories } from "../../../constants/Option.constants";

export default function OptionSelect() {
  const { optionList, selectedOption, consideredOption, optionCategoryId } =
    useOptionState();
  const optionDispatch = useOptionDispatch();
  const [filteredOptionList, setFilteredOptionList] =
    useState<OptionType[]>(optionList);

  useEffect(() => {
    const filteredList = filterOptionCategory(
      categories,
      optionCategoryId,
      optionList
    );

    setFilteredOptionList(filteredList);
  }, [optionCategoryId]);

  const changeOptionId = (index: number) => {
    optionDispatch({
      type: "SET_OPTION_ID",
      payload: {
        optionId: index,
      },
    });
  };

  return (
    <Container>
      {filteredOptionList.map((option, index) => {
        return (
          <OptionBox
            key={index}
            $id={option.id}
            $name={option.name}
            $description={option.description}
            $imgUrl={option.imgUrl}
            $ratio={option.ratio}
            $price={option.price}
            $choice={selectedOption.includes(option.id)}
            $considered={consideredOption.includes(option.id)}
            handleClick={() => changeOptionId(option.id)}
          />
        );
      })}
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: row;
  width: 51.5rem;
  gap: 0.5rem;
  overflow: hidden;
  overflow-x: scroll;
  &::-webkit-scrollbar {
    display: none;
  }
`;