import { ButtonContainer } from "./style";
import { QuotationActionType } from "../../../types/quotation.types";
import { useQuotationDispatch } from "../../../contexts/QuotationContext";
import { useOptionDispatch } from "../../../contexts/OptionContext";
import Button from "../Button/Button";
import theme from "../../../styles/Theme";

type OptionBoxButtonContainerProp = {
  id: number;
  name: string | undefined;
  price: number | undefined;
  choice: boolean | undefined;
  considered: boolean | undefined;
};

export default function OptionBoxButtonContainer({
  id,
  name,
  price,
  choice,
  considered,
}: OptionBoxButtonContainerProp) {
  const quotationDispatch = useQuotationDispatch();
  const optionDispatch = useOptionDispatch();

  const buttonHandler = (id: number, type: QuotationActionType) => {
    const where =
      type === "SET_SELECT_QUOTATION" ? "selectedOption" : "consideredOption";
    optionDispatch({
      type: "SET_CHOICE_OPTION",
      payload: {
        where: where,
        id: id,
      },
    });
    quotationDispatch({
      type: type,
      payload: {
        id: id,
        name: name,
        price: price,
      },
    });
  };

  return (
    <ButtonContainer>
      <Button
        $x={4.875}
        $y={1.5}
        $backgroundcolor={`${theme.colors.WHITE}`}
        $textcolor={`${theme.colors.NAVYBLUE5}`}
        $bordercolor={`${theme.colors.NAVYBLUE5}`}
        text={considered ? "취소하기" : "고민해보기"}
        $tool={
          choice
            ? "AddSelectedToConsider"
            : considered
            ? "ConsiderSelected"
            : "ConsiderDefault"
        }
        handleClick={() => buttonHandler(id, "SET_CONSIDER_QUOTATION")}
      />
      <Button
        $x={4.875}
        $y={1.5}
        $backgroundcolor={`${theme.colors.WHITE}`}
        $textcolor={`${theme.colors.NAVYBLUE5}`}
        $bordercolor={`${theme.colors.NAVYBLUE5}`}
        text={choice ? "취소하기" : "추가하기"}
        $tool={
          choice
            ? "AddSelected"
            : considered
            ? "ConsiderSelectToAdd"
            : "AddDefault"
        }
        handleClick={() => buttonHandler(id, "SET_SELECT_QUOTATION")}
      />
    </ButtonContainer>
  );
}
