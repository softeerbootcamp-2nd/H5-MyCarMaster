import InnerColorBox from "../../../common/ColorBox/InnerColorBox";
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
  const { interiorList, interiorId } = useCarPaintState();
  const { trimQuotation } = useQuotationState() as {
    trimQuotation: TrimQuotationType;
  };

  const quotationDispatch = useQuotationDispatch();
  const carPaintDispatch = useCarPaintDispatch();

  const selectInterior = (id: number, index: number) => {
    quotationDispatch({
      type: "SET_CAR_PAINT_QUOTATION",
      payload: {
        type: "interiorColorQuotation",
        name: interiorList[index].name,
        price: interiorList[index].price,
        imgUrl: interiorList[index].colorImgUrl,
      },
    });
    carPaintDispatch({
      type: "SELECT_INTERIOR",
      payload: {
        interiorId: id,
      },
    });
  };

  if (!interiorList?.length) return null;

  return (
    <>
      {interiorList?.length &&
        interiorList.map((interior, index) => {
          return (
            <InnerColorBox
              key={index}
              $id={interior.id}
              $name={interior.name}
              ratio={interior.ratio}
              price={interior.price}
              trim={trimQuotation?.trimQuotation?.name}
              $active={interior.id === interiorId}
              $colorImgUrl={interior.colorImgUrl}
              $coloredImgUrl={interior.coloredImgUrl}
              handleClick={() => selectInterior(interior.id, index)}
            />
          );
        })}
    </>
  );
}
