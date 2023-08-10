import styled from "styled-components";
import CategoryItem from "./CategoryItem";
import { useQuotationState } from "../../../contexts/QuotationContext";
import { useOptionState } from "../../../contexts/OptionContext";

type CagetoryListProps = {
  categories: string[];
  onClickHandler: (index: number) => void;
  indexSetter?: number;
};

function CategoryList({
  categories,
  onClickHandler,
  indexSetter,
}: CagetoryListProps) {
  const { navigationId } = useQuotationState();
  const { optionCategoryId } = useOptionState();
  return (
    <Container>
      {categories.map((category, index) => (
        <CategoryItem
          name={category}
          key={index}
          $active={
            indexSetter !== undefined
              ? index === navigationId - indexSetter
              : index === optionCategoryId
          }
          onClickHandler={() => onClickHandler(index)}
        />
      ))}
    </Container>
  );
}

const Container = styled.ul`
  display: inline-flex;
  align-items: flex-start;
  gap: 1.5rem;
`;

export default CategoryList;
