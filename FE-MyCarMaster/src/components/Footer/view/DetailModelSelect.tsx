import styled from "styled-components";
import { useDetailState } from "../../../contexts/DetailContext";
import { useQuotationState } from "../../../contexts/QuotationContext";
import OptionBox from "../../../components/common/OptionBox/OptionBox";

export default function DetailModelSelect() {
  const { engineList, wheelDriveList, bodyTypeList } = useDetailState();
  const { navigationId } = useQuotationState();
  return (
    <Container>
      {navigationId === 1 && (
        <>
          {engineList.map((engine) => {
            return (
              <OptionBox
                $id={engine.id}
                $name={engine.name}
                $description={engine.description}
                $imgUrl={engine.imgUrl}
                $ratio={engine.ratio}
                $price={engine.price}
              />
            );
          })}
        </>
      )}
      {navigationId === 2 && (
        <>
          {wheelDriveList.map((wheelDrive) => {
            return (
              <OptionBox
                $id={wheelDrive.id}
                $name={wheelDrive.name}
                $description={wheelDrive.description}
                $imgUrl={wheelDrive.imgUrl}
                $ratio={wheelDrive.ratio}
                $price={wheelDrive.price}
              />
            );
          })}
        </>
      )}
      {navigationId === 3 && (
        <>
          {bodyTypeList.map((bodyType) => {
            return (
              <OptionBox
                $id={bodyType.id}
                $name={bodyType.name}
                $description={bodyType.description}
                $imgUrl={bodyType.imgUrl}
                $ratio={bodyType.ratio}
                $price={bodyType.price}
              />
            );
          })}
        </>
      )}
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: row;
  width: 51.5rem;
  gap: 0.5rem;
`;
