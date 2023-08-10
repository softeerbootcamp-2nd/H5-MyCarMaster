import {
  DetailModelOptionContainer,
  Name,
  Ratio,
  Description,
  Decoration,
  OptionName,
  ActiveColor,
  DefaultColor,
} from "./style";

type OptionBoxNameProp = {
  $name?: string;
  $description?: string;
  $ratio?: number;
  choice?: boolean;
  considered?: boolean;
  isDetail: boolean;
  isTrim: boolean;
};
export default function OptionBoxName(props: OptionBoxNameProp) {
  return (
    <>
      {props.isDetail ? (
        <>
          <DetailModelOptionContainer>
            <Name $style={props.choice ? ActiveColor.Name : DefaultColor.Name}>
              {props.$name}
            </Name>
            <Ratio>구매자 {props.$ratio}%가 선택</Ratio>
          </DetailModelOptionContainer>
          <Description
            $style={
              props.choice ? ActiveColor.Description : DefaultColor.Description
            }
          >
            {props.$description}
          </Description>
        </>
      ) : (
        <>
          <Decoration
            $style={
              props.considered
                ? ActiveColor.DecorationConsider
                : props.choice
                ? ActiveColor.Decoration
                : DefaultColor.Decoration
            }
          >
            {props.$ratio === 0 ? "New" : `구매자 ${props.$ratio}%가 선택`}
          </Decoration>

          <OptionName
            $style={
              props.considered
                ? ActiveColor.OptionName
                : props.choice
                ? ActiveColor.OptionName
                : DefaultColor.OptionName
            }
            $size={
                props.isTrim ? DefaultColor.SizeUp : DefaultColor.SizeDown
            }
          >
            {props.$name}
          </OptionName>
        </>
      )}
    </>
  );
}
