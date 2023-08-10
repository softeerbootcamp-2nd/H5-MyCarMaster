import {
  useQuotationState,
  useQuotationDispatch,
} from "../../../contexts/QuotationContext";
import { useOptionDispatch } from "../../../contexts/OptionContext";
import theme from "../../../styles/Theme";
import Button from "../Button/Button";
import OptionBoxName from "./OptionBoxName";
import OptionBoxTopPrice from "./OptionBoxTopPrice";
import {
  Container,
  TopContainer,
  BottomContainer,
  ButtonContainer,
  Detail,
  Price,
  ActiveColor,
  DefaultColor,
} from "./style";

type OptionBoxProp = {
  $id: number;
  $name?: string;
  $description?: string;
  $price?: number;
  $ratio?: number;
  $imgUrl?: string;
  $choice?: boolean;
  $considered?: boolean;
  handleClick?: () => void;
};

function OptionBox(props: OptionBoxProp) {
  const { navigationId } = useQuotationState();
  const quotationDispatch = useQuotationDispatch();
  const optionDispatch = useOptionDispatch();

  const considerButtonHandler = (id: number) => {
    optionDispatch({
      type: "SET_CHOICE_OPTION",
      payload: {
        where: "consideredOption",
        id: id,
      },
    });
    quotationDispatch({
      type: "SET_CONSIDER_QUOTATION",
      payload: {
        id: id,
        name: props.$name,
        price: props.$price,
      },
    });
  };

  const addButtonHandler = (id: number) => {
    optionDispatch({
      type: "SET_CHOICE_OPTION",
      payload: {
        where: "selectedOption",
        id: id,
      },
    });
    quotationDispatch({
      type: "SET_SELECT_QUOTATION",
      payload: {
        id: id,
        name: props.$name,
        price: props.$price,
      },
    });
  };

  return (
    <Container
      onClick={props.handleClick}
      $style={
        props.$choice
          ? ActiveColor.Background
          : props.$considered
          ? ActiveColor.BackgroundConsider
          : DefaultColor.Background
      }
    >
      <TopContainer>
        <OptionBoxName
          $name={props.$name}
          $description={props.$description}
          $ratio={props.$ratio}
          choice={props.$choice}
          considered={props.$considered}
          isDetail={navigationId !== 0 && navigationId !== 6}
        />
        <OptionBoxTopPrice
          $description={props.$description}
          $price={props.$price}
          choice={props.$choice}
          isTrim={navigationId === 0}
        />
      </TopContainer>

      {navigationId === 6 && (
        <Price
          $style={
            props.$considered
              ? ActiveColor.Price
              : props.$choice
              ? ActiveColor.Price
              : DefaultColor.Price
          }
        >
          + {props.$price?.toLocaleString("ko-KR")} 원
        </Price>
      )}

      <BottomContainer>
        {navigationId === 6 ? (
          <>
            <ButtonContainer>
              <Button
                $x={4.875}
                $y={1.5}
                $backgroundcolor={`${theme.colors.WHITE}`}
                $textcolor={`${theme.colors.NAVYBLUE5}`}
                $bordercolor={`${theme.colors.NAVYBLUE5}`}
                text={props.$considered ? "취소하기" : "고민해보기"}
                $tool={
                  props.$choice
                    ? "AddSelectedToConsider"
                    : props.$considered
                    ? "ConsiderSelected"
                    : "ConsiderDefault"
                }
                handleClick={() => considerButtonHandler(props.$id)}
              />
              <Button
                $x={4.875}
                $y={1.5}
                $backgroundcolor={`${theme.colors.WHITE}`}
                $textcolor={`${theme.colors.NAVYBLUE5}`}
                $bordercolor={`${theme.colors.NAVYBLUE5}`}
                text={props.$choice ? "취소하기" : "추가하기"}
                $tool={
                  props.$choice
                    ? "AddSelected"
                    : props.$considered
                    ? "ConsiderSelectToAdd"
                    : "AddDefault"
                }
                handleClick={() => addButtonHandler(props.$id)}
              />
            </ButtonContainer>
          </>
        ) : (
          <>
            {navigationId === 0 ? (
              <Detail
                $style={
                  props.$choice ? ActiveColor.Detail : DefaultColor.Detail
                }
              >
                자세히보기 &gt;
              </Detail>
            ) : (
              <Price
                $style={props.$choice ? ActiveColor.Price : DefaultColor.Price}
              >
                + {props.$price?.toLocaleString("ko-KR")} 원
              </Price>
            )}
          </>
        )}
      </BottomContainer>
    </Container>
  );
}

export default OptionBox;
