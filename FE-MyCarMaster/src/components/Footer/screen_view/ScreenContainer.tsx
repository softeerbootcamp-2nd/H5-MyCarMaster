import styled from "styled-components";
import SearchTrimBox from "../../common/SearchTrimBox/SearchTrimBox";
import OptionCheckBox from "../../common/CheckBox/OptionCheckBox";
import Button from "../../common/Button/Button";
type ScreenContainerProps = {
  $loading: boolean;
  $show: boolean;
  onClick?: (e: React.MouseEvent<HTMLDivElement, MouseEvent>) => void;
};
export default function ScreenContainer({
  $loading,
  $show,
  onClick,
}: ScreenContainerProps) {
  return (
    <Container $loading={$loading} $show={$show} onClick={onClick}>
      <TrimSelectContainer>
        <SearchTrimBox
          name={"Exclusive"}
          description={"실용적이고 기본적인 기능을 갖춘 베이직 트림"}
          price={2000}
          status={"default"}
          isOption={"default"}
        />
        <SearchTrimBox
          name={"Le Blanc"}
          description={"실용적이고 기본적인 기능을 갖춘 베이직 트림"}
          price={38960000}
          status={"choice"}
          isOption={"none"}
        />
        <SearchTrimBox
          name={"Prestige"}
          description={"실용적이고 기본적인 기능을 갖춘 베이직 트림"}
          price={38960000}
          status={"none"}
          isOption={"none"}
        />

        <SearchTrimBox
          name={"Caligraphy"}
          description={"실용적이고 기본적인 기능을 갖춘 베이직 트림"}
          price={2000}
          status={"default"}
          isOption={"add"}
        />
      </TrimSelectContainer>
      <CheckOptionContainer>
        <OptionCheckBox />
        <OptionCheckBox />
        <OptionCheckBox />
        <OptionCheckBox />
        <OptionCheckBox />
        <OptionCheckBox />
        <OptionCheckBox />
        <OptionCheckBox />
        <OptionCheckBox />
      </CheckOptionContainer>
      <ButtonContainer>
        <Button $x={9.5625} $y={2.25} text={"나가기"} />
        <Button $x={9.5625} $y={2.25} text={"확인"} />
      </ButtonContainer>
    </Container>
  );
}

const Container = styled.div<ScreenContainerProps>`
  display: ${({ $show }) => ($show ? "flex" : "none")};
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  position: relative;
  width: 100%;
  height: 16rem;

  animation: ${({ $show }) =>
    $show ? "appear 0.5s ease-in-out forwards" : ""};
  animation: ${({ $loading }) =>
    $loading ? "disappear 0.5s ease-in-out forwards" : ""};

  @keyframes appear {
    0% {
      opacity: 0;
    }
    100% {
      opacity: 1;
    }
  }

  @keyframes disappear {
    0% {
      opacity: 1;
    }
    100% {
      opacity: 0;
    }
  }
`;

const TrimSelectContainer = styled.div`
  margin: 1.6rem 0 0.75rem 0;
  display: flex;
  flex-direction: row;
  gap: 0.5rem;
`;

const CheckOptionContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: row;
  flex-wrap: wrap;
  gap: 0.7rem;
`;

const ButtonContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: row;
  gap: 0.5rem;
  margin: 1.5rem;
`;
