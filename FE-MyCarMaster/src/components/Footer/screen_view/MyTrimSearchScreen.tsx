import styled from "styled-components";
import SearchTrimBox from "../../common/SearchTrimBox/SearchTrimBox";
import OptionCheckBox from "../../common/CheckBox/OptionCheckBox";
import Button from "../../common/Button/Button";
import { Fragment, useState } from "react";
import theme from "../../../styles/Theme";
import { useTrimState } from "../../../contexts/TrimContext";
import { QuotationType } from "../../../types/quotation.types";
import { DescriptionOptionModalProps } from "../../../types/options.types";
import OptionDescriptionModal from "../../common/OptionDescriptionModal/OptionDescriptionModal";

//dummy data
const data = [
  {
    id: 100,
    name: "2열 통풍시트",
    imgUrl:
      "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/option/second-row-ventilation-seat.png",
    summary:
      "2열 통풍시트'를 통해\n2열의 동승자까지 함께 시원한 드라이빙을 즐겨보세요",
    description:
      "시동이 걸린 상태에서 해당 좌석의 통풍 스위치를 누르면 표시등이 켜지면서 해당 좌석에 바람이 나오는 편의장치입니다.",
    subOptions: null,
    filter: {
      unavailableTrimIds: [],
      additionalTrimIds: [2, 1],
      defaultTrimIds: [3, 4],
    },
    appliedOption: {
      id: 100,
      category: "CONVENIENCE",
      name: "2열 통풍시트",
      price: 400000,
      imgUrl:
        "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/option/second-row-ventilation-seat.png",
    },
  },
  {
    id: 19,
    name: "후측방 충돌 경고(주행)",
    imgUrl: "https://h5-test-bucket.s3.ap-northeast-2.amazonaws.com/1.png",
    summary:
      "운전 중 돌발 상황에 도움이 필요하다면? 운전 중 돌발상황을 예방하는 '후측방 충돌 경고'를 사용해보세요.",
    description:
      "차로 변경을 위하여 방향지시등 스위치 조작 시, 후측방 충돌 위험이 감지되면 경고를 해줍니다.",
    subOptions: null,
    filter: {
      unavailableTrimIds: [],
      additionalTrimIds: [1],
      defaultTrimIds: [2, 3, 4],
    },
    appliedOption: {
      id: 125,
      category: "SAFE",
      name: "주차보조 시스템 1",
      price: 1090000,
      imgUrl: "https://h5-test-bucket.s3.ap-northeast-2.amazonaws.com/1.png",
    },
  },
  {
    id: 49,
    name: "계기판 클러스터(12.3인치 컬러 LCD)",
    imgUrl: "https://h5-test-bucket.s3.ap-northeast-2.amazonaws.com/1.png",
    summary:
      "컬러 LCD 클러스터'를 통해 운전 중에도 다양한 정보를 쉽게 확인할 수 있어요.",
    description:
      "컬러 LCD 클러스터(1,920x720)는 시인성이 높고 정보 파악이 용이하며, 주행모드별 차별화된 그래픽으로 즐거운 드라이빙 환경을 제공합니다.",
    subOptions: null,
    filter: {
      unavailableTrimIds: [1],
      additionalTrimIds: [],
      defaultTrimIds: [2, 3, 4],
    },
    appliedOption: {
      id: 49,
      category: "INTERNAL",
      name: "계기판 클러스터(12.3인치 컬러 LCD)",
      price: 0,
      imgUrl: "https://h5-test-bucket.s3.ap-northeast-2.amazonaws.com/1.png",
    },
  },
  {
    id: 114,
    name: "내비게이션 기반 스마트 크루즈 컨트롤(진출입로)",
    imgUrl:
      "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/option/navigation-based-smart.png",
    summary:
      "효과적인 감속을 돕는 '스마트 크루즈'를 통해 운전 중 돌발 상황을 예방할 수 있어요.",
    description:
      "스마트 크루즈 작동 중 고속도로/도시고속도로/자동차전용 도로 내 고속도로 진출입로 주행 시 차로를 판단하여 사전감속 또는 최적 속도에 맞추어 감속을 진행합니다.",
    subOptions: null,
    filter: {
      unavailableTrimIds: [1],
      additionalTrimIds: [2, 3],
      defaultTrimIds: [4],
    },
    appliedOption: {
      id: 93,
      category: "SAFE",
      name: "현대스마트센스 1",
      price: 790000,
      imgUrl: "https://h5-test-bucket.s3.ap-northeast-2.amazonaws.com/1.png",
    },
  },
  {
    id: 112,
    name: "헤드업 디스플레이",
    imgUrl: "https://h5-test-bucket.s3.ap-northeast-2.amazonaws.com/1.png",
    summary:
      "전면부 유리에 비치는 '헤드업 디스플레이'를 통해 전방 주시 중 주행 정보를 직관적으로 파악할 수 있어요. ",
    description:
      "주요 주행 정보를 전면 윈드실드에 표시하며, 밝기가 최적화되어 주간에도 시인성이 뛰어납니다.",
    subOptions: null,
    filter: {
      unavailableTrimIds: [1],
      additionalTrimIds: [2, 3],
      defaultTrimIds: [4],
    },
    appliedOption: {
      id: 92,
      category: "SAFE",
      name: "컴포트 2",
      price: 1090000,
      imgUrl: "https://h5-test-bucket.s3.ap-northeast-2.amazonaws.com/1.png",
    },
  },
  {
    id: 129,
    name: "전후석 통합 터치 공조 컨트롤",
    imgUrl:
      "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/option/front-and-rear-integrated-touch-control.png",
    summary:
      "미세먼지가 걱정된다면 '전후석 통합 터치 공조 컨트롤'을 추천해요. 공기청정모드를 통해 쾌적한 내부 환경을 제공해요.",
    description:
      "터치식으로 2열 공조 제어까지 가능하여 편리한 터치 타입 공조 패널에 공기질 센서, 마이크로 에어 필터, 운전석 공조 연동 자동 제어 등의 공기청정모드가 적용되어 실내 미세먼지를 획기적으로 저감하며 쾌적한 실내 환경을 제공합니다.",
    subOptions: null,
    filter: {
      unavailableTrimIds: [1, 2],
      additionalTrimIds: [],
      defaultTrimIds: [3, 4],
    },
    appliedOption: {
      id: 129,
      category: "CONVENIENCE",
      name: "전후석 통합 터치 공조 컨트롤",
      price: 0,
      imgUrl:
        "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/option/front-and-rear-integrated-touch-control.png",
    },
  },
  {
    id: 130,
    name: "KRELL 프리미엄 사운드",
    imgUrl:
      "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/option/krell-premium-sound.png",
    summary:
      "차 안에서도 콘서트장에 온 것 같은 사운드를 원한다면 'KRELL 프리미엄 사운드'를 추천해요",
    description:
      "음향의 세밀함과 공간감, 다이내믹함을 추구하며 세계 유수의 사운드 어워드를 수상한 세계적인 하이앤드 오디오 시스템 브랜드인 크렐 사운드 시스템을 적용하였습니다.",
    subOptions: null,
    filter: {
      unavailableTrimIds: [1, 2],
      additionalTrimIds: [3],
      defaultTrimIds: [4],
    },
    appliedOption: {
      id: 132,
      category: "MULTIMEDIA",
      name: "KRELL 사운드 패키지",
      price: 690000,
      imgUrl: "https://h5-test-bucket.s3.ap-northeast-2.amazonaws.com/1.png",
    },
  },
  {
    id: 133,
    name: "캘리그래피 전용 디자인",
    imgUrl:
      "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/option/calligraphy-only-design.png",
    summary:
      "휠, 라디에이터 그릴 & 가니쉬, 인테이크 그릴, 바디컬러 클래딩, 프론트 & 리어 크롬 스키드 플레이트에 적용되는 캘리그라피만의 디자인을 즐겨보세요.",
    description: null,
    subOptions: null,
    filter: {
      unavailableTrimIds: [1, 2, 3],
      additionalTrimIds: [],
      defaultTrimIds: [4],
    },
    appliedOption: {
      id: 133,
      category: "EXTERNAL",
      name: "캘리그래피 전용 디자인",
      price: 0,
      imgUrl:
        "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/option/calligraphy-only-design.png",
    },
  },
  {
    id: 134,
    name: "VIP 패키지",
    imgUrl: "https://h5-test-bucket.s3.ap-northeast-2.amazonaws.com/1.png",
    summary:
      "보다 고급스러운 인테리어를 원한다면 캘리그라피만의 VIP 패키지를 통해 2열 추가 편의 기능과 더욱 고급화된 실내 인테리어를 적용할 수 있어요",
    description:
      "양문형 2열 센터 콘솔 암레스트로 콘솔 개폐시 편의성이 우수합니다. 또한 2열 콘솔 부위에 앰비언트 무드램프를 더하여 2열 차별화와 고급감을 향상시켰습니다.\n- 공기청정기 : H13등급 헤파 필터와 활성탄 및 제올라이트 필터를 적용하여 미세먼지와 유해가스를 효과적으로 필터링하여 2열 좌석 중심으로 신선한 공기를 공급합니다. 미세먼지 센서로(PM2.5 레이저) 실내 미세먼지를 측정하여 풍량을 자동 제어합니다.\n- 터치 스위치 : 콘솔 공기청정기, 2열 냉온컵홀더, 후석엔터테인먼트 시스템을 제어 가능합니다.",
    subOptions: [
      {
        name: "2열 센터 콘솔(양문형 암레스트, 공기청정기, 냉온장 컵홀더, 앰비언트 무드램프, 터치 스위치 포함)",
        imgUrl:
          "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/option/2-center-consol.png",
        description: null,
      },
      {
        name: "VIP 전용 고급형 카매트(1/2/3열)",
        imgUrl:
          "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/option/vip-car-mat.png",
        description:
          "두터운 두께와 더욱 촘촘한 융 원단을 적용하여 보다 고급스러운 인테리어 감성을 제공합니다.",
      },
      {
        name: "스피커 내장형 윙타입 헤드레스트(2열)",
        imgUrl:
          "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/option/speaker-wing-type-headrest.png",
        description:
          "저음역을 강화하고 플랫 주파수 응답 성능을 확보하여 풍부한 베이스와 고퀄리티 사운드 감상이 가능합니다. 또한'후석 엔터테인먼트 시스템’과 연동되어 좌/우 헤드레스트 각각 개별적인 사운드 감상이 가능합니다.",
      },
      {
        name: "도어트림 스마트폰 무선충전기(2열)",
        imgUrl:
          "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/option/door-wireless-charger.png",
        description:
          "뒷좌석 도어에 별도의 케이블 없이 무선으로 스마트폰 충전이 가능한 무선 충전기를 장착하여 뒷좌석 탑승객의 편의를 높였습니다.",
      },
      {
        name: "후석 엔터테인먼트 시스템(2열)",
        imgUrl:
          "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/option/rear-entertainment-system.png",
        description: null,
      },
    ],
    filter: {
      unavailableTrimIds: [1, 2, 3],
      additionalTrimIds: [4],
      defaultTrimIds: [],
    },
    appliedOption: {
      id: 134,
      category: "CONVENIENCE",
      name: "VIP 패키지",
      price: 5740000,
      imgUrl: "https://h5-test-bucket.s3.ap-northeast-2.amazonaws.com/1.png",
    },
  },
];

