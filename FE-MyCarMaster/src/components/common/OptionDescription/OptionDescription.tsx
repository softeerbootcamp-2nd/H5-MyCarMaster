import { useEffect, useState } from "react";
import { styled } from "styled-components";
import { OptionType, SuboptionsType } from "../../../types/options.types";
import theme from "../../../styles/Theme";
import NPerformance from "../../../assets/images/NPerformance.png";
import ArrowLeft from "../../../assets/icons/ArrowLeft.svg";
import ArrowRight from "../../../assets/icons/ArrowRight.svg";

function OptionDescription({ option }: { option: OptionType }) {
  const [subOptions, setSubOptions] = useState<SuboptionsType[] | null>();
  const [page, setPage] = useState(0);
  const maxPage = subOptions && subOptions.length;

  useEffect(() => {
    setSubOptions(option.subOptions);
  }, []);

  const prevButtonHandler = () => {
    if (page === 0) setPage(maxPage - 1);
    else setPage(page - 1);
  };

  const nextButtonHandler = () => {
    if (page === maxPage - 1) setPage(0);
    else setPage(page + 1);
  };

  return (
    <Container>
      <MainOptionContainer>
        <MainOptionName>{option.name}</MainOptionName>
        <Summary>{option.description}</Summary>
        {option.tag ? <Tag src={NPerformance}></Tag> : <></>}
        <Border />
      </MainOptionContainer>

      {subOptions && subOptions ? (
        <SubOptionContainer>
          <SubOptionNameContainer>
            <PrevButton onClick={prevButtonHandler} />
            <SubOptionName>{subOptions[page].name}</SubOptionName>
            <NextButton onClick={nextButtonHandler} />
          </SubOptionNameContainer>
          <Description>{subOptions[page].description}</Description>
        </SubOptionContainer>
      ) : (
        <></>
      )}
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
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
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

export default OptionDescription;
