import { Container, CheckBox, Check, CheckStyle, Name, Detail } from "./style";

type OptionCheckBoxProps = {
  $name: string;
  $filter: {
    unavailableTrimIds: number[];
    additionalTrimIds: number[];
    defaultTrimIds: number[];
  };
  onChange: () => void;
  onClick: () => void;
  active: boolean;
};
export default function OptionCheckBox({
  $name,
  onChange,
  onClick,
  active,
}: OptionCheckBoxProps) {
  const onClickHandler = (e: React.MouseEvent) => {
    e.stopPropagation();
    onClick();
  };
  return (
    <Container $checked={active} onClick={onChange}>
      <CheckBox $checked={active}>
        <Check type="checkbox" checked={active} onChange={onChange} />
        <CheckStyle $checked={active} />
      </CheckBox>
      <Name $checked={active}>{$name}</Name>
      <Detail $checked={active} onClick={onClickHandler}>
        상세 보기
      </Detail>
    </Container>
  );
}
