export type CarMasterType = {
  id: number;
  name: string;
  imgUrl: string;
  intro: string;
  phone: string;
  agency: {
    id: number;
    name: string;
  };
};

export type AgencyType = {
  id: number;
  name: string;
  gu: string;
  latitude: number;
  longitude: number;
};
