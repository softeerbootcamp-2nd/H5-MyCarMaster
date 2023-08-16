import React, { createContext, useState } from "react";

interface ModalContextType {
  isModalOpen: boolean;
  showModal: () => void;
  closeModal: () => void;
}

export const ModalContext = createContext<ModalContextType>({
  isModalOpen: false,
  showModal: () => {},
  closeModal: () => {},
});

export const ModalProvider = ({ children }: { children: React.ReactNode }) => {
  const [isModalOpen, setIsModalOpen] = useState(false);

  const showModal = () => {
    setIsModalOpen(true);
  };

  const closeModal = () => {
    setIsModalOpen(false);
  };

  return (
    <ModalContext.Provider value={{ isModalOpen, showModal, closeModal }}>
      {children}
    </ModalContext.Provider>
  );
};

export default ModalContext;
