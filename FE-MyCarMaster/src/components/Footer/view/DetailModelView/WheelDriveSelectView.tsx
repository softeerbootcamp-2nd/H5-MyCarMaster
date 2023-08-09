import {
    useDetailState,
    useDetailDispatch,
  } from "../../../../contexts/DetailContext";
  import { useQuotationDispatch } from "../../../../contexts/QuotationContext";
  import OptionBox from "../../../common/OptionBox/OptionBox";
  
  export default function BodyTypeSelectView() {
    const { wheelDriveList } = useDetailState();
  
    const detailDispatch = useDetailDispatch();
    const quotationDispatch = useQuotationDispatch();
  
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
  
    return (
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
    );
  }