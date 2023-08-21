import {
  useDetailState,
  useDetailDispatch,
} from "../../../../contexts/DetailContext";
import { useQuotationDispatch } from "../../../../contexts/QuotationContext";
import OptionBox from "../../../common/OptionBox/OptionBox";

export default function BodyTypeSelectView() {
  const { wheelDriveList, wheelDriveId } = useDetailState();

  const detailDispatch = useDetailDispatch();
  const quotationDispatch = useQuotationDispatch();

  const selectWheelDrive = (id: number, index: number) => {
    quotationDispatch({
      type: "SET_DETAIL_QUOTATION",
      payload: {
        type: "wheelDriveQuotation",
        id: id,
        name: wheelDriveList[index].name,
        price: wheelDriveList[index].price,
      },
    });
    detailDispatch({
      type: "SELECT_DETAIL",
      payload: {
        wheelDriveId: id,
      },
    });
  };

  if (!wheelDriveList?.length) return null;

  return (
    <>
      {wheelDriveList?.length &&
        wheelDriveList.map((wheelDrive, index) => {
          return (
            <OptionBox
              key={index}
              $id={wheelDrive.id}
              $name={wheelDrive.name}
              $description={wheelDrive.description}
              $ratio={wheelDrive.ratio}
              $price={wheelDrive.price}
              $switch="detail"
              $choice={wheelDrive.id === wheelDriveId}
              handleClick={() => selectWheelDrive(wheelDrive.id, index)}
            />
          );
        })}
    </>
  );
}
