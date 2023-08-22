import React, { useState } from "react";
import { createPortal } from "react-dom";
import styled from "styled-components";
import AngleUp from "@assets/icons/AngleUp.svg";
import AngleDown from "@assets/icons/AngleDown.svg";
import MyTrimSearchScreen from "./screen_view/MyTrimSearchScreen";
import DefaultOptionScreen from "./screen_view/DefaultOptionScreen";
import { QuotationType } from "types/quotation.types";
import { useTrimState, useTrimDispatch } from "@contexts/TrimContext";
import { useQuotationDispatch } from "@contexts/QuotationContext";
import { SnackBar, Modals } from "@common/index";
import { ModalType } from "@constants/Modal.constants";

type FoldScreenProps = {
  text: string;
  $switch: "searchTrim" | "option";
};

export default function FoldScreen({ text, $switch }: FoldScreenProps) {
  const [isFold, setIsFold] = useState(false);
  const [loading, setLoading] = useState(false);
  const [isOpen, setIsOpen] = useState(false);
  const [saveData, setSaveData] = useState<{
    optionList: QuotationType[];
    selected: number;
  } | null>(null);
  const [snackBar, setSnackBar] = useState<string[] | null>(null);
  const { trimList, trimId } = useTrimState();
  const trimDispatch = useTrimDispatch();
  const quotationDispatch = useQuotationDispatch();

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

  const searchHandler = (
    optionList: QuotationType[],
    selected: number,
    sign?: boolean | undefined
  ) => {
    if (trimId !== selected && (sign === undefined || !sign)) {
      setIsOpen(true);
      setSaveData({ optionList, selected });
      return;
    }

    quotationDispatch({
      type: "RESET_QUOTATION",
    });

    const target = document.querySelector(".fold-screen") as HTMLDivElement;
    target.style.animation = "moveDown 0.5s ease-in-out forwards";

    const messages = optionList.map((option) => option.name);
    setSnackBarHandler(messages);

    setLoading(true);

    setTimeout(() => {
      setIsFold(false);
      setLoading(false);
    }, 500);

    trimDispatch({
      type: "SELECT_TRIM",
      payload: {
        trimId: selected,
      },
    });

    const selectTrim = trimList.find((trim) => trim.id === selected);

    quotationDispatch({
      type: "SET_TRIM_QUOTATION",
      payload: {
        name: selectTrim?.name,
        price: selectTrim?.price,
      },
    });

    quotationDispatch({
      type: "SET_MY_TRIM_OPTIONS",
      payload: {
        optionList: optionList,
      },
    });
    setIsOpen(false);
  };

  const setSnackBarHandler = (messages: string[] | null) => {
    setSnackBar(messages);
  };

  return (
    <Conatiner>
      <ButtonContainer
        onClick={showFold}
        $style={isFold}
        className="fold-screen"
      >
        {isFold ? <AngleDownIcon /> : <AngleUpIcon />}

        <Text $show={loading}>
          {!isFold
            ? text
            : $switch === "searchTrim"
            ? "원하는 기능을 선택하시면 해당 기능이 포함된 트림을 추천해드려요!"
            : "추가 옵션 선택하러 가기"}
        </Text>

        {!isFold && $switch === "searchTrim" && <Bar $show={loading} />}
        {$switch === "searchTrim" ? (
          <MyTrimSearchScreen
            $loading={loading}
            $show={isFold}
            onClick={(e: React.MouseEvent<HTMLDivElement, MouseEvent>) =>
              e.stopPropagation()
            }
            onSearch={searchHandler}
          />
        ) : (
          <DefaultOptionScreen
            $loading={loading}
            $show={isFold}
            onClick={(e: React.MouseEvent<HTMLDivElement, MouseEvent>) =>
              e.stopPropagation()
            }
          />
        )}
      </ButtonContainer>
      {snackBar !== null &&
        createPortal(
          <SnackBar
            messages={snackBar ? snackBar : []}
            onClose={() => setSnackBarHandler(null)}
          />,
          document.body
        )}
      {isOpen && (
        <Modals
          type={ModalType.CHANGE_SEARCH_TRIM}
          onClick={() =>
            searchHandler(saveData!.optionList, saveData!.selected, true)
          }
          setIsOpen={setIsOpen}
        />
      )}
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

  width: 8rem;
  height: 0.0625rem;
  background-color: ${({ theme }) => theme.colors.ACTIVE_BLUE};
  animation: ${({ $show }) =>
    $show ? "barDisappear 0.5s ease-in-out" : "barAppear 0.5s ease-in-out"};

  @keyframes barDisappear {
    0% {
      opacity: 1;
      width: 8rem;
    }
    100% {
      opacity: 0;
      width: 0;
    }
  }

  @keyframes barAppear {
    0% {
      opacity: 0;
      width: 0;
    }
    100% {
      opacity: 1;
      width: 8rem;
    }
  }
`;

const Text = styled.p<{ $show: boolean }>`
  ${({ theme }) => theme.fonts.ContentMedium1};
  animation: ${({ $show }) =>
    $show ? "textDisappear 0.5s ease-in-out" : "textAppear 0.5s ease-in-out"};

  @keyframes textDisappear {
    0% {
      opacity: 1;
    }
    100% {
      opacity: 0;
    }
  }

  @keyframes textAppear {
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
  width: ${({ $style }) => ($style ? "100vw" : "10rem")};
  height: ${({ $style }) => ($style ? "80vh" : "100%")};
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
