import styled from "styled-components";
import { useOptionState } from "../../../contexts/OptionContext";
import OptionBox from "../../../components/common/OptionBox/OptionBox";
import { useEffect, useState } from "react";
import { OptionType } from "../../../types/options.types";
import filterOptionCategory from "../../../utils/Option/filterOptionCategory";
import { categories } from "../../../constants/Option.constants";

export default function OptionSelect() {
  const { optionList, selectedOption, consideredOption, optionCategoryId } =
    useOptionState();
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
            $selected={selectedOption.includes(option.id)}
            $considered={consideredOption.includes(option.id)}
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
`;
