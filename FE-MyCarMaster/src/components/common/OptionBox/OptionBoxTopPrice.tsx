import { Description, Price, ActiveColor, DefaultColor } from "./style";

type OptionBoxTopPriceProp = {
  $description?: string;
  $price?: number;
  isTrim: boolean;
  choice?: boolean;
};

export default function OptionBoxTopPrice(props: OptionBoxTopPriceProp) {
  return (
    <>
      {props.isTrim && (
        <>
          <Description
            $style={
              props.choice ? ActiveColor.Description : DefaultColor.Description
            }
          >
            {props.$description}
          </Description>
          <Price $style={props.choice ? ActiveColor.Price : DefaultColor.Price}>
            {props.$price?.toLocaleString("ko-KR")} 원
          </Price>
        </>
      )}
    </>
  );
}
