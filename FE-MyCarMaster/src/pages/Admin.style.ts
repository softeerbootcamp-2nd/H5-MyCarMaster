import styled from "styled-components";
import { Flex, Text } from "@styles/core.style";

export const ModalOverlay = styled.div`
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background-color: rgba(0, 0, 0, 0.6);
  width: 100%;
  height: 100%;
  overflow: auto;
  z-index: 99;
`;

export const AdminLogin = styled(Flex)`
  position: absolute;
  width: 40rem;
  height: auto;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  flex-direction: column;
  background-color: ${(props) => props.theme.colors.WHITE};
  padding: 2.5rem;
  gap: 1rem;
`;

export const AdminLoginTitle = styled(Text)`
  ${(props) => props.theme.fonts.Medium15};
  margin: 0 4rem;
  text-align: center;
`;

export const AdminShortContent = styled(Text)`
  ${(props) => props.theme.fonts.Regular8};
  color: ${(props) => props.theme.colors.SMOOTH_RED};
  text-align: center;
`;

export const AdminContentText = styled(Text)`
  ${(props) => props.theme.fonts.Regular10};
  color: ${(props) => props.theme.colors.BLACK};
  text-align: left;
  width: 100%;

  animation: showAdminText 0.1s ease-in-out;
  @keyframes showAdminText {
    0% {
      transform: translate(1rem, 3rem);
    }
    100% {
      transform: translate(0, 0);
    }
  }
`;

export const Input = styled.input<CheckTypeProps>`
  ${(props) => props.theme.fonts.Regular13};
  width: 100%;
  height: 3rem;
  border-radius: 0.5rem;
  padding: 0 1rem;
  outline: none;
  border: 1px solid
    ${(props) => {
      switch (props.$status) {
        case "success":
          return props.theme.colors.ACTIVE_BLUE;
        case "error":
        case "invalid":
          return props.theme.colors.SMOOTH_RED;
        case "default":
          return props.theme.colors.ACTIVE_BLUE;
        default:
          return props.theme.colors.BLACK;
      }
    }};
`;

interface CheckTypeProps {
  $status: string;
}

export const CheckText = styled(Text)<CheckTypeProps>`
  ${(props) => props.theme.fonts.Regular10};

  color: ${(props) => {
    switch (props.$status) {
      case "success":
        return props.theme.colors.ACTIVE_BLUE;
      case "error":
      case "invalid":
        return props.theme.colors.SMOOTH_RED;
      case "default":
        return props.theme.colors.BLACK;
      default:
        return;
    }
  }};

  position: absolute;
  right: 1rem;
  top: 50%;
  transform: translateY(-50%);

  ${(props) =>
    props.$status === "invalid"
      ? `
    animation: shakingText 0.5s ease-in-out;
    
  `
      : `
  animation: showCheckText 0.1s ease-in-out;
  `}

  @keyframes showCheckText {
    0% {
      opacity: 0;
    }
    100% {
      opacity: 1;
    }
  }

  @keyframes shakingText {
    0% {
      opacity: 0;
    }
    25% {
      opacity: 1;
    }
    50% {
      opacity: 0;
    }
    75% {
      opacity: 1;
    }
    100% {
      opacity: 0;
    }
  }
`;

export const LogoImg = styled.img`
  margin: 0 4rem;
  width: 20%;
  min-width: 12rem;
  max-width: 16rem;
  z-index: 100;
  cursor: pointer;
`;
