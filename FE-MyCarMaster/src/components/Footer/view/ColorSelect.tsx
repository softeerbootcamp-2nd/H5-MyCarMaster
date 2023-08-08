import styled from "styled-components";
import {
  useCarPaintState,
  useCarPaintDispatch,
} from "../../../contexts/CarPaintContext";
import {
  useQuotationState,
  useQuotationDispatch,
} from "../../../contexts/QuotationContext";
import InnerColorBox from "../../common/ColorBox/InnerColorBox";
import OuterColorBox from "../../common/ColorBox/OuterColorBox";

export default function TrimSelect() {
  const { exteriorList, interiorList, exteriorId, interiorId } =
    useCarPaintState();
  const { navigationId, trimQuotation }: any = useQuotationState();
  const quotationDispatch = useQuotationDispatch();
  const carPaintDispatch = useCarPaintDispatch();

  const selectExterior = (id: number) => {
    quotationDispatch({
      type: "SET_CAR_PAINT_QUOTATION",
      payload: {
        type: "exteriorColorQuotation",
        name: exteriorList[id].name,
        price: exteriorList[id].price,
      },
    });
    carPaintDispatch({
      type: "SELECT_EXTERIOR",
      payload: {
        exteriorId: id,
      },
    });
  };

  const selectInterior = (id: number) => {
    quotationDispatch({
      type: "SET_CAR_PAINT_QUOTATION",
      payload: {
        type: "interiorColorQuotation",
        name: interiorList[id].name,
        price: interiorList[id].price,
      },
    });
    carPaintDispatch({
      type: "SELECT_INTERIOR",
      payload: {
        interiorId: id,
      },
    });
  };

  return (
    <>
      {navigationId === 4 && (
        <Container>
          {exteriorList.map((exterior) => {
            return (
              <OuterColorBox
                key={exterior.id}
                $id={exterior.id}
                $name={exterior.name}
                ratio={exterior.ratio}
                price={exterior.price}
                trim={trimQuotation.trimQuotation.name}
                $active={exterior.id === exteriorId}
                $colorImgUrl={exterior.colorImgUrl}
                $coloredImgUrl={exterior.coloredImgUrl}
                handleClick={() => selectExterior(exterior.id)}
              />
            );
          })}
        </Container>
      )}

      {navigationId === 5 && (
        <Container>
          {interiorList.map((interior) => {
            return (
              <InnerColorBox
                key={interior.id}
                $id={interior.id}
                $name={interior.name}
                ratio={interior.ratio}
                price={interior.price}
                trim={trimQuotation.trimQuotation.name}
                $active={interior.id === interiorId}
                $colorImgUrl={interior.colorImgUrl}
                $coloredImgUrl={interior.coloredImgUrl}
                handleClick={() => selectInterior(interior.id)}
              />
            );
          })}
        </Container>
      )}
    </>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: row;
  width: 51.5rem;
  gap: 0.5rem;
  flex-wrap: wrap;
`;
