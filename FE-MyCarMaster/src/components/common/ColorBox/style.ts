import styled, { css, RuleSet } from "styled-components";

type CSSProps = {
  $style?: RuleSet;
};

export const Text = styled.p<CSSProps>`
  ${(props) => props.$style}
`;

export const Price = styled.p<CSSProps>`
  ${(props) => props.$style}
  position: absolute;
  right: 0%;
  top: 50%;
  margin: 0 1rem;
  transform: translate(0%, -50%);
`;

export const DarkColor = {
  Head: css`
    color: ${(props) => props.theme.colors.WHITE};
    ${(props) => props.theme.fonts.BodyMedium};
  `,
  Content: css`
    color: ${(props) => props.theme.colors.GREY3};
    ${(props) => props.theme.fonts.BodySmall2};
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
    ${(props) => props.theme.fonts.BodySmall2};
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
    ${(props) => props.theme.fonts.BodySmall2};
  `,
  Price: css`
    color: ${(props) => props.theme.colors.COOLGREY2};
    ${(props) => props.theme.fonts.BodySmall};
  `,
};
