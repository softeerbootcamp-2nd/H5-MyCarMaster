import { styled } from "styled-components";
import CategoryList from "../../common/CategoryList/CategoryList";
import DetailModelWrapper from "./DetailModelView/DetailModelWrapper";
import { useQuotationDispatch } from "../../../contexts/QuotationContext";

function DetailModelContent() {
  const quotationDispatch = useQuotationDispatch();

  const onClickHandler = (index: number) => {
    const nextNavigationId = 1 + index;
    quotationDispatch({
      type: "NAVIGATE",
      payload: {
        navigationId: nextNavigationId,
      },
    });
  };
  return (
    <Container>
      <CategoryList
        categories={["엔진", "구동방식", "바디타입"]}
        onClickHandler={onClickHandler}
        indexSetter={1}
      />
      <DetailModelWrapper />
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  height: 100%;
`;

export default DetailModelContent;
