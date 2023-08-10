import styled, { css, RuleSet } from "styled-components";

export type CSSProps = {
  $style?: RuleSet;
};

export const Text = styled.p<CSSProps>`
  ${(props) => props.$style}
`;

export const DefaultStyle = {
  General: css`
    ${(props) => props.theme.fonts.ContentMedium1};
  `,
  Text: css`
    color: ${(props) => props.theme.colors.COOLGREY2};
    ${(props) => props.theme.fonts.ContentLarge};
  `,
  Qutoation: css`
    ${(props) => props.theme.fonts.ContentMedium2};
  `,
  Option: css`
    ${(props) => props.theme.fonts.ContentSmall};
  `,
};

export type StyleKey =
  | "ConsiderDefault"
  | "ConsiderSelected"
  | "AddSelectedToConsider"
  | "AddDefault"
  | "ConsiderSelectToAdd"
  | "AddSelected";

export const OptionStyle = {
  ConsiderDefault: css`
    color: ${(props) => props.theme.colors.BLACK};
    background: ${(props) => props.theme.colors.WHITE};
    border-color: ${(props) => props.theme.colors.BLACK};
  `,
  ConsiderSelected: css`
    color: ${(props) => props.theme.colors.WHITE};
    background: ${(props) => props.theme.colors.GOLD5};
    border-color: ${(props) => props.theme.colors.WHITE};
  `,
  AddSelectedToConsider: css`
    color: ${(props) => props.theme.colors.WHITE};
    background: ${(props) => props.theme.colors.NAVYBLUE5};
    border-color: ${(props) => props.theme.colors.WHITE};
  `,
  AddDefault: css`
    color: ${(props) => props.theme.colors.WHITE};
    background: ${(props) => props.theme.colors.NAVYBLUE5};
    border-color: ${(props) => props.theme.colors.BLACK};
  `,
  ConsiderSelectToAdd: css`
    color: ${(props) => props.theme.colors.GOLD5};
    background: ${(props) => props.theme.colors.WHITE};
    border-color: ${(props) => props.theme.colors.GOLD5};
  `,
  AddSelected: css`
    color: ${(props) => props.theme.colors.NAVYBLUE5};
    background: ${(props) => props.theme.colors.WHITE};
    border-color: ${(props) => props.theme.colors.NAVYBLUE5};
  `,
};
