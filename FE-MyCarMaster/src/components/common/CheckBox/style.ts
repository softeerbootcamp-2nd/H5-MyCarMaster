import styled from "styled-components";
import SquareCheck from "../../../assets/icons/SquareCheck.svg";

export const Container = styled.div<{ $checked: boolean }>`
  display: flex;
  align-items: center;
  padding: 1rem;
  width: 22.875rem;
  height: 4.125rem;
  gap: 0.875rem;
  border: 1px solid ${(props) => props.theme.colors.GREY2};
  background-color: ${(props) =>
    props.$checked && props.theme.colors.NAVYBLUE5};
  overflow: hidden;
`;

export const CheckBox = styled.label<{ $checked: boolean }>`
  display: flex;
  align-items: center;
  position: relative;
  min-width: 2rem;
  min-height: 2rem;
  border: 2px solid
    ${(props) =>
      props.$checked ? props.theme.colors.WHITE : props.theme.colors.GREY2};
  background-color: ${(props) =>
    props.$checked && props.theme.colors.NAVYBLUE5};

  cursor: pointer;
`;

export const Check = styled.input`
  position: absolute;
  opacity: 0;
  width: 100%;
  height: 100%;
  z-index: 1;
`;

export const CheckStyle = styled.div<{ $checked?: boolean }>`
  &:after {
    //SqaureCheck Icon
    content: "";
    position: absolute;
    top: 0.5rem;
    left: 0.4rem;
    width: 150%;
    height: 150%;
    background-image: url(${SquareCheck});
    background-repeat: no-repeat;
    filter: ${(props) =>
      props.$checked ? "invert(0%) sepia(100%) brightness(100%)" : "none"};
  }
`;

export const Name = styled.p<{ $checked: boolean }>`
  ${(props) => props.theme.fonts.Medium10};
  color: ${(props) =>
    props.$checked ? props.theme.colors.WHITE : props.theme.colors.BLACK};

  width: 100%;
`;

export const Detail = styled.p<{ $checked: boolean }>`
  ${(props) => props.theme.fonts.Medium10};
  color: ${(props) =>
    props.$checked ? props.theme.colors.WHITE : props.theme.colors.NAVYBLUE5};
  width: 6rem;
  text-align: right;
`;

// type CSSProps = {
//   $background?: RuleSet;
//   $text?: RuleSet;
// };
