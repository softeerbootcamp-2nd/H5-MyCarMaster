import { useReducer, createContext, useContext } from "react";

type engines = {
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

type wheelDrives = {
  id: number;
  name: string;
  description: string;
  price: number;
  ratio: number;
  imgUrl: string;
};

type bodyTypes = {
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
  engineList: engines[];
  wheelDriveList: wheelDrives[];
  bodyTypeList: bodyTypes[];
};

type DetailAction = {
  type: "SELECT_DETAIL" | "SET_DETAIL_LIST";
  payload: {
    engineId: number;
    wheelDriveId: number;
    bodyTypeId: number;
    engineList: engines[];
    wheelDriveList: wheelDrives[];
    bodyTypeList: bodyTypes[];
  };
};

const initialDetailState: DetailState = {
  engineId: 0,
  wheelDriveId: 0,
  bodyTypeId: 0,
  engineList: [
    {
      id: 0,
      name: "Select Engine",
      description: "Select Engine",
      price: 0,
      ratio: 0,
      imgUrl: "",
      power: 0,
      torque: 0,
      fuelMin: 0,
      fuelMax: 0,
    },
  ],
  wheelDriveList: [
    {
      id: 0,
      name: "Select Wheel Drive",
      description: "Select Wheel Drive",
      price: 0,
      ratio: 0,
      imgUrl: "",
    },
  ],
  bodyTypeList: [
    {
      id: 0,
      name: "Select Body Type",
      description: "Select Body Type",
      price: 0,
      ratio: 0,
      imgUrl: "",
    },
  ],
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
        engineId: action.payload.engineId,
        wheelDriveId: action.payload.wheelDriveId,
        bodyTypeId: action.payload.bodyTypeId,
      };

    case "SET_DETAIL_LIST":
      return {
        ...state,
        engineList: action.payload.engineList,
        wheelDriveList: action.payload.wheelDriveList,
        bodyTypeList: action.payload.bodyTypeList,
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
