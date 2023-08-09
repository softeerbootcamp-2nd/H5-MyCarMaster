import { useReducer, createContext, useContext } from "react";
import Diesel from "../assets/images/Diesel.png";
import Gasoline from "../assets/images/Gasoline.png";
import TWO_WD from "../assets/images/2WD.png";
import FOUR_WD from "../assets/images/4WD.png";
import BodyType_7 from "../assets/images/BodyType_7.png";
import BodyType_8 from "../assets/images/BodyType_8.png";

type engines = {
  id: number;
  name: string;
  description: string;
  price: number;
  ratio: number;
  imgUrl: string;
  power: number;
  torque: number;
  fuelMin: number;
  fuelMax: number;
};

type wheelDrives = {
  id: number;
  name: string;
  description: string;
  price: number;
  ratio: number;
  imgUrl: string;
};

type bodyTypes = {
  id: number;
  name: string;
  description: string;
  price: number;
  ratio: number;
  imgUrl: string;
};

type DetailState = {
  engineId: number;
  wheelDriveId: number;
  bodyTypeId: number;
  engineList: engines[];
  wheelDriveList: wheelDrives[];
  bodyTypeList: bodyTypes[];
};

type DetailAction = {
  type: "SELECT_DETAIL" | "SET_DETAIL_LIST";
  payload: {
    engineId?: number;
    wheelDriveId?: number;
    bodyTypeId?: number;
    engineList?: engines[];
    wheelDriveList?: wheelDrives[];
    bodyTypeList?: bodyTypes[];
  };
};

const initialDetailState: DetailState = {
  engineId: 0,
  wheelDriveId: 0,
  bodyTypeId: 0,
  engineList: [
    {
      id: 0,
      name: "가솔린 3.8",
      description:
        "우수한 가속 성능으로 안정적이고 엔진의 진동이 적어 조용한 드라이빙이 가능합니다.",
      price: 0,
      ratio: 0,
      imgUrl: Gasoline,
      power: 295,
      torque: 36.2,
      fuelMin: 8.0,
      fuelMax: 9.2,
    },
    {
      id: 1,
      name: "디젤 2.2",
      description:
        "높은 토크로 파워풀한 드라이빙이 가능하고 연비 효율이 우수합니다.",
      price: 1480000,
      ratio: 22,
      imgUrl: Diesel,
      power: 202,
      torque: 45.0,
      fuelMin: 11.4,
      fuelMax: 12.4,
    },
  ],
  wheelDriveList: [
    {
      id: 0,
      name: "2WD",
      description:
        "엔진 동력이 전륜 후륜 중 하나로 전달되어 움직입니다. 차체가 가벼워 연료 효율이 높습니다.",
      price: 0,
      ratio: 0,
      imgUrl: TWO_WD,
    },
    {
      id: 1,
      name: "4WD",
      description:
        "상시 4륜 구동 시스템으로 주행 환경에 맞춰 전후륜 구동력을 자동배분해 안전성을 높입니다",
      price: 2370000,
      ratio: 0,
      imgUrl: FOUR_WD,
    },
  ],
  bodyTypeList: [
    {
      id: 0,
      name: "7인승",
      description:
        "2열 가운데 시트를 없애 2열 탑승객의 편의는 물론, 3열 탑승객의 승하차가 편리합니다",
      price: 0,
      ratio: 0,
      imgUrl: BodyType_7,
    },
    {
      id: 1,
      name: "8인승",
      description:
        "1열 2명, 2열 3명, 3열 3명이 탑승할 수 있는 구조로, 많은 인원이 탑승할 수 있습니다",
      price: 0,
      ratio: 0,
      imgUrl: BodyType_8,
    },
  ],
};

type DetailDispatch = (action: DetailAction) => void;

const detailReducer = (
  state: DetailState,
  action: DetailAction
): DetailState => {
  switch (action.type) {
    case "SELECT_DETAIL":
      return {
        ...state,
        engineId: action.payload.engineId as number,
        wheelDriveId: action.payload.wheelDriveId as number,
        bodyTypeId: action.payload.bodyTypeId as number,
      };

    case "SET_DETAIL_LIST":
      return {
        ...state,
        engineList: action.payload.engineList as engines[],
        wheelDriveList: action.payload.wheelDriveList as wheelDrives[],
        bodyTypeList: action.payload.bodyTypeList as bodyTypes[],
      };
    default:
      return state;
  }
};

const DetailStateContext = createContext<DetailState | undefined>(undefined);
const DetailDispatchContext = createContext<DetailDispatch | undefined>(
  undefined
);

export const DetailProvider = ({ children }: { children: React.ReactNode }) => {
  const [state, dispatch] = useReducer(detailReducer, initialDetailState);
  return (
    <DetailStateContext.Provider value={state}>
      <DetailDispatchContext.Provider value={dispatch}>
        {children}
      </DetailDispatchContext.Provider>
    </DetailStateContext.Provider>
  );
};

export const useDetailState = () => {
  const state = useContext(DetailStateContext);
  if (!state) throw new Error("Cannot find DetailProvider");
  return state;
};

export const useDetailDispatch = () => {
  const dispatch = useContext(DetailDispatchContext);
  if (!dispatch) throw new Error("Cannot find DetailProvider");
  return dispatch;
};

export const useDetail = () => [useDetailState(), useDetailDispatch()] as const;

export default DetailProvider;
