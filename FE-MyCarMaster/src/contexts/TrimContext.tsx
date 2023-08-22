import { useReducer, createContext, useContext } from "react";
import { TrimAction, TrimState, Trims } from "types/trim.types";

const initialTrimState: TrimState = {
  trimId: 1,
  trimList: [],
};

type TrimDispatch = (action: TrimAction) => void;

const trimReducer = (state: TrimState, action: TrimAction): TrimState => {
  switch (action.type) {
    case "SELECT_TRIM":
      return {
        ...state,
        trimId: action.payload.trimId as number,
      };
    case "SET_TRIM_LIST":
      return {
        ...state,
        trimList: action.payload.trimList as Trims[],
      };

    default:
      return state;
  }
};

const TrimStateContext = createContext<TrimState | undefined>(undefined);
const TrimDispatchContext = createContext<TrimDispatch | undefined>(undefined);

export const TrimProvider = ({ children }: { children: React.ReactNode }) => {
  const [state, dispatch] = useReducer(trimReducer, initialTrimState);
  return (
    <TrimStateContext.Provider value={state}>
      <TrimDispatchContext.Provider value={dispatch}>
        {children}
      </TrimDispatchContext.Provider>
    </TrimStateContext.Provider>
  );
};

export const useTrimState = () => {
  const state = useContext(TrimStateContext);
  if (!state) throw new Error("Cannot find trimProvider");
  return state;
};

export const useTrimDispatch = () => {
  const dispatch = useContext(TrimDispatchContext);
  if (!dispatch) throw new Error("Cannot find trimProvider");
  return dispatch;
};

export const useTrim = () => [useTrimState(), useTrimDispatch()] as const;

export default TrimProvider;
