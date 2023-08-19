import { styled } from "styled-components";
import TextButton from "../Button/TextButton";
import {
  useQuotationDispatch,
  useQuotationState,
} from "../../../contexts/QuotationContext";
import QuotationOptionItem from "./QuotationOptionItem";
import theme from "../../../styles/Theme";
import { useNavigate } from "react-router-dom";
import { useTrimState } from "../../../contexts/TrimContext";
import { Fragment, useState } from "react";
import BasicOptionModal from "../BasicOptionModal/BasicOptionModal";
import { ConfirmType } from "../../../types/quotation.types";

function QuotationOptionList({ confirm }: ConfirmType) {
  const { optionQuotation } = useQuotationState();
  const { trimList, trimId } = useTrimState();
  const quotationDispatch = useQuotationDispatch();
  const [isBasicOptionModalOpen, setIsBasicOptionModalOpen] =
    useState<boolean>(false);

  const navigate = useNavigate();

  const calculateOptionTotalPrice = () => {
    const totalPrice = optionQuotation.selectedQuotation.reduce(
      (acc, item) => acc + item.price,
      0
    );
    return totalPrice;
  };

  const navigateOption = (navigationId: number) => {
    quotationDispatch({ type: "NAVIGATE", payload: { navigationId } });
    navigate("/estimation");
  };

  return (
    <Fragment>
      <Container>
        <CategoryContainer>
          <Category>옵션</Category>
          {!confirm && (
            <TextButton
              size={"1"}
              text={"변경하기"}
              handleClick={() => navigateOption(6)}
            />
          )}
        </CategoryContainer>
        <ItemContainer>
          <TextButton
            size={"1.25"}
            text={"기본 포함 옵션"}
            handleClick={() => setIsBasicOptionModalOpen(true)}
          />
          <TotalOptionContainer>
            <OptionContainer>
              <Option>추가 옵션</Option>
              {optionQuotation.selectedQuotation.map((option) => (
                <QuotationOptionItem
                  key={option.id}
                  id={option.id as number}
                  imgUrl={option.imgUrl as string}
                  category={option.category as string}
                  name={option.name}
                  price={option.price}
                  isSelected={true}
                  confirm={confirm}
                />
              ))}
            </OptionContainer>
            <OptionContainer>
              <Option>고민 옵션</Option>
              {optionQuotation.consideredQuotation.map((option) => (
                <QuotationOptionItem
                  key={option.id}
                  id={option.id as number}
                  imgUrl={option.imgUrl as string}
                  category={option.category as string}
                  name={option.name}
                  price={option.price}
                  isSelected={false}
                  confirm={confirm}
                />
              ))}
            </OptionContainer>
            <TotalPriceContainer>
              <TotalText>총합</TotalText>
              <TotalPrice>
                +{calculateOptionTotalPrice().toLocaleString("ko-KR")}원
              </TotalPrice>
            </TotalPriceContainer>
          </TotalOptionContainer>
        </ItemContainer>
      </Container>
      {isBasicOptionModalOpen && (
        <BasicOptionModal
          trimId={trimId}
          trimName={trimList[trimId - 1].name}
          trimDescription={trimList[trimId - 1].description}
          setIsBasicOptionModalOpen={setIsBasicOptionModalOpen}
        />
      )}
    </Fragment>
  );
}

export default QuotationOptionList;

const Container = styled.div`
  width: 59.5rem;

  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  padding-bottom: 2rem;
  border-bottom: 1px solid ${theme.colors.GREY3};
`;

const CategoryContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: flex-start;
  height: 4.5rem;
`;

const Category = styled.p`
  font-family: "HyundaiSansMedium";
  font-size: 1.5rem;
  font-style: normal;
  font-weight: 500;
  line-height: 2rem; /* 133.333% */
`;

const ItemContainer = styled.div`
  width: 48rem;
  display: flex;
  flex-direction: column;
  align-items: flex-end;
`;

const TotalOptionContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-self: flex-start;
  gap: 4rem;
`;

const OptionContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-self: flex-start;
  gap: 1.5rem;
`;

const Option = styled.div`
  font-family: "HyundaiSansMedium";
  font-size: 1.375rem;
  font-style: normal;
  font-weight: 700;
  line-height: 1.75rem;
`;

const TotalPriceContainer = styled.div`
  width: 48rem;
  display: flex;
  justify-content: space-between;
`;

const TotalText = styled.p`
  font-family: "HyundaiSansMedium";
  font-size: 1.375rem;
  font-style: normal;
  font-weight: 700;
  line-height: 1.75rem;
`;

const TotalPrice = styled.p`
  font-family: "HyundaiSansMedium";
  font-size: 1.375rem;
  font-style: normal;
  font-weight: 700;
  line-height: 1.75rem;
`;
