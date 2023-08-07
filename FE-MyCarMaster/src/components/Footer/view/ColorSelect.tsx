import styled from "styled-components";
import { useCarPaintState } from "../../../contexts/CarPaintContext";
import { useQuotationState } from "../../../contexts/QuotationContext";
import InnerColorBox from "../../common/ColorBox/InnerColorBox";
import OuterColorBox from "../../common/ColorBox/OuterColorBox";

export default function TrimSelect() {
  const { exteriorList, interiorList, exteriorId, interiorId } =
    useCarPaintState();
  const { navigationId, trimQuotation }: any = useQuotationState();

  return (
    <>
      {navigationId === 4 && (
        <Container>
          {exteriorList.map((exterior) => {
            return (
              <OuterColorBox
                $id={exterior.id}
                $name={exterior.name}
                ratio={exterior.ratio}
                price={exterior.price}
                trim={trimQuotation.trimQuotation.name}
                $active={exterior.id === exteriorId}
                $colorImgUrl={exterior.colorImgUrl}
                $coloredImgUrl={exterior.coloredImgUrl}
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
                $id={interior.id}
                $name={interior.name}
                ratio={interior.ratio}
                price={interior.price}
                trim={trimQuotation.trimQuotation.name}
                $active={interior.id === interiorId}
                $colorImgUrl={interior.colorImgUrl}
                $coloredImgUrl={interior.coloredImgUrl}
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
