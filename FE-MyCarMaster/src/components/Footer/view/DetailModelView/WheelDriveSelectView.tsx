import {
  useDetailState,
  useDetailDispatch,
} from "../../../../contexts/DetailContext";
import { useQuotationDispatch } from "../../../../contexts/QuotationContext";
import OptionBox from "../../../common/OptionBox/OptionBox";

export default function BodyTypeSelectView() {
  const { wheelDriveList, engineId, bodyTypeId, wheelDriveId } =
    useDetailState();

  const detailDispatch = useDetailDispatch();
  const quotationDispatch = useQuotationDispatch();

  const selectWheelDrive = (id: number) => {
    quotationDispatch({
      type: "SET_DETAIL_QUOTATION",
      payload: {
        type: "wheelDriveQuotation",
        name: wheelDriveList[id - 1].name,
        price: wheelDriveList[id - 1].price,
      },
    });
    detailDispatch({
      type: "SELECT_DETAIL",
      payload: {
        engineId,
        wheelDriveId: id,
        bodyTypeId,
      },
    });
  };

  if (!wheelDriveList?.length) return null;

  return (
    <>
      {wheelDriveList?.length &&
        wheelDriveList.map((wheelDrive) => {
          return (
            <OptionBox
              key={wheelDrive.id}
              $id={wheelDrive.id}
              $name={wheelDrive.name}
              $description={wheelDrive.description}
              $ratio={wheelDrive.ratio}
              $price={wheelDrive.price}
              $switch="detail"
              $choice={wheelDrive.id === wheelDriveId}
              handleClick={() => selectWheelDrive(wheelDrive.id)}
            />
          );
        })}
    </>
  );
}
