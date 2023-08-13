import {
  Text,
  TextContainer,
  Option,
  SearchTrim,
  ConsiderOption,
} from "./style";

type SwitchType = "AddsearchTrim" | "DefaultsearchTrim" | "option" | "consider";
type TagItemProps = {
  text: string;
  $switch: SwitchType;
};
export default function TagItem({ text, $switch }: TagItemProps) {
  const paddingStyle = caclulatePadding($switch);
  const backgroundStyle = calculateBackground($switch);
  const textStyle = calculateText($switch);
  return (
    <TextContainer $padding={paddingStyle} $background={backgroundStyle}>
      <Text $text={textStyle}>{text}</Text>
    </TextContainer>
  );
}

const caclulatePadding = ($switch: SwitchType) => {
  if ($switch === "AddsearchTrim" || $switch === "DefaultsearchTrim") {
    return SearchTrim.Padding;
  } else if ($switch === "option") {
    return Option.Padding;
  } else {
    return ConsiderOption.Padding;
  }
};

const calculateBackground = ($switch: SwitchType) => {
  if ($switch === "AddsearchTrim") {
    return SearchTrim.AddBackground;
  } else if ($switch === "DefaultsearchTrim") {
    return SearchTrim.DefaultBackground;
  } else if ($switch === "option") {
    return Option.Background;
  } else {
    return ConsiderOption.Background;
  }
};

const calculateText = ($switch: SwitchType) => {
  if ($switch === "AddsearchTrim") {
    return SearchTrim.AddText;
  } else if ($switch === "DefaultsearchTrim") {
    return SearchTrim.DefaultText;
  } else if ($switch === "option") {
    return Option.Text;
  } else {
    return ConsiderOption.Text;
  }
};
