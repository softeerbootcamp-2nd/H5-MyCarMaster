export type Trims = {
  id: number;
  name: string;
  description: string;
  price: number;
  ratio: number;
  imgUrl: string;
};

export type TrimState = {
  trimId: number;
  trimList: Trims[];
};

export type TrimAction = {
  type: "SELECT_TRIM" | "SET_TRIM_LIST";
  payload: {
    trimId?: number;
    trimList?: Trims[];
  };
};
