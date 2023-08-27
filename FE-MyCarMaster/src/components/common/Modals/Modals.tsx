import { ModalType } from "@constants/Modal.constants";
import ExitModal from "./ModalContents/ExitModal";
import { styled } from "styled-components";
import theme from "@styles/Theme";
import Button from "../Button/Button";
import ChangeTrimModal from "./ModalContents/ChangeTrimModal";
import ChangeEngineModal from "./ModalContents/ChangeEngineModal";
import { useEffect, useState } from "react";
import { UnselectableOptionProps } from "types/options.types";
import ConfirmModal from "./ModalContents/ConfirmModal";
import AlertModal from "./ModalContents/AlertModal";

interface ModalProps {
  type: ModalType;
  onClick: (id?: number) => void;
  setIsOpen: (isOpen: boolean) => void;
  unselectableOption?: UnselectableOptionProps[];
  isAlert?: boolean;
  text?: string[];
}

export function Modals({
  type,
  onClick,
  setIsOpen,
  unselectableOption,
  isAlert,
  text,
}: ModalProps) {
  const [isModalOpen, setIsModalOpen] = useState(false);

  useEffect(() => {
    setIsModalOpen(true);
  }, []);

  const closeModal = () => {
    setIsModalOpen(false);
    setIsOpen(false);
  };

  const ModalContent = (function () {
    switch (type) {
      case "CLOSE":
        return <ExitModal />;
      case "CHANGE_TRIM":
        return <ChangeTrimModal />;
      case "CHANGE_SEARCH_TRIM":
        return <ChangeTrimModal isSearch={true} />;
      case "CHANGE_ENGINE":
        if (unselectableOption && unselectableOption.length > 0) {
          return (
            <ChangeEngineModal
              id={unselectableOption[0].id}
              name={unselectableOption[0].name}
              price={unselectableOption[0].price}
            />
          );
        } else return <></>;
      case "CONFIRM":
        return <ConfirmModal />;
      case "ALERT":
        return <AlertModal text={text as string[]} />;
      default:
        return <></>;
    }
  })();

  const handleConfirm = () => {
    onClick();
    setIsModalOpen(false);
  };

  return (
    <>
      {isModalOpen && (
        <ModalOverlay>
          <Container>
            {ModalContent}
            <ButtonContainer>
              {isAlert ? (
                <></>
              ) : (
                <Button
                  $x={9.625}
                  $y={2.25}
                  $backgroundcolor={`${theme.colors.WHITE}`}
                  $textcolor={`${theme.colors.NAVYBLUE5}`}
                  $bordercolor={`${theme.colors.NAVYBLUE5}`}
                  text={"취소"}
                  handleClick={closeModal}
                />
              )}
              <Button
                $x={9.625}
                $y={2.25}
                $backgroundcolor={`${theme.colors.NAVYBLUE5}`}
                $textcolor={`${theme.colors.WHITE}`}
                $bordercolor={`${theme.colors.NAVYBLUE5}`}
                text={"확인"}
                handleClick={handleConfirm}
              />
            </ButtonContainer>
          </Container>
        </ModalOverlay>
      )}
    </>
  );
}

const ModalOverlay = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  background-color: rgba(0, 0, 0, 0.6);
  z-index: 999;
`;

const Container = styled.div`
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);

  width: 45rem;
  height: 25rem;
  background-color: ${theme.colors.WHITE};

  padding: 2rem;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

const ButtonContainer = styled.div`
  display: flex;
  gap: 1rem;

  margin-top: 2rem;
`;
