import { useReducer, createContext, useContext } from "react";
import {
  TrimQuotationType,
  DetailQuotationType,
  CarPaintQuotationType,
  OptionQuotationType,
} from "../types/quotation.types";

type QuotationState = {
  navigationId: undefined | number;
  isFirst: boolean[];
  trimQuotation: TrimQuotationType;
  detailQuotation: DetailQuotationType;
  carPaintQuotation: CarPaintQuotationType;
  optionQuotation: OptionQuotationType;
};

type QuotationAction = {
  type:
    | "NAVIGATE"
    | "SET_TRIM_QUOTATION"
    | "SET_DETAIL_QUOTATION"
    | "SET_SELECT_QUOTATION"
    | "SET_CONSIDER_QUOTATION"
    | "SET_CAR_PAINT_QUOTATION";
  payload: {
    navigationId?: number;
    isFirst?: boolean[];
    type?: string;
    name?: string | undefined;
    price?: number | undefined;
  };
};

const initialQuotationState: QuotationState = {
  navigationId: 0,
  isFirst: [false, true, true, true, true, true, true],
  trimQuotation: {
    trimQuotation: {
      name: "",
      price: 0,
    },
  },
  detailQuotation: {
    engineQuotation: { name: "", price: 0 },
    wheelDriveQuotation: { name: "", price: 0 },
    bodyTypeQuotation: { name: "", price: 0 },
  },
  carPaintQuotation: {
    exteriorColorQuotation: { name: "", price: 0 },
    interiorColorQuotation: { name: "", price: 0 },
  },
  optionQuotation: {
    selectedQuotation: [],
    consideredQuotation: [],
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
            ? (0 as number)
            : action.payload.navigationId === 8
            ? (7 as number)
            : action.payload.navigationId,
        isFirst: action.payload.isFirst
          ? (action.payload.isFirst as boolean[])
          : (state.isFirst as boolean[]),
      };
    case "SET_TRIM_QUOTATION":
      return {
        ...state,
        trimQuotation: {
          trimQuotation: {
            name: action.payload.name as string,
            price: action.payload.price as number,
          },
        },
      };
    case "SET_DETAIL_QUOTATION":
      return {
        ...state,
        detailQuotation: {
          ...state.detailQuotation,
          [action.payload.type as string]: {
            name: action.payload.name as string,
            price: action.payload.price as number,
          },
        },
      };
    case "SET_CAR_PAINT_QUOTATION":
      return {
        ...state,
        carPaintQuotation: {
          ...state.carPaintQuotation,
          [action.payload.type as string]: {
            name: action.payload.name as string,
            price: action.payload.price as number,
          },
        },
      };
    case "SET_SELECT_QUOTATION": {
      const { name, price } = action.payload;
      const isOptionSelected = state.optionQuotation.selectedQuotation.some(
        (option) => option.name === (name as string)
      );

      // 이미 선택된 옵션이 있는 경우 해당 옵션을 제거
      if (isOptionSelected) {
        return {
          ...state,
          optionQuotation: {
            ...state.optionQuotation,
            selectedQuotation: state.optionQuotation.selectedQuotation.filter(
              (option) => option.name !== (name as string)
            ),
          },
        };
      } else {
        // 선택된 옵션이 없는 경우 해당 옵션을 추가
        return {
          ...state,
          optionQuotation: {
            ...state.optionQuotation,
            selectedQuotation: [
              ...state.optionQuotation.selectedQuotation,
              {
                name: name as string,
                price: price as number,
              },
            ],
          },
        };
      }
    }
    case "SET_CONSIDER_QUOTATION":
      return {
        ...state,
        optionQuotation: {
          ...state.optionQuotation,
          consideredQuotation: [
            ...state.optionQuotation.consideredQuotation,
            {
              name: action.payload.name as string,
              price: action.payload.price as number,
            },
          ],
        },
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
