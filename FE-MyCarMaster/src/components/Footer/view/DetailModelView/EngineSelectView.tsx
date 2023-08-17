import {
  useDetailState,
  useDetailDispatch,
} from "../../../../contexts/DetailContext";
import { useQuotationDispatch } from "../../../../contexts/QuotationContext";
import OptionBox from "../../../common/OptionBox/OptionBox";

export default function BodyTypeSelectView() {
  const { engineList, wheelDriveId, bodyTypeId, engineId } = useDetailState();

  const detailDispatch = useDetailDispatch();
  const quotationDispatch = useQuotationDispatch();

  const selectEngine = (id: number) => {
    quotationDispatch({
      type: "SET_DETAIL_QUOTATION",
      payload: {
        type: "engineQuotation",
        name: engineList[id - 1].name,
        price: engineList[id - 1].price,
      },
    });
    detailDispatch({
      type: "SELECT_DETAIL",
      payload: {
        engineId: id,
        wheelDriveId,
        bodyTypeId,
      },
    });
  };

  if (!engineList?.length) return null;

  return (
    <>
      {engineList?.length &&
        engineList.map((engine) => {
          return (
            <OptionBox
              key={engine.id}
              $id={engine.id}
              $name={engine.name}
              $description={engine.description}
              $ratio={engine.ratio}
              $price={engine.price}
              $switch="detail"
              $choice={engine.id === engineId}
              handleClick={() => selectEngine(engine.id)}
            />
          );
        })}
    </>
  );
}
