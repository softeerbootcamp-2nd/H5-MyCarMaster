import { useReducer, createContext, useContext } from "react";

export type Engines = {
  id: number;
  name: string;
  description: string;
  price: number;
  ratio: number;
  imgUrl: string;
  power: number;
  torque: number;
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

type DetailState = {
  engineId: number;
  wheelDriveId: number;
  bodyTypeId: number;
  engineList: Engines[];
  wheelDriveList: WheelDrives[];
  bodyTypeList: BodyTypes[];
};

type DetailAction = {
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

const initialDetailState: DetailState = {
  engineId: 1,
  wheelDriveId: 1,
  bodyTypeId: 1,
  engineList: [],
  wheelDriveList: [],
  bodyTypeList: [],
};

type DetailDispatch = (action: DetailAction) => void;

const detailReducer = (
  state: DetailState,
  action: DetailAction
): DetailState => {
  switch (action.type) {
    case "SELECT_DETAIL":
      return {
        ...state,
        engineId: action.payload.engineId as number,
        wheelDriveId: action.payload.wheelDriveId as number,
        bodyTypeId: action.payload.bodyTypeId as number,
      };

    case "SET_DETAIL_LIST":
      return {
        ...state,
        engineList: action.payload.engineList as Engines[],
        wheelDriveList: action.payload.wheelDriveList as WheelDrives[],
        bodyTypeList: action.payload.bodyTypeList as BodyTypes[],
      };
    default:
      return state;
  }
};

const DetailStateContext = createContext<DetailState | undefined>(undefined);
const DetailDispatchContext = createContext<DetailDispatch | undefined>(
  undefined
);

export const DetailProvider = ({ children }: { children: React.ReactNode }) => {
  const [state, dispatch] = useReducer(detailReducer, initialDetailState);
  return (
    <DetailStateContext.Provider value={state}>
      <DetailDispatchContext.Provider value={dispatch}>
        {children}
      </DetailDispatchContext.Provider>
    </DetailStateContext.Provider>
  );
};

export const useDetailState = () => {
  const state = useContext(DetailStateContext);
  if (!state) throw new Error("Cannot find DetailProvider");
  return state;
};

export const useDetailDispatch = () => {
  const dispatch = useContext(DetailDispatchContext);
  if (!dispatch) throw new Error("Cannot find DetailProvider");
  return dispatch;
};

export const useDetail = () => [useDetailState(), useDetailDispatch()] as const;

export default DetailProvider;
