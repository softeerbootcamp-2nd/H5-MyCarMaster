import styled, { css, RuleSet } from "styled-components";

export const Container = styled.div<CSSProps>`
  width: 16.5rem;
  height: 12.25rem;
  padding: 1rem;

  display: flex;
  flex-direction: column;
  justify-content: space-around;

  ${(props) => props.$background}
  border: 1px solid ${(props) => props.theme.colors.GREY2};
`;

export const Name = styled.p<CSSProps>`
  ${(props) => props.$text}
  ${(props) => props.theme.fonts.titleLarge}
`;

export const Description = styled.p<CSSProps>`
  ${(props) => props.$text}
  ${(props) => props.theme.fonts.ContentMedium2}
`;

export const Price = styled.p<CSSProps>`
  ${(props) => props.$text}
  ${(props) => props.theme.fonts.contentLarge}
`;

export const BottomContainer = styled.div`
  display: flex;
  justify-content: space-between;
`;

type CSSProps = {
  $background?: RuleSet;
  $text?: RuleSet;
};

export const ActiveCSS = {
  Background: css`
    background-color: ${(props) => props.theme.colors.NAVYBLUE5};
  `,
  Name: css`
    color: ${(props) => props.theme.colors.WHITE};
  `,
  Description: css`
    color: ${(props) => props.theme.colors.WHITE};
    opacity: 0.5;
  `,
  Price: css`
    color: ${(props) => props.theme.colors.WHITE};
  `,
};

export const DefaultCSS = {
  Background: css`
    background-color: ${(props) => props.theme.colors.WHITE};
    border: 1px solid ${(props) => props.theme.colors.GREY2};
  `,
  Name: css`
    color: ${(props) => props.theme.colors.BLACK};
  `,
  Description: css`
    color: ${(props) => props.theme.colors.BLACK};
    opacity: 0.5;
  `,
  Price: css`
    color: ${(props) => props.theme.colors.BLACK};
  `,
};

export const NoneCSS = {
  Background: css`
    background-color: ${(props) => props.theme.colors.WHITE};
    border: 1px solid ${(props) => props.theme.colors.GREY2};
    opacity: 0.2;
  `,
  Name: css`
    color: ${(props) => props.theme.colors.BLACK};
  `,
  Description: css`
    color: ${(props) => props.theme.colors.BLACK};
    opacity: 0.5;
  `,
  Price: css`
    color: ${(props) => props.theme.colors.BLACK};
  `,
};
