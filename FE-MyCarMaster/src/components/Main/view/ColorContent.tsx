import { styled } from "styled-components";
import CategoryList from "../../common/CategoryList/CategoryList";
import ColorWrapper from "./ColorView/ColorWrapper";
import { useQuotationDispatch } from "../../../contexts/QuotationContext";

function ColorContent() {
  const quotationDispatch = useQuotationDispatch();

  const onClickHandler = (index: number) => {
    const nextNavigationId = 4 + index;
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
        categories={["외장색상", "내장색상"]}
        onClickHandler={onClickHandler}
        indexSetter={4}
      />
      <ColorWrapper />
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  height: 100%;
`;

export default ColorContent;