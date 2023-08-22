import { useReducer, createContext, useContext } from "react";
import {
  CarPaintState,
  CarPaintAction,
  ExteriorColors,
  InteriorColors,
} from "types/carpaint.types";

const initialCarPaintState: CarPaintState = {
  exteriorId: 1,
  interiorId: 0,
  exteriorList: [],
  interiorList: [],
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
        exteriorId: action.payload.exteriorId as number,
      };
    case "SELECT_INTERIOR":
      return {
        ...state,
        interiorId: action.payload.interiorId as number,
      };
    case "SET_EXTERIOR_LIST":
      return {
        ...state,
        exteriorList: action.payload.exteriorList as ExteriorColors[],
      };
    case "SET_INTERIOR_LIST":
      return {
        ...state,
        interiorList: action.payload.interiorList as InteriorColors[],
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
