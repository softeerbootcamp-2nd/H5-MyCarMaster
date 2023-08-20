import {
  useDetailState,
  useDetailDispatch,
} from "../../../../contexts/DetailContext";
import { useQuotationDispatch } from "../../../../contexts/QuotationContext";
import OptionBox from "../../../common/OptionBox/OptionBox";

export default function BodyTypeSelectView() {
  const { bodyTypeList, engineId, wheelDriveId, bodyTypeId } = useDetailState();
  const detailDispatch = useDetailDispatch();
  const quotationDispatch = useQuotationDispatch();

  const selectBodyType = (id: number) => {
    quotationDispatch({
      type: "SET_DETAIL_QUOTATION",
      payload: {
        type: "bodyTypeQuotation",
        id: id,
        name: bodyTypeList[id - 1].name,
        price: bodyTypeList[id - 1].price,
      },
    });
    detailDispatch({
      type: "SELECT_DETAIL",
      payload: {
        engineId,
        wheelDriveId,
        bodyTypeId: id,
      },
    });
  };

  if (!bodyTypeList?.length) return null;

  return (
    <>
      {bodyTypeList?.length &&
        bodyTypeList.map((bodyType) => {
          return (
            <OptionBox
              key={bodyType.id}
              $id={bodyType.id}
              $name={bodyType.name}
              $description={bodyType.description}
              $ratio={bodyType.ratio}
              $price={bodyType.price}
              $switch="detail"
              $choice={bodyType.id === bodyTypeId}
              handleClick={() => selectBodyType(bodyType.id)}
            />
          );
        })}
    </>
  );
}
