import {
  Ratio,
  Description,
  DecorationContainer,
  Decoration,
  Name,
  ActiveCSS,
  DefaultCSS,
} from "./style";
import TagItem from "../TagItem/TagItem";

type OptionBoxNameProp = {
  name?: string;
  description?: string;
  ratio?: number;
  choice?: boolean;
  considered?: boolean;
  isDetail: boolean;
  isTrim: boolean;
};
export default function OptionBoxName({
  name,
  description,
  ratio,
  choice,
  considered,
  isDetail,
  isTrim,
}: OptionBoxNameProp) {
  const nameLength = name?.length || 0;
  return (
    <>
      {isDetail ? (
        <Ratio>구매자 {ratio}%가 선택</Ratio>
      ) : (
        <DecorationContainer>
          <Decoration
            $style={
              considered
                ? ActiveCSS.DecorationConsider
                : choice
                ? ActiveCSS.Decoration
                : DefaultCSS.Decoration
            }
          >
            {ratio === 0 ? "New" : `구매자 ${ratio}%가 선택`}
          </Decoration>
          {considered && <TagItem text={"고민중인 옵션"} $switch="consider" />}
        </DecorationContainer>
      )}

      <Name
        $style={
          considered
            ? ActiveCSS.NameConsider
            : choice
            ? ActiveCSS.Name
            : DefaultCSS.Name
        }
        $size={
          isTrim
            ? DefaultCSS.SizeUp
            : nameLength < 20
            ? DefaultCSS.SizeDown
            : DefaultCSS.LongSizeDown
        }
      >
        {name}
      </Name>
      {(isDetail || isTrim) && (
        <Description
          $style={choice ? ActiveCSS.Description : DefaultCSS.Description}
        >
          {description}
        </Description>
      )}
    </>
  );
}
