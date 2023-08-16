import styled from "styled-components";

export const ModalContainer = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;

  background: rgba(35, 35, 35, 0.6);
  backdrop-filter: blur(2px);

  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 99;
`;
