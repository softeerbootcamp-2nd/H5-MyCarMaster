import styled from "styled-components";
import CategoryItem from "./CategoryItem";
import { useQuotationState } from "../../../contexts/QuotationContext";
import { useOptionState } from "../../../contexts/OptionContext";
import { FontType } from "@/styles/Theme";

type SwitchType = "detail" | "option" | "default" | "colors" | "model";
type CategoryType =
  | "파워트레인/성능"
  | "지능형 안전기술"
  | "안전"
  | "외관"
  | "내장"
  | "시트"
  | "편의";

type CagetoryListProps = {
  categories: string[];
  onClickHandler: (index?: number, category?: CategoryType | undefined) => void;
  indexSetter?: number | CategoryType;
  $switch?: SwitchType;
  $gap?: number;
  $font?: FontType;
};

function CategoryList({
  categories,
  onClickHandler,
  indexSetter,
  $switch,
  $gap,
  $font,
}: CagetoryListProps) {
  const { navigationId } = useQuotationState();
  const { optionCategoryId } = useOptionState();

  const SwitchActive = (
    switchType: SwitchType,
    index?: number,
    category?: CategoryType
  ): boolean => {
    switch (switchType) {
      case "detail":
      case "colors":
        return index === navigationId - (indexSetter as number);
      case "option":
        return index === optionCategoryId;
      case "default":
        return category === indexSetter;
      case "model":
        return index === indexSetter;
    }
  };

  return (
    <Container $gap={$gap}>
      {categories.map((category, index) => (
        <CategoryItem
          name={category}
          key={index}
          $font={$font}
          $active={SwitchActive(
            $switch as SwitchType,
            index,
            category as CategoryType
          )}
          onClickHandler={() => onClickHandler(index, category as CategoryType)}
        />
      ))}
    </Container>
  );
}

const Container = styled.ul<{ $gap?: number }>`
  display: inline-flex;
  align-items: flex-start;
  gap: ${({ $gap }) => ($gap ? `${$gap}rem` : "1.5rem")};
`;

export default CategoryList;
