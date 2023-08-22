import { useDetailState, useDetailDispatch } from "@contexts/DetailContext";
import { useQuotationDispatch } from "@contexts/QuotationContext";
import { OptionBox } from "@common/index";

export default function BodyTypeSelectView() {
  const { bodyTypeList, bodyTypeId } = useDetailState();
  const detailDispatch = useDetailDispatch();
  const quotationDispatch = useQuotationDispatch();

  const selectBodyType = (id: number, index: number) => {
    quotationDispatch({
      type: "SET_DETAIL_QUOTATION",
      payload: {
        type: "bodyTypeQuotation",
        id: id,
        name: bodyTypeList[index].name,
        price: bodyTypeList[index].price,
      },
    });
    detailDispatch({
      type: "SELECT_DETAIL",
      payload: {
        bodyTypeId: id,
      },
    });
  };

  if (!bodyTypeList?.length) return null;

  return (
    <>
      {bodyTypeList?.length &&
        bodyTypeList.map((bodyType, index) => {
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
              handleClick={() => selectBodyType(bodyType.id, index)}
            />
          );
        })}
    </>
  );
}
