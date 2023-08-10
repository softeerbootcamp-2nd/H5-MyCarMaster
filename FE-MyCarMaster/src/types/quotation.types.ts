export type QuotationType = {
  id?: number;
  name: string;
  price: number;
  imgUrl?: string;
  category?: string;
  description?: string;
};

export type TrimQuotationType = {
  trimQuotation?: QuotationType;
};

export type DetailQuotationType = {
  engineQuotation?: QuotationType;
  wheelDriveQuotation?: QuotationType;
  bodyTypeQuotation?: QuotationType;
};

export type CarPaintQuotationType = {
  exteriorColorQuotation: QuotationType;
  interiorColorQuotation: QuotationType;
};

export type OptionQuotationType = {
  selectedQuotation: QuotationType[];
  consideredQuotation: QuotationType[];
};
