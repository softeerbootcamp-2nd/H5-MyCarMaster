import { Container, CheckBox, Check, Name, Detail } from "./style";

export default function OptionCheckBox() {
  return (
    <Container>
      <CheckBox>
        <Check type="checkbox" />
        <span></span>
      </CheckBox>
      <Name>기본 제공</Name>
      <Detail>기본 제공</Detail>
    </Container>
  );
}
