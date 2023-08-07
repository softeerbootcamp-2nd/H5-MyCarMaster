import { styled } from "styled-components";
import theme from "../../../styles/Theme";
import CircleCheck from "../../../assets/icons/CircleCheck.svg";
import indexContextSwitching from "../../../utils/indexContextSwitching";
import {
  useQuotationState,
  useQuotationDispatch,
} from "../../../contexts/QuotationContext";
import {
  QuotationType,
  TrimQuotationType,
  DetailQuotationType,
  CarPaintQuotationType,
  OptionQuotationType,
} from "../../../types/quotation.types";

type activeProp = {
  $active?: boolean;
};

type QutoationProp =
  | undefined
  | TrimQuotationType
  | DetailQuotationType
  | CarPaintQuotationType
  | OptionQuotationType;

type NavigationItemProp = {
  name: string;
  quotation?: QutoationProp;
};

function NavigationItem({ name, quotation }: NavigationItemProp) {
  const { navigationId, isFirst } = useQuotationState();
  const quotationDispatch = useQuotationDispatch();
  const selected = indexContextSwitching(name);

  const handleNavigate = () => {
    quotationDispatch({
      type: "NAVIGATE",
      payload: { navigationId: selected },
    });
  };
  return (
    <Container $active={selected === navigationId} onClick={handleNavigate}>
      <TopContainer>
        <Category>{name}</Category>
        {!isFirst[selected] && (
          <ShowRightOption>
            <Price>+1,000,000</Price>
            <CheckCircle src={CircleCheck} />
          </ShowRightOption>
        )}
      </TopContainer>

      {quotation && name === "세부모델" ? (
        <BottomContainer>
          {Object.entries(quotation).map(([key, value]) => (
            <Text key={key}>{value.name}</Text>
          ))}
        </BottomContainer>
      ) : (
        quotation &&
        Object.entries(quotation).map(([key, value]) =>
          key === "selectedQuotation" ? (
            value.map((item: QuotationType) => (
              <BottomContainer key={item.name}>{item.name}</BottomContainer>
            ))
          ) : (
            <BottomContainer key={key}>{value.name}</BottomContainer>
          )
        )
      )}
    </Container>
  );
}

const Container = styled.li<activeProp>`
  width: 9.625rem;
  padding: 0.5rem 0.75rem;

  display: flex;
  flex-direction: column;
  justify-content: space-between;
  gap: 0.25rem;

  background-color: ${({ $active }) =>
    $active ? `${theme.colors.NavyBlue1}` : `${theme.colors.White}`};
  border: ${({ $active }) =>
    $active
      ? `1px solid ${theme.colors.NavyBlue4}`
      : `1px solid ${theme.colors.Grey2}`};
`;

const TopContainer = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const ShowRightOption = styled.div`
  display: flex;
  flex-direction: row;
  gap: 0.38rem;
`;

const Price = styled.p`
  font-size: 0.5rem;
`;

const BottomContainer = styled.div`
  display: flex;
  flex-direction: row;
  gap: 0.75rem;
  font-family: "Hyundai Sans Text KR";
  font-size: 0.5rem;
  font-style: normal;
  font-weight: 400;
  line-height: 165%; /* 0.825rem */
  letter-spacing: -0.015rem;
`;

const Text = styled.p``;

const Category = styled.p`
  font-family: "Hyundai Sans Text KR";
  font-size: 0.75rem;
  font-style: normal;
  font-weight: 700;
  line-height: 165%; /* 1.2375rem */
  letter-spacing: -0.0225rem;
`;

const CheckCircle = styled.img``;

export default NavigationItem;
