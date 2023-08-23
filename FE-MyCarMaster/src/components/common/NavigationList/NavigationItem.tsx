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
import { useNavigate } from "react-router-dom";

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
  confirm: boolean;
};

function NavigationItem({ name, quotation, confirm }: NavigationItemProp) {
  const { navigationId, isFirst } = useQuotationState();
  const quotationDispatch = useQuotationDispatch();
  const navigate = useNavigate();
  const [start, end] = indexNameSwitching(name) as number[];

  const handleNavigate = () => {
    quotationDispatch({
      type: "NAVIGATE",
      payload: { navigationId: start },
    });

    if (name === "견적서 완성") {
      navigate("/quotation");
      return;
    }
    navigate("/estimation");
  };

  return (
    <Container
      $active={
        navigationId !== undefined &&
        navigationId >= start &&
        navigationId <= end
      }
      onClick={!confirm ? handleNavigate : () => {}}
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
          key === "selectedQuotation"
            ? value.map((item: QuotationType, index: number) =>
                value.length > 3 && item.id === value[2].id ? (
                  <BottomContainer key={item.id}>
                    {item.name} 외 {value.length - 3}개
                  </BottomContainer>
                ) : (
                  index < 3 && (
                    <BottomContainer key={item.id}>{item.name}</BottomContainer>
                  )
                )
              )
            : key !== "consideredQuotation" &&
              value.name && (
                <BottomContainer key={key}>{value.name}</BottomContainer>
              )
        )
      )}
    </Container>
  );
}

const Container = styled.li<activeProp>`
  width: 12rem;
  padding: 0.5rem 0.75rem;

  display: flex;
  flex-direction: column;
  justify-content: space-between;
  & + & {
    gap: 0.45rem;
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
  ${(props) => props.theme.fonts.Regular9};
  display: flex;
  align-items: center;
`;

const BottomContainer = styled.div`
  display: flex;
  flex-direction: row;
  gap: 0.45rem;

  ${(props) => props.theme.fonts.Regular9};

  flex-wrap: wrap;
`;

const Text = styled.p``;

const Category = styled.p`
  ${(props) => props.theme.fonts.Medium10};
  line-height: 1.5rem;
`;

const CheckCircle = styled.img``;

export default NavigationItem;
