import { Container, CheckBox, Check, CheckStyle, Name, Detail } from "./style";
import { useState } from "react";

type OptionCheckBoxProps = {
  $name: string;
  $filter: {
    unavailableTrimIds: number[];
    additionalTrimIds: number[];
    defaultTrimIds: number[];
  };
  onChange: () => void;
};
export default function OptionCheckBox({
  $name,
  onChange,
}: OptionCheckBoxProps) {
  const [check, setCheck] = useState(false);

  return (
    <Container $checked={check}>
      <CheckBox $checked={check}>
        <Check
          type="checkbox"
          checked={check}
          onClick={onChange}
          onChange={() => setCheck(!check)}
        />
        <CheckStyle $checked={check} />
      </CheckBox>
      <Name $checked={check}>{$name}</Name>
      <Detail $checked={check}>상세 보기</Detail>
    </Container>
  );
}