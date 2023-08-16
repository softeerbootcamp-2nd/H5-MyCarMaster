import React from "react";
import { useModal } from "../../../hooks/useModal";
import { useNavigate } from "react-router-dom";

export function Modals() {
  const { closeModal } = useModal();
  const navigate = useNavigate();
  return <div>Modals</div>;
}
