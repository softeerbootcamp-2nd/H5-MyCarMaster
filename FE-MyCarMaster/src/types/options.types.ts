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
  optionId: number;
  optionCategoryId: number;
};

type OptionActionType =
  | "SET_CHOICE_OPTION"
  | "SET_OPTION_LIST"
  | "SET_OPTION_CATEGORY_INDEX"
  | "SET_OPTION_ID"
  | "SET_SELECTED_OPTIONS";

export type OptionAction = {
  type: OptionActionType;
  payload: {
    where?: "selectedOption" | "consideredOption";
    id?: number;
    selectedOption?: number[];
    consideredOption?: number[];
    optionList?: OptionType[];
    optionId?: number;
    optionCategoryId?: number;
    ids?: (number | undefined)[];
  };
};

export type UnselectableOptionProps = {
  id: number;
  name: string;
  price: number;
};

export type BasicOptionProps = {
  id: number;
  category: string;
  name: string;
  imgUrl: string;
  description: string;
};

export type DescriptionOptionModalProps = {
  id: number;
  name: string;
  imgUrl: string;
  summary?: string;
  description: string;
  subOptions?: SuboptionsType | null;
  filter?: {
    unavailableTrimIds: number[];
    additionalTrimIds: number[];
    defaultTrimIds: number[];
  };
  appliedOption?: {
    id: number;
    category: string;
    name: string;
    price: number;
    imgUrl: string;
  };
};
