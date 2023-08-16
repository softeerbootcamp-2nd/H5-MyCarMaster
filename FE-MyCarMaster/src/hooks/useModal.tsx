import { useContext } from "react";
import ModalContext from "../contexts/ModalContext";

export function useModal() {
  const context = useContext(ModalContext);
  return context;
}
