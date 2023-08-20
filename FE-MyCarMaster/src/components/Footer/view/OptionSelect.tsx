import styled from "styled-components";
import {
  useOptionDispatch,
  useOptionState,
} from "../../../contexts/OptionContext";
import { useQuotationState } from "../../../contexts/QuotationContext";
import OptionBox from "../../common/OptionBox/OptionBox";
import { useEffect, useState } from "react";
import { OptionType } from "../../../types/options.types";
import filterOptionCategory from "../../../utils/Option/filterOptionCategory";
import { categories } from "../../../constants/Option.constants";

export default function OptionSelect() {
  const { optionList, selectedOption, consideredOption, optionCategoryId } =
    useOptionState();
  const [isTrimCheckOption, setIsTrimCheckOption] = useState<boolean>(false);
  const { optionQuotation } = useQuotationState();
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
  }, [optionList, optionCategoryId]);

  useEffect(() => {
    if (optionQuotation.selectedQuotation) {
      const selectedOptionIdList = optionQuotation.selectedQuotation.map(
        (option) => option.id
      );
      optionDispatch({
        type: "SET_SELECTED_OPTIONS",
        payload: {
          where: "selectedOption",
          ids: selectedOptionIdList,
        },
      });
      setIsTrimCheckOption(true);
    }
  }, [optionQuotation.selectedQuotation, optionDispatch]);

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
      {isTrimCheckOption &&
        filteredOptionList?.length &&
        filteredOptionList.map((option, index) => {
          return (
            <OptionBox
              key={index}
              $id={option.id}
              $name={option.name}
              $description={option.description}
              $ratio={option.ratio}
              $price={option.price}
              $switch="option"
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
  width: 59.5rem;
  gap: 0.5rem;
  overflow: hidden;
  overflow-x: scroll;
  &::-webkit-scrollbar {
    display: none;
  }
`;
