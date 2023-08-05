import { useReducer, createContext, useContext } from "react";

type exteriorColors = {
  id: number;
  name: string;
  price: number;
  ratio: number;
  colorImgUrl: string;
  coloredImgUrl: string[];
};

type interiorColors = {
  id: number;
  name: string;
  price: number;
  ratio: number;
  colorImgUrl: string;
  coloredImgUrl: string[];
};

type CarPaintState = {
  exteriorId: number;
  interiorId: number;
  exteriorList: exteriorColors[];
  interiorList: interiorColors[];
};

type CarPaintAction = {
  type:
    | "SELECT_EXTERIOR"
    | "SELECT_INTERIOR"
    | "SET_EXTERIOR_LIST"
    | "SET_INTERIOR_LIST";
  payload: {
    exteriorId: number;
    interiorId: number;
    exteriorList: exteriorColors[];
    interiorList: interiorColors[];
  };
};

const initialCarPaintState: CarPaintState = {
  exteriorId: 0,
  interiorId: 0,
  exteriorList: [
    {
      id: 0,
      name: "Select Exterior",
      price: 0,
      ratio: 0,
      colorImgUrl: "",
      coloredImgUrl: [""],
    },
  ],
  interiorList: [
    {
      id: 0,
      name: "Select Interior",
      price: 0,
      ratio: 0,
      colorImgUrl: "",
      coloredImgUrl: [""],
    },
  ],
};

type CarPaintDispatch = (action: CarPaintAction) => void;

const detailReducer = (
  state: CarPaintState,
  action: CarPaintAction
): CarPaintState => {
  switch (action.type) {
    case "SELECT_EXTERIOR":
      return {
        ...state,
        exteriorId: action.payload.exteriorId,
      };
    case "SELECT_INTERIOR":
      return {
        ...state,
        interiorId: action.payload.interiorId,
      };
    case "SET_EXTERIOR_LIST":
      return {
        ...state,
        exteriorList: action.payload.exteriorList,
      };
    case "SET_INTERIOR_LIST":
      return {
        ...state,
        interiorList: action.payload.interiorList,
      };
    default:
      return state;
  }
};

const CarPaintStateContext = createContext<CarPaintState | undefined>(
  undefined
);
const CarPaintDispatchContext = createContext<CarPaintDispatch | undefined>(
  undefined
);

export const CarPaintProvider = ({
  children,
}: {
  children: React.ReactNode;
}) => {
  const [state, dispatch] = useReducer(detailReducer, initialCarPaintState);
  return (
    <CarPaintStateContext.Provider value={state}>
      <CarPaintDispatchContext.Provider value={dispatch}>
        {children}
      </CarPaintDispatchContext.Provider>
    </CarPaintStateContext.Provider>
  );
};

export const useCarPaintState = () => {
  const state = useContext(CarPaintStateContext);
  if (!state) throw new Error("Cannot find CarPaintProvider");
  return state;
};

export const useCarPaintDispatch = () => {
  const dispatch = useContext(CarPaintDispatchContext);
  if (!dispatch) throw new Error("Cannot find CarPaintProvider");
  return dispatch;
};

export const useCarPaint = () =>
  [useCarPaintState(), useCarPaintDispatch()] as const;

export default CarPaintProvider;
