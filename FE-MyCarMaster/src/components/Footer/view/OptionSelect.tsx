import { useEffect, useState } from "react";
import { BlurFlex, OptionFlex, ScrollButton } from "./style";
import { useOptionDispatch, useOptionState } from "@contexts/OptionContext";
import { useQuotationState } from "@contexts/QuotationContext";
import { categories } from "@constants/Option.constants";
import { OptionBox } from "@common/index";
import filterOptionCategory from "@utils/Option/filterOptionCategory";
import { OptionType } from "types/options.types";
import ArrowRightLong from "@assets/icons/ArrowRightLong.svg";
import ArrowLeftLong from "@assets/icons/ArrowLeftLong.svg";

export default function OptionSelect() {
  const { optionList, selectedOption, consideredOption, optionCategoryId } =
    useOptionState();
  const [isTrimCheckOption, setIsTrimCheckOption] = useState<boolean>(false);
  const { optionQuotation } = useQuotationState();
  // const [current, setCurrent] = useState<number>(0);
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

  const handleScroll = (e: React.WheelEvent<HTMLDivElement>) => {
    const target = e.currentTarget;
    const scrollAmount = e.deltaY;
    target.scrollTo({
      top: 0,
      left: target.scrollLeft + scrollAmount,
      behavior: "smooth",
    });
    // setCurrent(target.scrollLeft);
  };

  const handleScrollButton = (direction: string) => {
    const target = document.querySelector(".option-box") as HTMLDivElement;
    const scrollAmount = direction === "left" ? -230 : 230;
    target.scrollTo({
      top: 0,
      left: target.scrollLeft + scrollAmount,
      behavior: "smooth",
    });
    // setCurrent(target.scrollLeft);
  };

  return (
    <BlurFlex $width="59.5rem" $justifyContent="center" $position="relative">
      <OptionFlex $gap="0.5rem" onWheel={handleScroll} className="option-box">
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
      </OptionFlex>
      <ScrollButton
        $direction="left"
        onClick={() => handleScrollButton("left")}
      >
        <img src={ArrowLeftLong} alt="arrow-left" />
      </ScrollButton>
      <ScrollButton
        $direction="right"
        onClick={() => handleScrollButton("right")}
      >
        <img src={ArrowRightLong} alt="arrow-right" />
      </ScrollButton>
    </BlurFlex>
  );
}
