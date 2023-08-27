import { Fragment, useState, useEffect } from "react";
import styled from "styled-components";
import theme from "@styles/Theme";
import {
  SearchTrimBox,
  OptionCheckBox,
  Button,
  OptionDescriptionModal,
} from "@common/index";
import { useModelState } from "@contexts/ModelContext";
import { useTrimState } from "@contexts/TrimContext";
import { QuotationType } from "types/quotation.types";
import { DescriptionOptionModalProps } from "types/options.types";
import useFetch from "@hooks/useFetch";

type DataListProps = {
  id: number;
  name: string;
  imgUrl: string;
  summary: string;
  description: string;
  subOptions: null | SubDataList[];
  filter: {
    unavailableTrimIds: number[];
    additionalTrimIds: number[];
    defaultTrimIds: number[];
  };
  appliedOption: QuotationType;
};

type SubDataList = {
  name: string;
  imgUrl: string;
  description: string | null;
};
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

type CheckActiveProps = {
  id: number;
  checkActive: boolean;
};

type StatusType = "default" | "choice" | "none";
type OptionType = "default" | "add" | "none";

let dataList: DataListProps[] = [];

export default function MyTrimSearchScreen({
  $loading,
  $show,
  onClick,
  onSearch,
}: MyTrimSearchScreenProps) {
  const [idData, setIdData] = useState<IDDataProps[]>([]);
  const [selected, setSelected] = useState<number | null>(null);
  const { trimList } = useTrimState();
  const [checkActive, setCheckActive] = useState<CheckActiveProps[]>([]);

  const [isDescriptionModalOpen, setIsDescriptionModalOpen] = useState(false);
  const [detailOption, setDetailOption] =
    useState<DescriptionOptionModalProps>();
  const { modelId } = useModelState();

  const SERVER_URL = import.meta.env.VITE_APP_SERVER_URL;

  const { data } = useFetch<{
    result: { representativeOptions: DataListProps[] };
  }>(`${SERVER_URL}/options/representative?modelId=${modelId}`, {
    method: "GET",
  });

  useEffect(() => {
    if (data) {
      dataList = data.result.representativeOptions;
    }
  }, [data]);

  const dataChange = (id: number, filter: IDDataProps) => {
    setSelected(null);
    const index = idData.findIndex((dataList) => dataList.optionId === id);
    if (index === -1) {
      setIdData([...idData, { ...filter, optionId: id }]);
    } else {
      const newIdData = idData.filter((dataList) => dataList.optionId !== id);
      setIdData(newIdData);
    }

    const checkIndex = checkActive.findIndex((dataList) => dataList.id === id);
    if (checkIndex === -1) {
      setCheckActive([...checkActive, { id, checkActive: true }]);
    }
    if (checkIndex !== -1) {
      const newCheckActive = checkActive.filter(
        (dataList) => dataList.id !== id
      );
      setCheckActive(newCheckActive);
    }
  };

  const CheckfilterOption = (
    trimId: number
  ): { status: StatusType; isOption: OptionType } => {
    if (idData.length === 0) return { status: "default", isOption: "none" };

    const isUnavailable = idData.some((dataList) =>
      dataList.unavailableTrimIds.includes(trimId)
    );

    if (isUnavailable) return { status: "none", isOption: "none" };

    for (const dataList of idData)
      if (!dataList.defaultTrimIds.includes(trimId))
        return { status: "default", isOption: "add" };

    return { status: "default", isOption: "default" };
  };

  const onSearchHandler = (idData: IDDataProps[], selected: number) => {
    const appliedOptions = idData.map((optionData) => {
      if (optionData.defaultTrimIds.includes(selected)) return null;
      const appliedOption = dataList.find(
        (option) => option.id === optionData.optionId
      )?.appliedOption;
      return appliedOption;
    });

    const appliedOptionsFilter = appliedOptions.filter(
      (option) => option !== null
    );

    onSearch && onSearch(appliedOptionsFilter as QuotationType[], selected);
  };

  if (dataList.length === 0) return <></>;

  return (
    <Fragment>
      {dataList.length && (
        <>
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
              {dataList.map((option) => (
                <OptionCheckBox
                  key={option.id}
                  $filter={option.filter}
                  $name={option.name}
                  active={checkActive.some((data) => data.id === option.id)}
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
              isTrimSelect={true}
              setIsDescriptionModalOpen={setIsDescriptionModalOpen}
              option={detailOption as DescriptionOptionModalProps}
              onClick={() => {
                if (detailOption?.filter)
                  dataChange(detailOption!.id, detailOption!.filter);
              }}
            />
          )}
        </>
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
