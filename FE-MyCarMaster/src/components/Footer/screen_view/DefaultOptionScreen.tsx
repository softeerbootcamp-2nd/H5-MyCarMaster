import { useState, useEffect } from "react";
import styled from "styled-components";
import { CategoryList, GridOptionList } from "@common/index";
import { useTrimState } from "@contexts/TrimContext";
import { useDetailState } from "@contexts/DetailContext";
import ArrowLeft from "@assets/icons/ArrowLeft.svg";
import ArrowRight from "@assets/icons/ArrowRight.svg";
import useFetch from "@hooks/useFetch";
import theme from "@styles/Theme";

type DataListProps = {
  id: number;
  category: CategoryType;
  name: string;
  imgUrl: string;
  description: string;
};

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

let dataList: DataListProps[] = [];

export default function DefaultOptionScreen({
  $loading,
  $show,
  onClick,
}: DefaultOptionScreenProps) {
  const [selected, setSelected] = useState<CategoryType>("파워트레인/성능");
  const [page, setPage] = useState(1);
  const { trimId } = useTrimState();
  const { engineId, wheelDriveId, bodyTypeId } = useDetailState();

  const SERVER_URL = import.meta.env.VITE_APP_SERVER_URL;
  const { data } = useFetch<{
    result: { defaultOptions: DataListProps[] };
  }>(
    `${SERVER_URL}/options/default?trimId=${trimId}&engineId=${engineId}&wheelDriveId=${wheelDriveId}&bodyTypeId=${bodyTypeId}`,
    {
      method: "GET",
    }
  );

  const onClickHandler = (category: CategoryType) => {
    setSelected(category as CategoryType);
    setPage(1);
  };

  const filterData =
    dataList && dataList.filter((item) => item.category === selected);

  useEffect(() => {
    if (data) {
      dataList = data.result.defaultOptions;
    }
  }, [data]);

  return (
    dataList && (
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
            $font={theme.fonts.Medium12_15}
            onClickHandler={(_index, category) =>
              onClickHandler(category as CategoryType)
            }
            $switch={"default"}
          />
        </CategoryListContainer>
        <GridOptionListContainer>
          <GridOptionList list={filterData} page={page} />
        </GridOptionListContainer>
        <PageButtonContainer>
          <PageButton
            $none={page === 1}
            src={ArrowLeft}
            onClick={() => {
              if (page > 1) {
                setPage(page - 1);
              }
            }}
          ></PageButton>
          {page}
          <PageButton
            $none={
              page === Math.ceil(filterData.length / 10) ||
              filterData.length === 0
            }
            src={ArrowRight}
            onClick={() => {
              if (page < Math.ceil(filterData.length / 10)) {
                setPage(page + 1);
              }
            }}
          ></PageButton>
        </PageButtonContainer>
      </Container>
    )
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

const PageButtonContainer = styled.div`
  display: flex;
  justify-content: center;
  width: 80%;
  text-align: center;
  gap: 1rem;
  ${(props) => props.theme.fonts.Medium20};
  color: ${({ theme }) => theme.colors.NAVYBLUE5};
`;

const PageButton = styled.img<{ $none?: boolean }>`
  filter: ${({ $none }) => ($none ? "grayscale(100%)" : "none")};
  opacity: ${({ $none }) => ($none ? "0.3" : "1")};
  width: 1.5rem;
`;
