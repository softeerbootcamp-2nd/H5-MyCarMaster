export type ExteriorColors = {
    id: number;
    name: string;
    price: number;
    ratio: number;
    colorImgUrl: string;
    coloredImgUrl: string;
  };
  
  export type InteriorColors = {
    id: number;
    name: string;
    price: number;
    ratio: number;
    colorImgUrl: string;
    coloredImgUrl: string;
  };

  export type CarPaintState = {
    exteriorId: number;
    interiorId: number;
    exteriorList: ExteriorColors[];
    interiorList: InteriorColors[];
  };
  
  export type CarPaintAction = {
    type:
      | "SELECT_EXTERIOR"
      | "SELECT_INTERIOR"
      | "SET_EXTERIOR_LIST"
      | "SET_INTERIOR_LIST";
    payload: {
      exteriorId?: number;
      interiorId?: number;
      exteriorList?: ExteriorColors[];
      interiorList?: InteriorColors[];
    };
  };