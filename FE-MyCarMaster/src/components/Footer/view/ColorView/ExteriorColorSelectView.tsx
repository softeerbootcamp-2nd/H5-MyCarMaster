import OuterColorBox from "../../../common/ColorBox/OuterColorBox";
import {
  useCarPaintState,
  useCarPaintDispatch,
} from "../../../../contexts/CarPaintContext";
import {
  useQuotationState,
  useQuotationDispatch,
} from "../../../../contexts/QuotationContext";
import { TrimQuotationType } from "../../../../types/quotation.types";

export default function InteriorColorSelectView() {
  const { exteriorList, exteriorId } = useCarPaintState();
  const { trimQuotation } = useQuotationState() as {
    trimQuotation: TrimQuotationType;
  };

  const quotationDispatch = useQuotationDispatch();
  const carPaintDispatch = useCarPaintDispatch();

  const selectExterior = (id: number, index: number) => {
    quotationDispatch({
      type: "SET_CAR_PAINT_QUOTATION",
      payload: {
        type: "exteriorColorQuotation",
        id: id,
        name: exteriorList[index].name,
        price: exteriorList[index].price,
        imgUrl: exteriorList[index].colorImgUrl,
      },
    });
    carPaintDispatch({
      type: "SELECT_EXTERIOR",
      payload: {
        exteriorId: id,
      },
    });
  };

  if (!exteriorList?.length) return null;

  return (
    <>
      {exteriorList?.length &&
        exteriorList.map((exterior, index) => {
          return (
            <OuterColorBox
              key={index}
              $id={exterior.id}
              $name={exterior.name}
              ratio={exterior.ratio}
              price={exterior.price}
              trim={trimQuotation?.trimQuotation?.name}
              $active={exterior.id === exteriorId}
              $colorImgUrl={exterior.colorImgUrl}
              $coloredImgUrl={exterior.coloredImgUrl}
              handleClick={() => selectExterior(exterior.id, index)}
            />
          );
        })}
    </>
  );
}
