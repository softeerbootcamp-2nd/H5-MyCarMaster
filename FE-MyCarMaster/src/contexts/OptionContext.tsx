import { useReducer, createContext, useContext } from "react";

type subOptions = {
  name: string;
  imgUrl: string;
  description: string;
};
type options = {
  id: number;
  name: string;
  description: string;
  price: number;
  ratio: number;
  imgUrl: string;
  category: string;
  tag: string;
  subOptions: subOptions[];
};

type OptionState = {
  selectedOption: number[];
  consideredOption: number[];
  optionList: options[];
};

type OptionAction = {
  type: "SELECT_OPTION" | "SET_OPTION_LIST";
  payload: {
    selectedOption: number[];
    consideredOption: number[];
    optionList: options[];
  };
};

const initialOptionState: OptionState = {
  selectedOption: [0],
  consideredOption: [0],
  optionList: [
    {
      id: 0,
      name: "Select Option",
      description: "Select Option",
      price: 0,
      ratio: 10,
      imgUrl: "",
      category: "",
      tag: "",
      subOptions: [
        {
          name: "",
          imgUrl: "",
          description: "",
        },
      ],
    },
    {
      id: 1,
      name: "Select Option2",
      description: "Select Option2",
      price: 213120,
      ratio: 20,
      imgUrl: "",
      category: "",
      tag: "",
      subOptions: [
        {
          name: "",
          imgUrl: "",
          description: "",
        },
      ],
    },
    {
      id: 2,
      name: "Select Option3",
      description: "Select Option3",
      price: 4123210,
      ratio: 30,
      imgUrl: "",
      category: "",
      tag: "",
      subOptions: [
        {
          name: "",
          imgUrl: "",
          description: "",
        },
      ],
    },
  ],
};

type OptionDispatch = (action: OptionAction) => void;

const optionReducer = (
  state: OptionState,
  action: OptionAction
): OptionState => {
  switch (action.type) {
    case "SELECT_OPTION":
      return {
        ...state,
        selectedOption: action.payload.selectedOption,
        consideredOption: action.payload.consideredOption,
      };
    case "SET_OPTION_LIST":
      return {
        ...state,
        optionList: action.payload.optionList,
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