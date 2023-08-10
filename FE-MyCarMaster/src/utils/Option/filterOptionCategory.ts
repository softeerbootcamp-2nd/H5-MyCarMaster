import { categoryObject } from "../../constants/Option.constants";
import { OptionType } from "../../types/options.types";

export default function filterOptionCategory(
  categories: string[],
  index: number,
  optionList: OptionType[]
) {
  if (index === 0) return optionList;

  const selectedCategoryValue = categories[index];
  const selectedCategoryKey = Object.keys(categoryObject).find(
    (key) => categoryObject[key] === selectedCategoryValue
  );
  const filteredData = optionList.filter(
    (item: OptionType) => item.category === selectedCategoryKey
  );

  return filteredData;
}
