import { useEffect, useState } from "react";
import { styled } from "styled-components";
import { OptionType } from "../../../types/options.types";
import theme from "../../../styles/Theme";
import NPerformance from "../../../assets/images/NPerformance.png";
import ArrowLeft from "../../../assets/icons/ArrowLeft.svg";
import ArrowRight from "../../../assets/icons/ArrowRight.svg";
import CirclePagination from "../../../assets/icons/CirclePagination.svg";
import { useOptionState } from "../../../contexts/OptionContext";

function OptionDescription({ option }: { option: OptionType }) {
  const [page, setPage] = useState<number>(0);
  const { optionId } = useOptionState();
  const maxPage = (option.subOptions && option.subOptions?.length) || 0;
  const $transformX = -page * 279;

  const prevButtonHandler = () => {
    if (page === 0) setPage(maxPage - 1);
    else setPage(page - 1);
  };

  const nextButtonHandler = () => {
    if (page === maxPage - 1) setPage(0);
    else setPage(page + 1);
  };

  const paginationButtonHandler = (index: number) => {
    setPage(index);
  };

  useEffect(() => {
    setPage(0);
  }, [optionId]);

  useEffect(() => {
    const interval = setInterval(() => {
      if (page === maxPage - 1) setPage(0);
      else setPage(page + 1);
    }, 5000);
    return () => {
      clearInterval(interval);
    };
  }, [page]);

  return (
    <Container>
      <MainOptionContainer>
        <MainOptionName>{option.name}</MainOptionName>
        <Summary>{option.description}</Summary>
        {option.tag ? <Tag src={NPerformance}></Tag> : <></>}
        <Border />
      </MainOptionContainer>

      <SubOptionContainer>
        <SubOptionList
          style={{ width: `${maxPage * 279}px` }}
          $transformX={$transformX}
        >
          {option.subOptions &&
            option.subOptions.map((subOption, index) => (
              <SubOptionItem key={index}>
                <SubOption>
                  <SubOptionNameContainer>
                    <PrevButton onClick={prevButtonHandler} />
                    <SubOptionName>{subOption.name}</SubOptionName>
                    <NextButton onClick={nextButtonHandler} />
                  </SubOptionNameContainer>
                  <Description>{subOption.description}</Description>
                </SubOption>
              </SubOptionItem>
            ))}
        </SubOptionList>
        <PaginationContainer>
          {Array.from({ length: maxPage }).map((_, index) => (
            <PaginationButton
              key={index}
              onClick={() => paginationButtonHandler(index)}
              $active={index === page}
            />
          ))}
        </PaginationContainer>
      </SubOptionContainer>
    </Container>
  );
}

const Container = styled.div`
  width: 327px;
  height: 334px;
  padding: 0 1.5rem;

  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  gap: 2rem;
`;

const MainOptionContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  gap: 0.5rem;
  /* border-bottom: 1px solid ${theme.colors.Grey2}; */
`;

const MainOptionName = styled.p`
  font-size: 1.75rem;
  font-style: normal;
  font-weight: 500;
  line-height: 2.25rem; /* 128.571% */
`;

const Summary = styled.p`
  font-size: 0.8125rem;
  font-style: normal;
  font-weight: 500;
  line-height: 165%; /* 1.34063rem */

  color: ${theme.colors.CoolGrey2};
`;

const Tag = styled.img`
  margin-top: 1rem;
  width: 50%;
  height: 1rem;
`;

const Border = styled.div`
  margin-top: 1rem;
  width: 100%;
  border: 1px solid ${theme.colors.Grey2};
`;

const SubOptionContainer = styled.div`
  width: 100%;
  height: 100%;

  display: flex;
  flex-direction: column;
  overflow: hidden;
`;

const SubOptionList = styled.div<{ $transformX: number }>`
  display: flex;
  width: 100%;
  transition: transform 0.5s ease-in-out;
  transform: translateX(${({ $transformX }) => $transformX}px);
`;

const SubOptionItem = styled.div`
  width: 100%;
`;

const SubOption = styled.div`
  display: flex;
  flex-direction: column;
  gap: 1rem;
`;

const SubOptionNameContainer = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const PrevButton = styled.button`
  width: 1rem;
  height: 1rem;
  background-image: url(${ArrowLeft});
`;

const SubOptionName = styled.p`
  font-size: 0.875rem;
  font-style: normal;
  font-weight: 500;
  line-height: 1.25rem; /* 142.857% */

  color: ${theme.colors.NavyBlue5};
`;

const NextButton = styled.button`
  width: 1rem;
  height: 1rem;
  background-image: url(${ArrowRight});
  background-position: center;
`;

const Description = styled.p`
  font-size: 0.8125rem;
  font-style: normal;
  font-weight: 400;
  line-height: 165%; /* 1.34063rem */
`;

const PaginationContainer = styled.div`
  margin-top: auto;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 0.5rem;
`;

const PaginationButton = styled.button<{ $active: boolean }>`
  width: 1rem;
  height: 1rem;
  background-image: url(${CirclePagination});
  background-position: center;
  background-repeat: no-repeat;
  ${({ $active }) =>
    $active
      ? `filter: invert(12%) sepia(38%) saturate(5531%) hue-rotate(221deg) brightness(90%) contrast(89%);`
      : `filter: invert(85%) sepia(6%) saturate(273%) hue-rotate(184deg) brightness(96%) contrast(90%);`};
`;

export default OptionDescription;