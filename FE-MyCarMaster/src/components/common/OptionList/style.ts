import styled, { css, keyframes } from "styled-components";
import AngleUp from "../../../assets/icons/AngleUp.svg";
import AngleDown from "../../../assets/icons/AngleDown.svg";

const expandAnimation = keyframes`
  from {
    height: calc(2rem + 1.5rem + 0.75rem + 0.75rem);
  }
  to {
    height: 100%;
  }
`;

const ListTextAppearAnimation = keyframes`
    from {
        opacity: 0;
    }
    to {
        opacity: 1;
    }
`;

const LineAppearAnimation = keyframes`
    from {
    }
    to {
        width: 100%;
    }
`;

const LineDisappearAnimation = keyframes`
    from {
        width: 100%;
    }
    to {
        width: 0%;
    }
`;

export const Container = styled.div<{ $isOpen: boolean }>`
  display: flex;
  flex-direction: column;
  padding: 1rem 1.5rem;
  width: 80%;
  border: 1px solid ${(props) => props.theme.colors.GREY2};
  background-color: ${(props) => props.theme.colors.WHITE};

  ${(props) =>
    props.$isOpen &&
    css`
      animation: ${expandAnimation} 1s ease-in-out forwards;
    `}

  & + & {
    margin: 0.875rem 0;
  }
`;
export const ListContainer = styled.div<{ $isOpen: boolean }>`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

export const Text = styled.div`
  font-size: 1.25rem;
`;

export const Icon = styled.div<{ $isOpen: boolean }>`
  width: 2rem;
  height: 2rem;
  background-image: url(${(props) => (props.$isOpen ? AngleUp : AngleDown)});
  background-repeat: no-repeat;
  background-position: center;
`;

export const OptionItem = styled.div<{ $isOpen: boolean }>`
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0.75rem 0;

  ${(props) =>
    props.$isOpen &&
    css`
      animation: ${ListTextAppearAnimation} 1s ease-in-out forwards;
    `}
`;

export const Line = styled.div<{ $isOpen: boolean; $isFirst: boolean }>`
  width: 0%;
  height: 1px;
  background-color: ${(props) => props.theme.colors.GREY2};
  align-self: center;

  ${(props) =>
    props.$isFirst
      ? ""
      : props.$isOpen
      ? css`
          animation: ${LineAppearAnimation} 1s ease-in-out forwards;
        `
      : css`
          animation: ${LineDisappearAnimation} 1s ease-in-out forwards;
        `}
`;
