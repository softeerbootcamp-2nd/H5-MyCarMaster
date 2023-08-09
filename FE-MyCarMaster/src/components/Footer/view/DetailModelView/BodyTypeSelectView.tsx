import {
  useDetailState,
  useDetailDispatch,
} from "../../../../contexts/DetailContext";
import { useQuotationDispatch } from "../../../../contexts/QuotationContext";
import OptionBox from "../../../common/OptionBox/OptionBox";

export default function BodyTypeSelectView() {
  const { bodyTypeList, engineId, wheelDriveId } = useDetailState();
  const detailDispatch = useDetailDispatch();
  const quotationDispatch = useQuotationDispatch();

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
        engineId,
        wheelDriveId,
        bodyTypeId: id,
      },
    });
  };

  return (
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
  );
}
