import { useReducer, createContext, useContext } from "react";
import { OptionAction, OptionState, OptionType } from "../types/options.types";

const initialOptionState: OptionState = {
  selectedOption: [],
  consideredOption: [],
  optionId: 1,
  optionCategoryId: 0,
  optionList: [],
};

type OptionDispatch = (action: OptionAction) => void;

const optionReducer = (
  state: OptionState,
  action: OptionAction
): OptionState => {
  switch (action.type) {
    case "SET_CHOICE_OPTION": {
      const { where, id } = action.payload;
      const existingIds =
        state[
          where === "consideredOption" ? "consideredOption" : "selectedOption"
        ];
      const existingOtherId =
        state[
          where === "consideredOption" ? "selectedOption" : "consideredOption"
        ];

      if (existingIds.includes(id as number)) {
        return {
          ...state,
          [where as string]: existingIds.filter(
            (existingId) => existingId !== id
          ),
        };
      } else {
        return {
          ...state,
          [where as string]: [...existingIds, id],
          [where === "consideredOption"
            ? "selectedOption"
            : "consideredOption"]: existingOtherId.filter(
            (existingId) => existingId !== id
          ),
        };
      }
    }
    case "SET_OPTION_LIST":
      return {
        ...state,
        optionList: action.payload.optionList as OptionType[],
      };
    case "SET_OPTION_CATEGORY_INDEX":
      return {
        ...state,
        optionCategoryId: action.payload.optionCategoryId as number,
        optionId: action.payload.optionId as number,
      };
    case "SET_OPTION_ID":
      return {
        ...state,
        optionId: action.payload.optionId as number,
      };
    default:
      return state;
  }
};

const OptionStateContext = createContext<OptionState | undefined>(undefined);
const OptionDispatchContext = createContext<OptionDispatch | undefined>(
  undefined
);

export const OptionProvider = ({ children }: { children: React.ReactNode }) => {
  const [state, dispatch] = useReducer(optionReducer, initialOptionState);
  return (
    <OptionStateContext.Provider value={state}>
      <OptionDispatchContext.Provider value={dispatch}>
        {children}
      </OptionDispatchContext.Provider>
    </OptionStateContext.Provider>
  );
};

export const useOptionState = () => {
  const state = useContext(OptionStateContext);
  if (!state) throw new Error("Cannot find OptionProvider");
  return state;
};

export const useOptionDispatch = () => {
  const dispatch = useContext(OptionDispatchContext);
  if (!dispatch) throw new Error("Cannot find OptionProvider");
  return dispatch;
};

export const useOption = () => [useOptionState(), useOptionDispatch()] as const;

export default OptionProvider;
