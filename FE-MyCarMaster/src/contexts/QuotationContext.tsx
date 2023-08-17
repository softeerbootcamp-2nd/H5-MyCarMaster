import { useReducer, createContext, useContext } from "react";
import { QuotationAction, QuotationState } from "../types/quotation.types";

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
    exteriorColorQuotation: { name: "", price: 0, imgUrl: "" },
    interiorColorQuotation: { name: "", price: 0, imgUrl: "" },
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
          action.payload!.navigationId === -1
            ? (0 as number)
            : action.payload!.navigationId === 8
            ? (7 as number)
            : (action.payload!.navigationId as number),
        isFirst: action.payload!.isFirst
          ? (action.payload!.isFirst as boolean[])
          : (state.isFirst as boolean[]),
      };
    case "SET_TRIM_QUOTATION":
      return {
        ...state,
        trimQuotation: {
          trimQuotation: {
            name: action.payload!.name as string,
            price: action.payload!.price as number,
          },
        },
      };
    case "SET_DETAIL_QUOTATION":
      return {
        ...state,
        detailQuotation: {
          ...state.detailQuotation,
          [action.payload!.type as string]: {
            name: action.payload!.name as string,
            price: action.payload!.price as number,
          },
        },
      };
    case "SET_CAR_PAINT_QUOTATION":
      return {
        ...state,
        carPaintQuotation: {
          ...state.carPaintQuotation,
          [action.payload!.type as string]: {
            name: action.payload!.name as string,
            price: action.payload!.price as number,
            imgUrl: action.payload!.imgUrl as string,
          },
        },
      };
    case "SET_SELECT_QUOTATION": {
      const { id, name, price, imgUrl, category } = action.payload!;
      const isOptionSelected = state.optionQuotation.selectedQuotation.some(
        (option) => option.id === (id as number)
      );

      return {
        ...state,
        optionQuotation: {
          ...state.optionQuotation,
          consideredQuotation: state.optionQuotation.consideredQuotation.filter(
            (option) => option.id !== (id as number)
          ),
          selectedQuotation: isOptionSelected
            ? state.optionQuotation.selectedQuotation.filter(
                (option) => option.id !== (id as number)
              )
            : [
                ...state.optionQuotation.selectedQuotation,
                {
                  id: id as number,
                  name: name as string,
                  price: price as number,
                  imgUrl: imgUrl as string,
                  category: category as string,
                },
              ],
        },
      };
    }
    case "SET_CONSIDER_QUOTATION": {
      const { id, name, price, imgUrl, category } = action.payload!;
      const isOptionConsidered = state.optionQuotation.consideredQuotation.some(
        (option) => option.id === (id as number)
      );
      return {
        ...state,
        optionQuotation: {
          ...state.optionQuotation,
          selectedQuotation: state.optionQuotation.selectedQuotation.filter(
            (option) => option.id !== (id as number)
          ),
          consideredQuotation: isOptionConsidered
            ? state.optionQuotation.consideredQuotation.filter(
                (option) => option.id !== (id as number)
              )
            : [
                ...state.optionQuotation.consideredQuotation,
                {
                  id: id as number,
                  name: name as string,
                  price: price as number,
                  imgUrl: imgUrl as string,
                  category: category as string,
                },
              ],
        },
      };
    }
    case "RESET_QUOTATION":
      return initialQuotationState;
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
