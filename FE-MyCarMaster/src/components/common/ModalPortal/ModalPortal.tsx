import React, { useContext, useRef } from "react";
import ReactDOM from "react-dom";
import { ModalContainer } from "./styles";
import ModalContext from "../../../contexts/ModalContext";

interface ModalPortalProps {
  children: React.ReactNode;
}

export const ModalPortal = ({ children }: ModalPortalProps) => {
  const { isModalOpen, closeModal } = useContext(ModalContext);

  const modalRef = useRef<HTMLDivElement>(null);
  const el = document.getElementById("modal") as HTMLElement;

  const handleOutsideClick = (e: React.MouseEvent<HTMLDivElement>) => {
    if (modalRef.current && !modalRef.current.contains(e.target as Node)) {
      closeModal();
    }
  };
  return (
    isModalOpen &&
    ReactDOM.createPortal(
      <ModalContainer onClick={handleOutsideClick}>
        <div ref={modalRef}>{children}</div>
      </ModalContainer>,
      el
    )
  );
};
