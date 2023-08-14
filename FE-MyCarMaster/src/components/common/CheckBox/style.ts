import styled from "styled-components";

export const Container = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 1rem;
  width: 20.875rem;

  border: 1px solid ${(props) => props.theme.colors.GREY2};
  background-color: ${(props) => props.theme.colors.WHITE};
`;

export const CheckBox = styled.div`
  width: 2rem;
  height: 2rem;
  border: 2px solid ${(props) => props.theme.colors.GREY1};
  cursor: pointer;
`;

export const Check = styled.input`
  display: none;
  &:checked + ${CheckBox} {
    background-color: ${(props) => props.theme.colors.GOLD4};
  }
`;

export const Name = styled.p`
  ${(props) => props.theme.fonts.contentMedium};
  margin-left: 1rem;
`;

export const Detail = styled.p`
  ${(props) => props.theme.fonts.contentMedium};
  color: ${(props) => props.theme.colors.NAVYBLUE5};
  margin-right: 1rem;
`;

// type CSSProps = {
//   $background?: RuleSet;
//   $text?: RuleSet;
// };
