export type OptionType = {
  id: number;
  category?: string;
  name: string;
  price?: number;
  ratio?: number;
  imgUrl?: string;
  description: string;
  tag: string | null;
  subOptions: SuboptionsType[] | null;
};

export type SuboptionsType = {
  name: string;
  description: string;
  imgUrl?: string;
};

export type OptionCategoryType = {
  [key: string]: string;
};

export type OptionState = {
  selectedOption: number[];
  consideredOption: number[];
  optionList: OptionType[];
  optionCategoryId: number;
};

export type OptionAction = {
  type: "SELECT_OPTION" | "SET_OPTION_LIST" | "SET_OPTION_CATEGORY_INDEX";
  payload: {
    selectedOption?: number[];
    consideredOption?: number[];
    optionList?: OptionType[];
    optionCategoryId?: number;
  };
};
