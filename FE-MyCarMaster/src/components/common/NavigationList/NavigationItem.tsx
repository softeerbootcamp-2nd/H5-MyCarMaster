import { styled } from "styled-components";
import theme from "../../../styles/Theme";
import CircleCheck from "../../../assets/icons/CircleCheck.svg";
import indexNameSwitching from "../../../utils/indexNameSwitching";
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
  $active?: boolean | undefined | 0;
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
  const [start, end] = indexNameSwitching(name) as number[];

  const handleNavigate = () => {
    quotationDispatch({
      type: "NAVIGATE",
      payload: { navigationId: start },
    });
  };

  return (
    <Container
      $active={
        navigationId !== undefined &&
        navigationId >= start &&
        navigationId <= end
      }
      onClick={handleNavigate}
    >
      <TopContainer>
        <Category>{name}</Category>
        {(!isFirst[start] || !isFirst[end]) && quotation && (
          <ShowRightOption>
            <Price>
              {"+"}
              {Object.entries(quotation)
                .reduce((acc, [key, value]) => {
                  if (key === "selectedQuotation") {
                    return (
                      acc +
                      value.reduce(
                        (sum: number, curr: QuotationType) => sum + curr.price,
                        0
                      )
                    );
                  } else if (key === "consideredQuotation") return acc;
                  return acc + value.price;
                }, 0)
                .toLocaleString("ko-KR")}
            </Price>
            <CheckCircle src={CircleCheck} />
          </ShowRightOption>
        )}
      </TopContainer>

      {quotation && name === "세부모델" ? (
        <BottomContainer>
          {Object.entries(quotation).map(
            ([key, value]) => value.name && <Text key={key}>{value.name}</Text>
          )}
        </BottomContainer>
      ) : (
        quotation &&
        Object.entries(quotation).map(([key, value]) =>
          key === "selectedQuotation" ? (
            value.map((item: QuotationType) => (
              <BottomContainer key={item.id}>{item.name}</BottomContainer>
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
  & + & {
    gap: 0.25rem;
  }

  background-color: ${({ $active }) =>
    $active ? `${theme.colors.NAVYBLUE1}` : `${theme.colors.WHITE}`};
  border: ${({ $active }) =>
    $active
      ? `1px solid ${theme.colors.NAVYBLUE4}`
      : `1px solid ${theme.colors.GREY2}`};
  cursor: pointer;
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
  // text vertical center
  display: flex;
  align-items: center;
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
