import { useReducer, createContext, useContext } from "react";
import {
  TrimQuotationType,
  DetailQuotationType,
  CarPaintQuotationType,
  OptionQuotationType,
} from "../types/quotation.types";

type QuotationState = {
  navigationId: number;
  isFirst: boolean[];
  trimQuotation: TrimQuotationType;
  detailQuotation: DetailQuotationType;
  carPaintQuotation: CarPaintQuotationType;
  optionQuotation: OptionQuotationType;
};

type QuotationAction = {
  type: "NAVIGATE";
  payload: { navigationId: number; isFirst?: boolean[] };
};

const initialQuotationState: QuotationState = {
  navigationId: 0,
  isFirst: [false, true, true, true, true, true, true],
  trimQuotation: {
    trimQuotation: { name: "Le Blanc (르블랑)", price: 1000 },
  },
  detailQuotation: {
    engineQuotation: { name: "가솔린3.8", price: 20 },
    wheelDriveQuotation: { name: "4WD", price: 30 },
    bodyTypeQuotation: { name: "7인승", price: 40 },
  },
  carPaintQuotation: {
    exteriorColorQuotation: { name: "그라파이드 그레이 메탈릭", price: 50 },
    interiorColorQuotation: { name: "퀼팅천연(블랙)", price: 60 },
  },
  optionQuotation: {
    selectedQuotation: [
      { name: "파노라마 선루프", price: 70 },
      { name: "헤드업 디스플레이", price: 80 },
      { name: "하이패스", price: 90 },
    ],
    consideredQuotation: [
      { name: "파노라마 선루프", price: 70 },
      { name: "헤드업 디스플레이", price: 80 },
      { name: "하이패스", price: 90 },
    ],
  },
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
        isFirst: action.payload.isFirst
          ? action.payload.isFirst
          : state.isFirst,
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
