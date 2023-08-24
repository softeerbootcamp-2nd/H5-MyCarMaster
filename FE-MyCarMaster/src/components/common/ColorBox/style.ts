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

export const Color = {
  Head: css`
    color: ${(props) => props.theme.colors.BLACK};
    ${(props) => props.theme.fonts.Medium10};
  `,
  Content: css`
    color: ${(props) => props.theme.colors.GREY3};
    ${(props) => props.theme.fonts.Regular8};
  `,
  OuterPrice: css`
    color: ${(props) => props.theme.colors.COOLGREY2};
    ${(props) => props.theme.fonts.Regular6};
  `,
  InnerPrice: css`
    color: ${(props) => props.theme.colors.COOLGREY2};
    ${(props) => props.theme.fonts.Medium10};
  `,
};
