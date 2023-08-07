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
