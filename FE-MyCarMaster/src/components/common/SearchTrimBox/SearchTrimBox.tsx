import {
  Container,
  Name,
  Description,
  Price,
  BottomContainer,
  ActiveCSS,
  DefaultCSS,
  NoneCSS,
} from "./style";
import TagItem from "../TagItem/TagItem";

type StatusType = "default" | "choice" | "none";
type OptionType = "default" | "add" | "none";

type SearchTrimBoxProps = {
  name: string;
  description: string;
  price: number;
  status: StatusType;
  isOption?: OptionType;
  onClick?: undefined | (() => void);
};

export default function SearchTrimBox({
  name,
  description,
  price,
  status,
  isOption,
  onClick,
}: SearchTrimBoxProps) {
  const backgroundStyle = calculateBackground(status);
  const nameStyle = calculateName(status);
  const descriptionStyle = calculateDescription(status);
  const priceStyle = calculatePrice(status);

  const onClickHandler = () => {
    if (status !== "none") {
      onClick && onClick();
    }
    return;
  };

  return (
    <Container $background={backgroundStyle} onClick={onClickHandler}>
      <Name $text={nameStyle}>{name}</Name>
      <Description $text={descriptionStyle}>{description}</Description>
      <BottomContainer>
        <Price $text={priceStyle}>{price.toLocaleString("ko-KR")} 원</Price>
        {isOption === "default" ? (
          <TagItem text={"기본 제공"} $switch="DefaultsearchTrim" />
        ) : (
          isOption === "add" && (
            <TagItem text={"추가 옵션"} $switch="AddsearchTrim" />
          )
        )}
      </BottomContainer>
    </Container>
  );
}

const calculateBackground = (status: StatusType) => {
  switch (status) {
    case "none":
      return NoneCSS.Background;
    case "choice":
      return ActiveCSS.Background;
    default:
      return DefaultCSS.Background;
  }
};

const calculateName = (status: StatusType) => {
  switch (status) {
    case "none":
      return NoneCSS.Name;
    case "choice":
      return ActiveCSS.Name;
    default:
      return DefaultCSS.Name;
  }
};

const calculateDescription = (status: StatusType) => {
  switch (status) {
    case "none":
      return NoneCSS.Description;
    case "choice":
      return ActiveCSS.Description;
    default:
      return DefaultCSS.Description;
  }
};

const calculatePrice = (status: StatusType) => {
  switch (status) {
    case "none":
      return NoneCSS.Price;
    case "choice":
      return ActiveCSS.Price;
    default:
      return DefaultCSS.Price;
  }
};
