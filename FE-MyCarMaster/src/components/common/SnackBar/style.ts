import styled from "styled-components";

export const Container = styled.div<{ $show: boolean }>`
  display: ${(props) => (props.$show ? "flex" : "none")};
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 0.375rem;
  background-color: ${(props) => props.theme.colors.BLACK};
  backdrop-filter: blur(4px);

  position: fixed;
  top: 15%;
  left: 50%;
  transform: translateX(-50%);
  z-index: 11;
`;

export const Message = styled.p`
  ${(props) => props.theme.fonts.Medium12};
  color: ${(props) => props.theme.colors.WHITE};
  text-align: center;
  line-height: 1.25;
  padding: 1.125rem 2.5rem 0 2.5rem;
`;

export const SubMessage = styled.p`
  padding: 0rem 2.5rem 1.125rem 2.5rem;
  ${(props) => props.theme.fonts.Medium9};
  color: ${(props) => props.theme.colors.WHITE};
  opacity: 0.6;
`;
