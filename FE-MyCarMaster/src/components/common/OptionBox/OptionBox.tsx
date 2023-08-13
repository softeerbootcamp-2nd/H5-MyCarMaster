import OptionBoxName from "./OptionBoxName";
import OptionBoxButtonContainer from "./OptionBoxButtonContainer";
import {
  Container,
  TopContainer,
  BottomContainer,
  Detail,
  Price,
  ActiveCSS,
  DefaultCSS,
} from "./style";

type OptionBoxProp = {
  $id: number;
  $name: string;
  $description: string;
  $price: number | undefined;
  $ratio: number | undefined;
  $choice: boolean;
  $switch: string;
  $considered?: boolean;
  handleClick?: () => void;
};

function OptionBox({
  $id,
  $name,
  $description,
  $price,
  $ratio,
  $choice,
  $switch,
  $considered,
  handleClick,
}: OptionBoxProp) {
  return (
    <Container
      onClick={handleClick}
      $style={
        $choice
          ? ActiveCSS.Background
          : $considered
          ? ActiveCSS.BackgroundConsider
          : DefaultCSS.Background
      }
    >
      <TopContainer>
        <OptionBoxName
          name={$name}
          description={$description}
          ratio={$ratio}
          choice={$choice}
          considered={$considered}
          isDetail={$switch === "detail"}
          isTrim={$switch === "trim"}
        />
      </TopContainer>

      {$switch === "option" && (
        <Price
          $style={
            $considered
              ? ActiveCSS.Price
              : $choice
              ? ActiveCSS.Price
              : DefaultCSS.Price
          }
        >
          + {$price?.toLocaleString("ko-KR")} 원
        </Price>
      )}

      <BottomContainer>
        {$switch === "option" ? (
          <OptionBoxButtonContainer
            id={$id}
            name={$name}
            price={$price}
            choice={$choice}
            considered={$considered}
          />
        ) : (
          <>
            {$switch === "trim" ? (
              <>
                <Price $style={$choice ? ActiveCSS.Price : DefaultCSS.Price}>
                  {$price?.toLocaleString("ko-KR")} 원
                </Price>
                <Detail $style={$choice ? ActiveCSS.Detail : DefaultCSS.Detail}>
                  자세히보기 &gt;
                </Detail>
              </>
            ) : (
              <Price $style={$choice ? ActiveCSS.Price : DefaultCSS.Price}>
                + {$price?.toLocaleString("ko-KR")} 원
              </Price>
            )}
          </>
        )}
      </BottomContainer>
    </Container>
  );
}

export default OptionBox;