type MyTrimSearchScreenProps = {
  $loading: boolean;
  $show: boolean;
  onClick?: (e: React.MouseEvent<HTMLDivElement, MouseEvent>) => void;
  onSearch?: (appiedOption: QuotationType[], selected: number) => void;
};

type IDDataProps = {
  optionId?: number;
  unavailableTrimIds: number[];
  additionalTrimIds: number[];
  defaultTrimIds: number[];
};

type StatusType = "default" | "choice" | "none";
type OptionType = "default" | "add" | "none";

export default function MyTrimSearchScreen({
  $loading,
  $show,
  onClick,
  onSearch,
}: MyTrimSearchScreenProps) {
  const [idData, setIdData] = useState<IDDataProps[]>([]);
  const [selected, setSelected] = useState<number | null>(null);
  const { trimList } = useTrimState();

  const [isDescriptionModalOpen, setIsDescriptionModalOpen] = useState(false);
  const [detailOption, setDetailOption] =
    useState<DescriptionOptionModalProps>();

  const dataChange = (id: number, filter: IDDataProps) => {
    setSelected(null);
    const index = idData.findIndex((data) => data.optionId === id);
    if (index === -1) {
      setIdData([...idData, { ...filter, optionId: id }]);
    } else {
      const newIdData = idData.filter((data) => data.optionId !== id);
      setIdData(newIdData);
    }
  };

  const CheckfilterOption = (
    trimId: number
  ): { status: StatusType; isOption: OptionType } => {
    if (idData.length === 0) return { status: "default", isOption: "none" };

    const isUnavailable = idData.some((data) =>
      data.unavailableTrimIds.includes(trimId)
    );

    if (isUnavailable) return { status: "none", isOption: "none" };

    for (const data of idData)
      if (!data.defaultTrimIds.includes(trimId))
        return { status: "default", isOption: "add" };

    return { status: "default", isOption: "default" };
  };

  const onSearchHandler = (idData: IDDataProps[], selected: number) => {
    const appliedOptions = idData.map((optionData) => {
      if (optionData.defaultTrimIds.includes(selected)) return null;
      const appliedOption = data.find(
        (option) => option.id === optionData.optionId
      )?.appliedOption;
      return appliedOption;
    });

    const appliedOptionsFilter = appliedOptions.filter(
      (option) => option !== null
    );

    onSearch && onSearch(appliedOptionsFilter as QuotationType[], selected);
  };

  return (
    <Fragment>
      <Container $loading={$loading} $show={$show} onClick={onClick}>
        <TrimSelectContainer>
          {trimList.map((trim) => (
            <SearchTrimBox
              key={trim.id}
              name={trim.name}
              description={trim.description}
              price={trim.price}
              status={
                selected === trim.id
                  ? "choice"
                  : CheckfilterOption(trim.id).status
              }
              isOption={CheckfilterOption(trim.id).isOption}
              onClick={
                selected === trim.id
                  ? () => setSelected(null)
                  : () => setSelected(trim.id)
              }
            />
          ))}
        </TrimSelectContainer>
        <CheckOptionContainer>
          {data.map((option) => (
            <OptionCheckBox
              key={option.id}
              $filter={option.filter}
              $name={option.name}
              onChange={() => dataChange(option.id, option.filter)}
              onClick={() => {
                setDetailOption(option as DescriptionOptionModalProps);
                setIsDescriptionModalOpen(true);
              }}
            />
          ))}
        </CheckOptionContainer>
        <ButtonContainer>
          <Button
            $x={9.5625}
            $y={2.25}
            text={"결정하기"}
            $backgroundcolor={theme.colors.NAVYBLUE5}
            $bordercolor={theme.colors.NAVYBLUE5}
            $textcolor={theme.colors.WHITE}
            $opacity={selected ? 1 : 0.3}
            handleClick={
              selected !== null
                ? () => {
                    onSearch && onSearchHandler(idData, selected);
                  }
                : undefined
            }
          />
        </ButtonContainer>
      </Container>
      {isDescriptionModalOpen && (
        <OptionDescriptionModal
          setIsDescriptionModalOpen={setIsDescriptionModalOpen}
          option={detailOption as DescriptionOptionModalProps}
          isTrimSelect={true}
          // onClick 부분에 체크가 변하는 것도 있어야해서 모달 자체를 OptionCheckBox 내로 넣어야하나 고민
          onClick={() => {
            if (detailOption?.filter) {
              dataChange(detailOption!.id, detailOption.filter);
            }
          }}
        />
      )}
    </Fragment>
  );
}

const Container = styled.div<MyTrimSearchScreenProps>`
  display: ${({ $show }) => ($show ? "flex" : "none")};
  flex-direction: column;
  align-items: center;
  position: relative;
  width: 100%;
  height: 100%;
  gap: 1.5rem;

  animation: ${({ $show }) =>
    $show ? "appear 0.5s ease-in-out forwards" : ""};
  animation: ${({ $loading }) =>
    $loading ? "disappear 0.5s ease-in-out forwards" : ""};

  @keyframes appear {
    0% {
      opacity: 0;
    }
    100% {
      opacity: 1;
    }
  }

  @keyframes disappear {
    0% {
      opacity: 1;
    }
    100% {
      opacity: 0;
    }
  }
`;

const TrimSelectContainer = styled.div`
  margin-top: 1.5rem;
  display: flex;
  flex-direction: row;
  gap: 0.5rem;
`;

const CheckOptionContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: row;
  flex-wrap: wrap;
  gap: 0.7rem;
  max-width: 72rem;
`;

const ButtonContainer = styled.div`
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: row;
  gap: 0.5rem;
  margin: 1.5rem;
`;
