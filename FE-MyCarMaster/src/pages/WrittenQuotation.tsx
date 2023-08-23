import { styled } from "styled-components";
import QuotationItem from "../components/common/QuotationList/QuotationItem";
import QuotationOptionItem from "../components/common/QuotationList/QuotationOptionItem";
import theme from "../styles/Theme";

const data = {
  result: {
    trim: {
      name: "Le Blanc",
      price: 43460000,
    },
    engine: {
      name: "디젤 2.2 엔진",
      price: 0,
    },
    wheelDrive: {
      name: "2WD",
      price: 0,
    },
    bodyType: {
      name: "7인승",
      price: 0,
    },
    exteriorColor: {
      name: "어비스 블랙 펄",
      price: 0,
      colorImgUrl:
        "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/exterior-color/abyss.png",
      coloredImgUrl:
        "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/exterior-color/car/abyss.png",
    },
    interiorColor: {
      name: "퀼팅천연 (블랙)",
      price: 0,
      colorImgUrl:
        "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/interior-color/quilting-natural-black.png",
    },
    selectOptions: [
      {
        name: "빌트인 캠(보조배터리 포함)",
        price: 690000,
        imgUrl:
          "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/option/built-in-cam.png",
        category: "안전",
      },
      {
        name: "주차보조 시스템 2",
        price: 690000,
        imgUrl:
          "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/option/rear-parking-collision-prevention-assistance.png",
        category: "안전",
      },
    ],
    considerOptions: [
      {
        name: "20인치 다크 스파터링 휠",
        price: 840000,
        imgUrl:
          "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/option/20-inch-dark-sputtering-wheel.png",
        category: "스타일&퍼포먼스",
      },
      {
        name: "20인치 블랙톤 전면 가공 휠",
        price: 840000,
        imgUrl:
          "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/option/20-inch-black-tone-front-processing-wheel.png",
        category: "스타일&퍼포먼스",
      },
    ],
  },
};

function WrittenEstimation() {
  const calculateTotalPrice = () => {
    let totalPrice = 0;

    totalPrice += data.result.trim.price;
    totalPrice += data.result.engine.price;
    totalPrice += data.result.wheelDrive.price;
    totalPrice += data.result.bodyType.price;

    totalPrice += data.result.exteriorColor.price;
    totalPrice += data.result.interiorColor.price;

    data.result.selectOptions.forEach((option) => {
      totalPrice += option.price;
    });

    data.result.considerOptions.forEach((option) => {
      totalPrice += option.price;
    });

    return totalPrice;
  };

  return (
    <Container>
      <BlueBackground />
      <QuotationMain>
        <QuotationContent>
          <Model>팰리세이드</Model>
          <CarImage src={data.result.exteriorColor.coloredImgUrl} />
        </QuotationContent>
      </QuotationMain>
      <QuotationFooter>
        <PriceContainer>
          <Price>예상 가격</Price>
          <SumPrice>{calculateTotalPrice().toLocaleString("ko-KR")}원</SumPrice>
        </PriceContainer>
      </QuotationFooter>
      <StepList>
        <QuotationItem
          category={"트림"}
          name={data.result.trim.name}
          price={data.result.trim.price}
          id={0}
          confirm={true}
        />
        <QuotationItem
          category={"엔진"}
          name={data.result.engine.name}
          price={data.result.engine.price}
          id={1}
          confirm={true}
        />
        <QuotationItem
          category={"구동 방식"}
          name={data.result.wheelDrive.name}
          price={data.result.wheelDrive.price}
          id={2}
          confirm={true}
        />
        <QuotationItem
          category={"바디 타입"}
          name={data.result.bodyType.name}
          price={data.result.bodyType.price}
          id={3}
          confirm={true}
        />
        <QuotationItem
          category={"외장 색상"}
          name={data.result.exteriorColor.name}
          price={data.result.exteriorColor.price}
          imgUrl={data.result.exteriorColor.colorImgUrl}
          id={4}
          confirm={true}
        />
        <QuotationItem
          category={"내장 색상"}
          name={data.result.interiorColor.name}
          price={data.result.interiorColor.price}
          imgUrl={data.result.interiorColor.colorImgUrl}
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
                {data.result.selectOptions.map((option, id) => (
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
                {data.result.considerOptions.map((option, id) => (
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
  );
}

export default WrittenEstimation;

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
  font-family: "HyundaiSansBold";
  font-size: 4rem;
  font-style: normal;
  font-weight: 500;
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
  font-family: "HyundaiSansRegular";
  font-size: 1rem;
  font-style: normal;
  font-weight: 700;
  line-height: 1.5rem;
`;

const SumPrice = styled.p`
  font-family: "HyundaiSansMedium";
  font-size: 1.75rem;
  font-style: normal;
  font-weight: 500;
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
  font-family: "HyundaiSansMedium";
  font-size: 1.5rem;
  font-style: normal;
  font-weight: 500;
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
  font-family: "HyundaiSansMedium";
  font-size: 1.375rem;
  font-style: normal;
  font-weight: 700;
  line-height: 1.75rem;
`;
