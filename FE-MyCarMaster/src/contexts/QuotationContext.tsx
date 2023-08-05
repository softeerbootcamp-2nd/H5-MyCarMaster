import { useReducer, createContext, useContext } from "react";

type quotation = {
  name: string;
  price: number;
  imgUrl?: string;
  category?: string;
  description?: string;
};

type QuotationState = {
  navigationId: number;
  isFirst: boolean[];
  trimQuotation: quotation[];
  engineQuotation: quotation[];
  wheelDriveQuotation: quotation[];
  bodyTypeQuotation: quotation[];
  exteriorColorQuotation: quotation[];
  interiorColorQuotation: quotation[];
  selectedQuotation: quotation[];
  consideredQuotation: quotation[];
};

type QuotationAction =
  | {
      type: "NAVIGATE";
      payload: { navigationId: number; isFirst?: boolean[] };
    }
  | {
      type: "SET_QUOTATION";
      payload: {
        trimQuotation?: quotation[];
        engineQuotation?: quotation[];
        wheelDriveQuotation?: quotation[];
        bodyTypeQuotation?: quotation[];
        exteriorColorQuotation?: quotation[];
        interiorColorQuotation?: quotation[];
        selectedQuotation?: quotation[];
        consideredQuotation?: quotation[];
      };
    };

const initialQuotationState: QuotationState = {
  navigationId: 0,
  isFirst: [true, true, true, true, true, true, true],
  trimQuotation: [],
  engineQuotation: [],
  wheelDriveQuotation: [],
  bodyTypeQuotation: [],
  exteriorColorQuotation: [],
  interiorColorQuotation: [],
  selectedQuotation: [],
  consideredQuotation: [],
};

type QuotationDispatch = (action: QuotationAction) => void;

const quotationReducer = (
  state: QuotationState,
  action: QuotationAction
): QuotationState => {
  switch (action.type) {
    case "NAVIGATE":
      return {
        ...state,
        navigationId:
          action.payload.navigationId === -1
            ? 0
            : action.payload.navigationId === 7
            ? 6
            : action.payload.navigationId,
      };
    case "SET_QUOTATION":
      return {
        ...state,
        trimQuotation: action.payload.trimQuotation
          ? action.payload.trimQuotation
          : state.trimQuotation,

        engineQuotation: action.payload.engineQuotation
          ? action.payload.engineQuotation
          : state.engineQuotation,

        wheelDriveQuotation: action.payload.wheelDriveQuotation
          ? action.payload.wheelDriveQuotation
          : state.wheelDriveQuotation,

        bodyTypeQuotation: action.payload.bodyTypeQuotation
          ? action.payload.bodyTypeQuotation
          : state.bodyTypeQuotation,

        exteriorColorQuotation: action.payload.exteriorColorQuotation
          ? action.payload.exteriorColorQuotation
          : state.exteriorColorQuotation,

        interiorColorQuotation: action.payload.interiorColorQuotation
          ? action.payload.interiorColorQuotation
          : state.interiorColorQuotation,

        selectedQuotation: action.payload.selectedQuotation
          ? action.payload.selectedQuotation
          : state.selectedQuotation,

        consideredQuotation: action.payload.consideredQuotation
          ? action.payload.consideredQuotation
          : state.consideredQuotation,
      };
    default:
      return state;
  }
};

const QuotationStateContext = createContext<QuotationState | undefined>(
  undefined
);
const QuotationDispatchContext = createContext<QuotationDispatch | undefined>(
  undefined
);

export const QuotationProvider = ({
  children,
}: {
  children: React.ReactNode;
}) => {
  const [state, dispatch] = useReducer(quotationReducer, initialQuotationState);
  return (
    <QuotationStateContext.Provider value={state}>
      <QuotationDispatchContext.Provider value={dispatch}>
        {children}
      </QuotationDispatchContext.Provider>
    </QuotationStateContext.Provider>
  );
};

export const useQuotationState = () => {
  const state = useContext(QuotationStateContext);
  if (!state) throw new Error("Cannot find QuotationProvider");
  return state;
};

export const useQuotationDispatch = () => {
  const dispatch = useContext(QuotationDispatchContext);
  if (!dispatch) throw new Error("Cannot find QuotationProvider");
  return dispatch;
};

export const useQuotation = () =>
  [useQuotationState(), useQuotationDispatch()] as const;

export default QuotationProvider;
