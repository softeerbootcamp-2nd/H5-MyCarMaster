import styled, { css, RuleSet } from "styled-components";

export type CSSProps = {
  $style?: RuleSet;
};

export const Text = styled.p<CSSProps>`
  ${(props) => props.$style}
`;

export const DarkColor = {
  Head: css`
    color: ${(props) => props.theme.colors.WHITE};
    ${(props) => props.theme.fonts.BodyMedium};
  `,
  Content: css`
    color: ${(props) => props.theme.colors.GREY3};
    ${(props) => props.theme.fonts.BodySmall};
  `,
  Price: css`
    color: ${(props) => props.theme.colors.COOLGREY1};
    ${(props) => props.theme.fonts.BodySmall};
  `,
};

export const DefaultColor = {
  Head: css`
    color: ${(props) => props.theme.colors.BLACK};
    ${(props) => props.theme.fonts.BodyMedium};
  `,
  Content: css`
    color: ${(props) => props.theme.colors.GREY3};
    ${(props) => props.theme.fonts.BodySmall};
  `,
  Price: css`
    color: ${(props) => props.theme.colors.COOLGREY2};
    ${(props) => props.theme.fonts.BodySmall};
  `,
};

export const LightColor = {
  Head: css`
    color: ${(props) => props.theme.colors.BLACK};
    ${(props) => props.theme.fonts.BodyMedium};
  `,
  Content: css`
    color: ${(props) => props.theme.colors.GREY3};
    ${(props) => props.theme.fonts.BodySmall};
  `,
  Price: css`
    color: ${(props) => props.theme.colors.COOLGREY2};
    ${(props) => props.theme.fonts.BodySmall};
  `,
};
