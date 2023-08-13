import {
  Ratio,
  Description,
  Decoration,
  Name,
  ActiveCSS,
  DefaultCSS,
} from "./style";

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
  return (
    <>
      {isDetail ? (
        <Ratio>구매자 {ratio}%가 선택</Ratio>
      ) : (
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
      )}

      <Name
        $style={
          considered
            ? ActiveCSS.NameConsider
            : choice
            ? ActiveCSS.Name
            : DefaultCSS.Name
        }
        $size={isTrim ? DefaultCSS.SizeUp : DefaultCSS.SizeDown}
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
