import styled from "styled-components";
import { useTrimState, useTrimDispatch } from "../../../contexts/TrimContext";
import { useQuotationDispatch } from "../../../contexts/QuotationContext";
import OptionBox from "../../common/OptionBox/OptionBox";

export default function TrimSelect() {
  const { trimList, trimId } = useTrimState();
  const trimDispatch = useTrimDispatch();
  const quotationDispatch = useQuotationDispatch();

  const selectTrim = (id: number) => {
    quotationDispatch({
      type: "SET_TRIM_QUOTATION",
      payload: {
        name: trimList[id].name,
        price: trimList[id].price,
      },
    });
    trimDispatch({
      type: "SELECT_TRIM",
      payload: {
        trimId: id,
      },
    });
  };
  return (
    <Container>
      {trimList.map((trim) => {
        return (
          <OptionBox
            key={trim.id}
            $id={trim.id}
            $name={trim.name}
            $description={trim.description}
            $imgUrl={trim.imgUrl}
            $ratio={trim.ratio}
            $price={trim.price}
            $choice={trimId === trim.id}
            handleClick={() => selectTrim(trim.id)}
          />
        );
      })}
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: row;
  width: 51.5rem;
  gap: 0.5rem;
`;
