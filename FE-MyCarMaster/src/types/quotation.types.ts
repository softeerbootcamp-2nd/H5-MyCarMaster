export type QuotationType = {
  id?: number;
  name: string;
  price: number;
  imgUrl?: string;
  coloredImgUrl?: string;
  category?: string;
  description?: string;
};

export type TrimQuotationType = {
  trimQuotation: QuotationType;
};

export type DetailQuotationType = {
  engineQuotation: QuotationType;
  wheelDriveQuotation: QuotationType;
  bodyTypeQuotation: QuotationType;
};

export type CarPaintQuotationType = {
  exteriorColorQuotation: QuotationType;
  interiorColorQuotation: QuotationType;
};

export type OptionQuotationType = {
  selectedQuotation: QuotationType[];
  consideredQuotation: QuotationType[];
};

export type QuotationState = {
  navigationId: number;
  isFirst: boolean[];
  trimQuotation: TrimQuotationType;
  detailQuotation: DetailQuotationType;
  carPaintQuotation: CarPaintQuotationType;
  optionQuotation: OptionQuotationType;
};

export type QuotationActionType =
  | "NAVIGATE"
  | "SET_TRIM_QUOTATION"
  | "SET_DETAIL_QUOTATION"
  | "SET_SELECT_QUOTATION"
  | "SET_CONSIDER_QUOTATION"
  | "SET_CAR_PAINT_QUOTATION"
  | "SET_MY_TRIM_OPTIONS"
  | "RESET_QUOTATION"
  | "REMOVE_EXCEPT_SELECTED";

export type QuotationAction = {
  type: QuotationActionType;
  payload?: {
    id?: number;
    ids?: number[];
    navigationId?: number;
    isFirst?: boolean[];
    type?: string;
    name?: string | undefined;
    price?: number | undefined;
    imgUrl?: string;
    coloredImgUrl?: string;
    category?: string;
    description?: string;
    optionList?: QuotationType[];
  };
};

export type ConfirmType = {
  confirm: boolean;
};
