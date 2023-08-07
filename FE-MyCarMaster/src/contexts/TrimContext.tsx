import { useReducer, createContext, useContext } from "react";

type Trims = {
  id: number;
  name: string;
  description: string;
  price: number;
  ratio: number;
  imgUrl: string;
};

type TrimState = {
  trimId: number;
  trimList: Trims[];
};

type TrimAction = {
  type: "SELECT_TRIM" | "SET_TRIM_LIST";
  payload: {
    trimId: number;
    trimList: Trims[];
  };
};

const initialTrimState: TrimState = {
  trimId: 0,
  trimList: [
    {
      id: 0,
      name: "Select Trim",
      description: "Select Trim",
      price: 0,
      ratio: 0,
      imgUrl: "",
    },
  ],
};

type TrimDispatch = (action: TrimAction) => void;

const trimReducer = (state: TrimState, action: TrimAction): TrimState => {
  switch (action.type) {
    case "SELECT_TRIM":
      return {
        ...state,
        trimId: action.payload.trimId,
      };
    case "SET_TRIM_LIST":
      return {
        ...state,
        trimList: action.payload.trimList,
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