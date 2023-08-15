import { useState } from "react";
import styled from "styled-components";
import AngleUp from "../../assets/icons/AngleUp.svg";
import AngleDown from "../../assets/icons/AngleDown.svg";
import ScreenContainer from "./screen_view/ScreenContainer";

type FoldScreenProps = {
  text: string;
  $switch: "searchTrim" | "option";
};

export default function FoldScreen({ text, $switch }: FoldScreenProps) {
  const [isFold, setIsFold] = useState(false);
  const [loading, setLoading] = useState(false);

  const showFold = (e: React.MouseEvent<HTMLDivElement, MouseEvent>) => {
    const target = e.currentTarget;
    if (isFold) {
      target.style.animation = "moveDown 0.5s ease-in-out forwards";
    } else {
      target.style.animation = "moveUp 0.5s ease-in-out forwards";
    }
    setLoading(true);

    setTimeout(() => {
      setIsFold(!isFold);
      setLoading(false);
    }, 500);
  };

  return (
    <Conatiner>
      <ButtonContainer onClick={showFold} $style={isFold}>
        {isFold ? <AngleDownIcon /> : <AngleUpIcon />}

        <Text $show={loading}>
          {!isFold
            ? text
            : $switch === "searchTrim"
            ? "원하는 기능을 선택하시면 해당 기능이 포함된 트림을 추천해드려요!"
            : "추가 옵션 선택하기"}
        </Text>

        {!isFold && $switch === "searchTrim" && <Bar $show={loading} />}
        <ScreenContainer
          $loading={loading}
          $show={isFold}
          onClick={(e: React.MouseEvent<HTMLDivElement, MouseEvent>) =>
            e.stopPropagation()
          }
        />
      </ButtonContainer>
    </Conatiner>
  );
}

const Conatiner = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  position: absolute;
  width: 100%;
  bottom: 0%;
  padding: 1rem 0;
  align-items: center;
  z-index: 1;
  overflow: hidden;
`;

const AngleUpIcon = styled.img.attrs({
  src: AngleUp,
})``;

const AngleDownIcon = styled.img.attrs({
  src: AngleDown,
})`
  margin: 0.5rem;
`;

const Bar = styled.div<{ $show: boolean }>`
  position: absolute;
  bottom: -10%;

  width: 6rem;
  height: 0.0625rem;
  background-color: ${({ theme }) => theme.colors.ACTIVE_BLUE};
  animation: ${({ $show }) =>
    $show ? "disappear 0.5s ease-in-out" : "appear 0.5s ease-in-out"};

  @keyframes disappear {
    0% {
      opacity: 1;
    }
    100% {
      opacity: 0;
    }
  }

  @keyframes appear {
    0% {
      opacity: 0;
    }
    100% {
      opacity: 1;
    }
  }
`;

const Text = styled.p<{ $show: boolean }>`
  ${({ theme }) => theme.fonts.ContentMedium1};
  animation: ${({ $show }) =>
    $show ? "disappear 0.5s ease-in-out" : "appear 0.5s ease-in-out"};

  @keyframes disappear {
    0% {
      opacity: 1;
    }
    100% {
      opacity: 0;
    }
  }

  @keyframes appear {
    0% {
      opacity: 0;
    }
    100% {
      opacity: 1;
    }
  }
`;

const ButtonContainer = styled.div<{ $style: boolean }>`
  display: flex;
  flex-direction: column;
  align-items: center;
  cursor: pointer;
  width: ${({ $style }) => ($style ? "100vw" : "auto")};
  height: ${({ $style }) => ($style ? "80vh" : "auto")};
  position: relative;

  background-color: ${({ theme }) => theme.colors.GREY1};
  box-shadow: ${({ $style }) =>
    $style ? "0px -6px 4px -4px rgba(0, 0, 0, 0.21);" : "0px 0px 0px 0px"};

  &:hover {
    ${AngleUpIcon} {
      animation: slowlyMove ease-in-out forwards infinite 1s;

      @keyframes slowlyMove {
        0% {
          transform: translateY(0%);
        }
        50% {
          transform: translateY(-25%);
        }
        100% {
          transform: translateY(0%);
        }
      }
    }
    ${AngleDownIcon} {
      animation: slowlyMove ease-in-out forwards infinite 1s;

      @keyframes slowlyMove {
        0% {
          transform: translateY(0%);
        }
        50% {
          transform: translateY(25%);
        }
        100% {
          transform: translateY(0%);
        }
      }
    }

    // Bar, Text Color Change
    ${Bar} {
      background-color: ${({ theme }) => theme.colors.NAVYBLUE5};
    }
    ${Text} {
      color: ${({ theme }) => theme.colors.NAVYBLUE5};
    }
  }

  @keyframes moveUp {
    from {
      transform: translateY(calc(100% - 2.25rem));
      width: 100vw;
      height: 80vh;
    }
    to {
      transform: translateY(0%);
      width: 100vw;
      height: 80vh;
    }
  }

  @keyframes moveDown {
    from {
      transform: translateY(0%);
      width: 100vw;
    }
    to {
      transform: translateY(calc(100% - 2.25rem));
    }
  }
`;
