import styled, { css, RuleSet } from "styled-components";
import { FontType } from "@styles/Theme";

export type CSSProps = {
  $style?: RuleSet;
  $font?: FontType;
};

export const Text = styled.p<CSSProps>`
  ${(props) => props.$style}
  ${(props) => props.$font}
`;

export const DefaultStyle = {
  General: css`
    ${(props) => props.theme.fonts.Medium12};
  `,
  Text: css`
    color: ${(props) => props.theme.colors.COOLGREY2};
    ${(props) => props.theme.fonts.Medium13};
  `,
  Qutoation: css`
    ${(props) => props.theme.fonts.Medium10};
  `,
  Option: css`
    ${(props) => props.theme.fonts.Medium8};
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
