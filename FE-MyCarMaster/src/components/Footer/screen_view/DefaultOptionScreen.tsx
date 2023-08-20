import { useState } from "react";
import styled from "styled-components";
import CategoryList from "../../common/CategoryList/CategoryList";
import GridOptionList from "../../common/OptionList/GridOptionList";

const data = [
  {
    id: 1,
    category: "파워트레인/성능",
    name: "디젤 2.2 엔진",
    imgUrl:
      "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/option/second-row-ventilation-seat.png",
    description:
      "강력한 토크와 탁월한 효율로 여유있는 파워와 높은 연비를 제공하는 디젤엔진입니다.\n최고출력 : 202마력\n최대토크 : 45.0kgf·m",
  },
  {
    id: 3,
    category: "파워트레인/성능",
    name: "8단 자동변속기",
    imgUrl:
      "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/option/second-row-ventilation-seat.png",
    description:
      "전달 효율 증대로 전 엔진 동급 최고의 연비를 구현함은 물론, 최적의 변속 성능으로 드라이빙 감성까지 향상시켜줍니다.",
  },
  {
    id: 4,
    category: "파워트레인/성능",
    name: "ISG 시스템",
    imgUrl:
      "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/option/second-row-ventilation-seat.png",
    description:
      "신호 대기 상황이거나 정차 중일 때 차의 엔진을 일시 정지하여 연비를 향상시키고, 배출가스 발생을 억제하는 시스템입니다.",
  },
  {
    id: 5,
    category: "파워트레인/성능",
    name: "ISG 시스템",
    imgUrl:
      "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/option/second-row-ventilation-seat.png",
    description:
      "신호 대기 상황이거나 정차 중일 때 차의 엔진을 일시 정지하여 연비를 향상시키고, 배출가스 발생을 억제하는 시스템입니다.",
  },
  {
    id: 6,
    category: "파워트레인/성능",
    name: "ISG 시스템",
    imgUrl:
      "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/option/second-row-ventilation-seat.png",
    description:
      "신호 대기 상황이거나 정차 중일 때 차의 엔진을 일시 정지하여 연비를 향상시키고, 배출가스 발생을 억제하는 시스템입니다.",
  },
  {
    id: 7,
    category: "파워트레인/성능",
    name: "ISG 시스템",
    imgUrl:
      "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/option/second-row-ventilation-seat.png",
    description:
      "신호 대기 상황이거나 정차 중일 때 차의 엔진을 일시 정지하여 연비를 향상시키고, 배출가스 발생을 억제하는 시스템입니다.",
  },
];

type DefaultOptionScreenProps = {
  $loading: boolean;
  $show: boolean;
  onClick?: (e: React.MouseEvent<HTMLDivElement, MouseEvent>) => void;
};

type CategoryType =
  | "파워트레인/성능"
  | "지능형 안전기술"
  | "안전"
  | "외관"
  | "내장"
  | "시트"
  | "편의";

export default function DefaultOptionScreen({
  $loading,
  $show,
  onClick,
}: DefaultOptionScreenProps) {
  const [selected, setSelected] = useState<CategoryType>("파워트레인/성능");

  const onClickHandler = (category: CategoryType) => {
    setSelected(category as CategoryType);
  };

  const filterData = data.filter((item) => item.category === selected);

  return (
    <Container $loading={$loading} $show={$show} onClick={onClick}>
      <CategoryListContainer>
        <CategoryList
          categories={[
            "파워트레인/성능",
            "지능형 안전기술",
            "안전",
            "외관",
            "내장",
            "시트",
            "편의",
          ]}
          indexSetter={selected}
          onClickHandler={(_index, category) =>
            onClickHandler(category as CategoryType)
          }
          $switch={"default"}
        />
      </CategoryListContainer>
      <GridOptionListContainer>
        <GridOptionList list={filterData} />
      </GridOptionListContainer>
    </Container>
  );
}

const Container = styled.div<DefaultOptionScreenProps>`
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

const CategoryListContainer = styled.div`
  margin-top: 1.5rem;
  display: flex;
  align-items: flex-start;
  width: 80%;
`;

const GridOptionListContainer = styled.div`
  margin-top: 1.5rem;
  width: 80%;
  height: 75%;
`;
