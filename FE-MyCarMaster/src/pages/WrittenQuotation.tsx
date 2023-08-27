import { styled } from "styled-components";
import QuotationItem from "@common/QuotationList/QuotationItem";
import QuotationOptionItem from "@common/QuotationList/QuotationOptionItem";
import theme from "@styles/Theme";
import { Fragment, useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { get } from "@/utils/fetch";

interface WrittenOptionProps {
  name: string;
  price: number;
  imgUrl: string;
  category: string;
}

interface WrittenQuotationProps {
  trim: {
    name: string;
    price: number;
  };
  engine: {
    name: string;
    price: number;
  };
  wheelDrive: {
    name: string;
    price: number;
  };
  bodyType: {
    name: string;
    price: number;
  };
  exteriorColor: {
    name: string;
    price: number;
    colorImgUrl: string;
    coloredImgUrl: string;
  };
  interiorColor: {
    name: string;
    price: number;
    colorImgUrl: string;
  };
  selectOptions: WrittenOptionProps[] | null;
  considerOptions: WrittenOptionProps[] | null;
}

function WrittenQuotation() {
  const SERVER_URL = import.meta.env.VITE_APP_SERVER_URL;
  const { estimateId } = useParams();
  const [data, setData] = useState<WrittenQuotationProps>();

  function calculateTotalPrice(quotation: WrittenQuotationProps): number {
    const parts = [
      quotation.trim,
      quotation.engine,
      quotation.wheelDrive,
      quotation.bodyType,
      quotation.exteriorColor,
      quotation.interiorColor,
    ];

    let totalPrice = parts.reduce((acc, part) => acc + part.price, 0);
    data?.selectOptions &&
      data.selectOptions.forEach((option: WrittenOptionProps) => {
        totalPrice += option.price;
      });
    data?.considerOptions &&
      data.considerOptions.forEach((option: WrittenOptionProps) => {
        totalPrice += option.price;
      });

    return totalPrice;
  }

  useEffect(() => {
    console.log(estimateId);
    get(`${SERVER_URL}/estimates/${estimateId}`).then((res) => {
      console.log(res);
      setData(res.result);
    });
  }, []);

  return (
    <Fragment>
      {data && (
        <Container>
          <BlueBackground />
          <QuotationMain>
            <QuotationContent>
              <Model>팰리세이드</Model>
              <CarImage
                src={`${data!.exteriorColor.coloredImgUrl}high/sprite.png`}
              />
            </QuotationContent>
          </QuotationMain>
          <QuotationFooter>
            <PriceContainer>
              <Price>예상 가격</Price>
              <SumPrice>
                {calculateTotalPrice(
                  data as WrittenQuotationProps
                ).toLocaleString("ko-KR")}
                원
              </SumPrice>
            </PriceContainer>
          </QuotationFooter>
          <StepList>
            <QuotationItem
              category={"트림"}
              name={data!.trim.name}
              price={data!.trim.price}
              id={0}
              confirm={true}
            />
            <QuotationItem
              category={"엔진"}
              name={data!.engine.name}
              price={data!.engine.price}
              id={1}
              confirm={true}
            />
            <QuotationItem
              category={"구동 방식"}
              name={data!.wheelDrive.name}
              price={data!.wheelDrive.price}
              id={2}
              confirm={true}
            />
            <QuotationItem
              category={"바디 타입"}
              name={data!.bodyType.name}
              price={data!.bodyType.price}
              id={3}
              confirm={true}
            />
            <QuotationItem
              category={"외장 색상"}
              name={data!.exteriorColor.name}
              price={data!.exteriorColor.price}
              imgUrl={data!.exteriorColor.colorImgUrl}
              id={4}
              confirm={true}
            />
            <QuotationItem
              category={"내장 색상"}
              name={data!.interiorColor.name}
              price={data!.interiorColor.price}
              imgUrl={data!.interiorColor.colorImgUrl}
              id={5}
              confirm={true}
            />
          </StepList>
          <Options>
            <OptionResize>
              <CategoryContainer>
                <Category>옵션</Category>
              </CategoryContainer>
              <ItemContainer>
                <TotalOptionContainer>
                  <OptionContainer>
                    <Option>추가 옵션</Option>
                    {data?.selectOptions &&
                      data.selectOptions.map((option, id) => (
                        <QuotationOptionItem
                          key={id}
                          id={id as number}
                          imgUrl={option.imgUrl as string}
                          category={option.category as string}
                          name={option.name}
                          price={option.price}
                          isSelected={true}
                          $isFinished={true}
                          confirm={true}
                        />
                      ))}
                  </OptionContainer>
                  <OptionContainer>
                    <Option>고민 옵션</Option>
                    {data?.considerOptions &&
                      data.considerOptions.map((option, id) => (
                        <QuotationOptionItem
                          key={id}
                          id={id as number}
                          imgUrl={option.imgUrl as string}
                          category={option.category as string}
                          name={option.name}
                          price={option.price}
                          isSelected={false}
                          $isFinished={true}
                          confirm={true}
                        />
                      ))}
                  </OptionContainer>
                </TotalOptionContainer>
              </ItemContainer>
            </OptionResize>
          </Options>
        </Container>
      )}
    </Fragment>
  );
}

export default WrittenQuotation;

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100vw;
  height: 100vh;
`;

const BlueBackground = styled.div`
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 37.5rem;
  margin: 0;
  background: linear-gradient(180deg, #dde4f8 0%, rgba(231, 235, 246, 0) 100%);
  z-index: -1;
`;

const QuotationMain = styled.div`
  width: 100%;
  height: 100%;

  display: flex;
  flex-direction: row;
  justify-content: center;
  gap: 10rem;

  margin-top: 3rem;
`;

const QuotationContent = styled.div`
  width: 59.5rem;

  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 1rem;
`;

const Model = styled.p`
  ${(props) => props.theme.fonts.Medium40};
  line-height: 2.5rem; /* 125% */
`;

const CarImage = styled.img``;

const QuotationFooter = styled.div`
  display: flex;
  justify-content: center;
  gap: 10rem;
  margin-bottom: 3rem;
`;

const PriceContainer = styled.div`
  width: 59.5rem;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: flex-end;
`;

const Price = styled.p`
  ${(props) => props.theme.fonts.Regular10};
  line-height: 1.5rem;
`;

const SumPrice = styled.p`
  ${(props) => props.theme.fonts.Regular15};
  line-height: 2.25rem;
`;

const StepList = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 2rem;
`;

const Options = styled.div`
  display: flex;
  justify-content: center;
  align-items: flex-start;
  padding-bottom: 2rem;
  border-bottom: 1px solid ${theme.colors.GREY3};
  width: 100%;
`;
const OptionResize = styled.div`
  width: 59.5rem;
`;

const CategoryContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: flex-start;
  height: 4.5rem;
`;

const Category = styled.p`
  ${(props) => props.theme.fonts.Regular15};
  line-height: 2rem; /* 133.333% */
`;

const ItemContainer = styled.div`
  width: 59.5rem;
  display: flex;
  flex-direction: column;
  align-items: flex-end;
`;

const TotalOptionContainer = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  align-self: flex-start;
  gap: 4rem;
`;

const OptionContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-self: flex-start;
  width: 100%;
  gap: 1.5rem;
`;

const Option = styled.div`
  ${(props) => props.theme.fonts.Regular13};
  line-height: 1.75rem;
`;
