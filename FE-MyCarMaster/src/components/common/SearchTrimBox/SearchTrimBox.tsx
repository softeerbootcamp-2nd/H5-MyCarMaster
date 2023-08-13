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
type SearchTrimBoxProps = {
  name: string;
  description: string;
  price: number;
  status: string;
  isOption?: "default" | "add" | "none";
};

export default function SearchTrimBox({
  name,
  description,
  price,
  status,
  isOption,
}: SearchTrimBoxProps) {
  const backgroundStyle = calculateBackground(status);
  const nameStyle = calculateName(status);
  const descriptionStyle = calculateDescription(status);
  const priceStyle = calculatePrice(status);
  return (
    <Container $background={backgroundStyle}>
      <Name $text={nameStyle}>{name}</Name>
      <Description $text={descriptionStyle}>{description}</Description>
      <BottomContainer>
        <Price $text={priceStyle}>{price}</Price>
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

const calculateBackground = (status: string) => {
  switch (status) {
    case "none":
      return NoneCSS.Background;
    case "choice":
      return ActiveCSS.Background;
    default:
      return DefaultCSS.Background;
  }
};

const calculateName = (status: string) => {
  switch (status) {
    case "none":
      return NoneCSS.Name;
    case "choice":
      return ActiveCSS.Name;
    default:
      return DefaultCSS.Name;
  }
};

const calculateDescription = (status: string) => {
  switch (status) {
    case "none":
      return NoneCSS.Description;
    case "choice":
      return ActiveCSS.Description;
    default:
      return DefaultCSS.Description;
  }
};

const calculatePrice = (status: string) => {
  switch (status) {
    case "none":
      return NoneCSS.Price;
    case "choice":
      return ActiveCSS.Price;
    default:
      return DefaultCSS.Price;
  }
};
