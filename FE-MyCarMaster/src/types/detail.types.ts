export type Engines = {
    id: number;
    name: string;
    description: string;
    price: number;
    ratio: number;
    imgUrl: string;
    power: number;
    toque: number;
    fuelMin: number;
    fuelMax: number;
  };
  
  export type WheelDrives = {
    id: number;
    name: string;
    description: string;
    price: number;
    ratio: number;
    imgUrl: string;
  };
  
  export type BodyTypes = {
    id: number;
    name: string;
    description: string;
    price: number;
    ratio: number;
    imgUrl: string;
  };
  
  export type DetailState = {
    engineId: number;
    wheelDriveId: number;
    bodyTypeId: number;
    engineList: Engines[];
    wheelDriveList: WheelDrives[];
    bodyTypeList: BodyTypes[];
  };
  
  export type DetailAction = {
    type: "SELECT_DETAIL" | "SET_DETAIL_LIST";
    payload: {
      engineId?: number;
      wheelDriveId?: number;
      bodyTypeId?: number;
      engineList?: Engines[];
      wheelDriveList?: WheelDrives[];
      bodyTypeList?: BodyTypes[];
    };
  };