import { useReducer, createContext, useContext } from "react";
import { ModelState, ModelAction } from "../types/model.types";

const initialModelState: ModelState = {
  modelId: 1,
  modelName: "Palisade",
};

type ModelDispatch = (action: ModelAction) => void;

const modelReducer = (state: ModelState, action: ModelAction): ModelState => {
  switch (action.type) {
    case "SET_MODEL":
      return {
        ...state,
        modelId: action.payload.modelId,
        modelName: action.payload.modelName,
      };
    default:
      return state;
  }
};

const ModelStateContext = createContext<ModelState | undefined>(undefined);
const ModelDispatchContext = createContext<ModelDispatch | undefined>(
  undefined
);

export const ModelProvider = ({ children }: { children: React.ReactNode }) => {
  const [state, dispatch] = useReducer(modelReducer, initialModelState);
  return (
    <ModelStateContext.Provider value={state}>
      <ModelDispatchContext.Provider value={dispatch}>
        {children}
      </ModelDispatchContext.Provider>
    </ModelStateContext.Provider>
  );
};

export const useModelState = () => {
  const state = useContext(ModelStateContext);
  if (!state) throw new Error("Cannot find ModelProvider");
  return state;
};

export const useModelDispatch = () => {
  const dispatch = useContext(ModelDispatchContext);
  if (!dispatch) throw new Error("Cannot find ModelProvider");
  return dispatch;
};

export const useModel = () => [useModelState(), useModelDispatch()] as const;

export default ModelProvider;
