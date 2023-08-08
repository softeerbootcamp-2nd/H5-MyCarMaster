import { useReducer, createContext, useContext } from "react";
import { OptionAction, OptionState, OptionType } from "../types/options.types";


const initialOptionState: OptionState = {
  selectedOption: [],
  consideredOption: [],
  optionCategoryId: 0,
  optionList: [
    {
      id: 0,
      name: "Select Option",
      description: "Select Option",
      price: 0,
      ratio: 10,
      imgUrl: "",
      category: "",
      tag: null,
      subOptions: [
        {
          name: "후방 감지 어쩌구",
          imgUrl: "S3 Url",
          description:
            "주차 또는 출차 시 저속 후진 중 후방카메라와 센서로 정후면에 위치한 보행자 및 장애물과의 충돌이 예상되면 운전자에게 경고하고 차량의 제동을 제어하여 충돌방지를 보조합니다.",
        },
        {
          name: "후방 감지 저쩌구",
          imgUrl: "S3 Url",
          description: "설명",
        },
        {
          name: "페이지네이션 고고",
          imgUrl: "S3 Url",
          description: "설명",
        },
      ],
    },
    {
      id: 2,
      category: "STYLE_PERFORMANCE",
      name: "20인치 다크 스파터링 휠",
      price: 400000,
      ratio: 22,
      imgUrl: "S3 Url",
      description: "현대자동차의 기술력과 노하우가 결합된...",
      tag: "N Performance",
      subOptions: null,
    },
    {
      id: 3,
      category: "CAR_PROTECTION",
      name: "차량 보호 필름",
      price: 50000,
      ratio: 30,
      imgUrl: "S3 Url",
      description: "흠집으로 부터 차량을 보호하고 싶다면?....",
      tag: "H Genuine Accessories",
      subOptions: null,
    },
    {
      id: 4,
      category: "CONVENIENCE",
      name: "2열 통풍 시트",
      price: 400000,
      ratio: 22,
      imgUrl: "S3 Url",
      description: "시동이 걸린 상태에서 해당 좌석....",
      tag: null,
      subOptions: null,
    },
    {
      id: 5,
      category: "CONVENIENCE",
      name: "적외선 무릎 워머",
      price: 800000,
      ratio: 25,
      imgUrl: "S3 Url",
      description: "무릎까지 따뜻한 드라이빙을 원한다면,...",
      tag: "H Genuine Accessories",
      subOptions: null,
    },
    {
      id: 6,
      category: "CONVENIENCE",
      name: "빌트인 공기 청정기",
      price: 800000,
      ratio: 22,
      imgUrl: "S3 Url",
      description: "더 깨끗한 차량 내 공기를 위해...",
      tag: "H Genuine Accessories",
      subOptions: null,
    },
    {
      id: 3,
      name: "Select Option4",
      description: "Select Option4",
      price: 4123210,
      ratio: 30,
      imgUrl: "S3 Url",
      description: "흠집으로 부터 차량을 보호하고 싶다면?....",
      tag: "H Genuine Accessories",
      subOptions: null,
    },
    {
      id: 4,
      category: "CONVENIENCE",
      name: "2열 통풍 시트",
      price: 400000,
      ratio: 22,
      imgUrl: "S3 Url",
      description: "시동이 걸린 상태에서 해당 좌석....",
      tag: null,
      subOptions: null,
    },
    {
      id: 5,
      category: "CONVENIENCE",
      name: "적외선 무릎 워머",
      price: 800000,
      ratio: 25,
      imgUrl: "S3 Url",
      description: "무릎까지 따뜻한 드라이빙을 원한다면,...",
      tag: "H Genuine Accessories",
      subOptions: null,
    },
    {
      id: 6,
      category: "CONVENIENCE",
      name: "빌트인 공기 청정기",
      price: 800000,
      ratio: 22,
      imgUrl: "S3 Url",
      description: "더 깨끗한 차량 내 공기를 위해...",
      tag: "H Genuine Accessories",
      subOptions: null,
    },
  ],
};

type OptionDispatch = (action: OptionAction) => void;

const optionReducer = (
  state: OptionState,
  action: OptionAction
): OptionState => {
  switch (action.type) {
    case "SET_CHOICE_OPTION": {
      const { where, id } = action.payload;
      const existingIds = state[where];
      if (existingIds.includes(id)) {
        return {
          ...state,
          [where]: existingIds.filter((existingId) => existingId !== id),
        };
      } else {
        return {
          ...state,
          [where]: [...existingIds, id],
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
