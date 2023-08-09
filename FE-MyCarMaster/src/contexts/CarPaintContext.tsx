import { useReducer, createContext, useContext } from "react";

type exteriorColors = {
  id: number;
  name: string;
  price: number;
  ratio: number;
  colorImgUrl: string;
  coloredImgUrl: string;
};

type interiorColors = {
  id: number;
  name: string;
  price: number;
  ratio: number;
  colorImgUrl: string;
  coloredImgUrl: string;
};

type CarPaintState = {
  exteriorId: number;
  interiorId: number;
  exteriorList: exteriorColors[];
  interiorList: interiorColors[];
};

type CarPaintAction = {
  type:
    | "SELECT_EXTERIOR"
    | "SELECT_INTERIOR"
    | "SET_EXTERIOR_LIST"
    | "SET_INTERIOR_LIST";
  payload: {
    exteriorId?: number;
    interiorId?: number;
    exteriorList?: exteriorColors[];
    interiorList?: interiorColors[];
  };
};

const initialCarPaintState: CarPaintState = {
  exteriorId: 0,
  interiorId: 0,
  exteriorList: [
    {
      id: 0,
      name: "Select Exterior1",
      price: 10,
      ratio: 0,
      colorImgUrl: "",
      coloredImgUrl: "/images/exterior/black/image_001.png",
    },
    {
      id: 1,
      name: "Select Exterior2",
      price: 20,
      ratio: 0,
      colorImgUrl: "",
      coloredImgUrl: "/images/exterior/black/image_002.png",
    },
    {
      id: 2,
      name: "Select Exterior3",
      price: 30,
      ratio: 0,
      colorImgUrl: "",
      coloredImgUrl: "/images/exterior/black/image_003.png",
    },
    {
      id: 3,
      name: "Select Exterior4",
      price: 40,
      ratio: 0,
      colorImgUrl: "",
      coloredImgUrl: "/images/exterior/black/image_004.png",
    },
    {
      id: 4,
      name: "Select Exterior5",
      price: 0,
      ratio: 0,
      colorImgUrl: "",
      coloredImgUrl: "/images/exterior/black/image_005.png",
    },
    {
      id: 5,
      name: "Select Exterior6",
      price: 0,
      ratio: 0,
      colorImgUrl: "",
      coloredImgUrl: "/images/exterior/black/image_006.png",
    },
  ],
  interiorList: [
    {
      id: 0,
      name: "Select Interior",
      price: 0,
      ratio: 0,
      colorImgUrl: "",
      coloredImgUrl: "/images/interior/interiorImage.png",
    },
    {
      id: 1,
      name: "Select Interior2",
      price: 10,
      ratio: 0,
      colorImgUrl: "",
      coloredImgUrl: "", // 일부러 안넣음.
    },
    {
      id: 2,
      name: "Select Interior3",
      price: 20,
      ratio: 0,
      colorImgUrl: "",
      coloredImgUrl: "/images/interior/interiorImage.png",
    },
  ],
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
        exteriorList: action.payload.exteriorList as exteriorColors[],
      };
    case "SET_INTERIOR_LIST":
      return {
        ...state,
        interiorList: action.payload.interiorList as interiorColors[],
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
