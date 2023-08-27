import styled, { css, RuleSet } from "styled-components";

export const Text = styled.p<CSSProps>`
  ${(props) => props.$text}
`;

export const TextContainer = styled.div<CSSProps>`
  ${(props) => props.$padding}
  ${(props) => props.$background}
  display: flex;
  align-items: center;
`;

type CSSProps = {
  $background?: RuleSet;
  $text?: RuleSet;
  $padding?: RuleSet;
};

export const Option = {
  Background: css`
    background-color: ${(props) => props.theme.colors.NAVYBLUE3};
  `,
  Text: css`
    color: ${(props) => props.theme.colors.NAVYBLUE5};
    ${(props) => props.theme.fonts.Medium8}
  `,

  Padding: css`
    padding: 0.13rem 0.5rem;
  `,
};

export const SearchTrim = {
  AddBackground: css`
    background-color: ${(props) => props.theme.colors.NAVYBLUE1};
  `,
  AddText: css`
    color: ${(props) => props.theme.colors.NAVYBLUE5};
    ${(props) => props.theme.fonts.Regular8}
  `,
  DefaultBackground: css`
    background-color: ${(props) => props.theme.colors.NAVYBLUE5};
  `,
  DefaultText: css`
    color: ${(props) => props.theme.colors.NAVYBLUE1};
    ${(props) => props.theme.fonts.Regular8}
  `,
  Padding: css`
    padding: 0.13rem 0.5rem;
  `,
};

export const ConsiderOption = {
  Padding: css`
    padding: 0 0.25rem;
  `,
  Background: css`
    background-color: ${(props) => props.theme.colors.WHITE};
  `,
  Text: css`
    color: ${(props) => props.theme.colors.GOLD5};
    ${(props) => props.theme.fonts.Regular8}
  `,
};
