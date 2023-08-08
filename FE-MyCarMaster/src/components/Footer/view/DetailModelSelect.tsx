import styled from "styled-components";
import {
  useDetailState,
  useDetailDispatch,
} from "../../../contexts/DetailContext";
import {
  useQuotationState,
  useQuotationDispatch,
} from "../../../contexts/QuotationContext";
import OptionBox from "../../../components/common/OptionBox/OptionBox";

export default function DetailModelSelect() {
  const { engineList, wheelDriveList, bodyTypeList } = useDetailState();
  const { navigationId } = useQuotationState();
  const detailDispatch = useDetailDispatch();
  const quotationDispatch = useQuotationDispatch();

  const selectEngine = (id: number) => {
    quotationDispatch({
      type: "SET_DETAIL_QUOTATION",
      payload: {
        type: "engineQuotation",
        name: engineList[id].name,
        price: engineList[id].price,
      },
    });
    detailDispatch({
      type: "SELECT_DETAIL",
      payload: {
        engineId: id,
      },
    });
  };

  const selectWheelDrive = (id: number) => {
    quotationDispatch({
      type: "SET_DETAIL_QUOTATION",
      payload: {
        type: "wheelDriveQuotation",
        name: wheelDriveList[id].name,
        price: wheelDriveList[id].price,
      },
    });
    detailDispatch({
      type: "SELECT_DETAIL",
      payload: {
        wheelDriveId: id,
      },
    });
  };

  const selectBodyType = (id: number) => {
    quotationDispatch({
      type: "SET_DETAIL_QUOTATION",
      payload: {
        type: "bodyTypeQuotation",
        name: bodyTypeList[id].name,
        price: bodyTypeList[id].price,
      },
    });
    detailDispatch({
      type: "SELECT_DETAIL",
      payload: {
        bodyTypeId: id,
      },
    });
  };

  return (
    <Container>
      {navigationId === 1 && (
        <>
          {engineList.map((engine) => {
            return (
              <OptionBox
                key={engine.id}
                $id={engine.id}
                $name={engine.name}
                $description={engine.description}
                $imgUrl={engine.imgUrl}
                $ratio={engine.ratio}
                $price={engine.price}
                handleClick={() => selectEngine(engine.id)}
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
                key={wheelDrive.id}
                $id={wheelDrive.id}
                $name={wheelDrive.name}
                $description={wheelDrive.description}
                $imgUrl={wheelDrive.imgUrl}
                $ratio={wheelDrive.ratio}
                $price={wheelDrive.price}
                handleClick={() => selectWheelDrive(wheelDrive.id)}
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
                key={bodyType.id}
                $id={bodyType.id}
                $name={bodyType.name}
                $description={bodyType.description}
                $imgUrl={bodyType.imgUrl}
                $ratio={bodyType.ratio}
                $price={bodyType.price}
                handleClick={() => selectBodyType(bodyType.id)}
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
