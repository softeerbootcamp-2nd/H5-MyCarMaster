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
  type: "SET_CHOICE_OPTION" | "SET_OPTION_LIST" | "SET_OPTION_CATEGORY_INDEX";
  payload: {
    where?: "selectedOption" | "consideredOption";
    id?: number;
    selectedOption?: number[];
    consideredOption?: number[];
    optionList?: OptionType[];
    optionCategoryId?: number;
  };
};
