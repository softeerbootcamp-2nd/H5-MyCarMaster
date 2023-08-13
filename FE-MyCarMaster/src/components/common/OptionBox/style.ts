import styled, { css, RuleSet } from "styled-components";

export const Decoration = styled.p<CSSProps>`
  ${(props) => props.theme.fonts.BodySmall};
  ${(props) => props.$style}
`;

export const Name = styled.p<CSSProps>`
  ${(props) => props.$size}
  ${(props) => props.$style}
`;

export const Description = styled.p<CSSProps>`
  ${(props) => props.theme.fonts.contentMedium};
  ${(props) => props.$style}
  margin: auto 0;
`;

export const Price = styled.p<CSSProps>`
  ${(props) => props.theme.fonts.contentLarge};
  ${(props) => props.$style}
`;

export const Ratio = styled.p`
  ${(props) => props.theme.fonts.BodySmall};
  color: ${(props) => props.theme.colors.NAVYBLUE4};
`;

export const Detail = styled.p<CSSProps>`
  ${(props) => props.theme.fonts.BodySmall};
  ${(props) => props.$style}
`;

export const TopContainer = styled.div`
  height: 5.75rem;
  display: flex;
  flex-direction: column;
`;

export const BottomContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-around;
`;

export const ButtonContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 0.5rem;
`;

export const Container = styled.div<{ $style?: RuleSet }>`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: flex-start;
  gap: 0.5rem;
  ${(props) => props.$style}

  border: 1px solid ${(props) => props.theme.colors.GREY2};
  width: 12.5rem;
  height: 10.25rem;
  padding: 0.75rem 1rem;

  cursor: pointer;
`;

type CSSProps = {
  $style?: RuleSet;
  $size?: RuleSet;
};

export const ActiveCSS = {
  Background: css`
    background-color: ${(props) => props.theme.colors.NAVYBLUE5};
  `,

  BackgroundConsider: css`
    background-color: ${(props) => props.theme.colors.GOLD5};
  `,

  Decoration: css`
    color: ${(props) => props.theme.colors.NAVYBLUE1};
  `,
  DecorationConsider: css`
    color: ${(props) => props.theme.colors.WHITE};
    opacity: 0.5;
  `,
  Name: css`
    color: ${(props) => props.theme.colors.WHITE};
  `,
  NameConsider: css`
    color: ${(props) => props.theme.colors.WHITE};
  `,
  Description: css`
    color: ${(props) => props.theme.colors.WHITE};
    opacity: 0.5;
  `,
  Price: css`
    color: ${(props) => props.theme.colors.WHITE};
  `,
  Detail: css`
    color: ${(props) => props.theme.colors.WHITE};
  `,
};

export const DefaultCSS = {
  Background: css`
    background-color: ${(props) => props.theme.colors.WHITE};
  `,
  Decoration: css`
    color: ${(props) => props.theme.colors.NAVYBLUE5};
  `,
  Name: css`
    color: ${(props) => props.theme.colors.BLACK};
  `,
  Description: css`
    color: ${(props) => props.theme.colors.GREY3};
  `,
  Price: css`
    color: ${(props) => props.theme.colors.BLACK};
  `,
  Detail: css`
    color: ${(props) => props.theme.colors.BLACK};
  `,

  SizeDown: css`
    ${(props) => props.theme.fonts.titleSmall};
  `,

  SizeUp: css`
    ${(props) => props.theme.fonts.titleLarge};
  `,
};

export const NoneCSS = {
  Background: css`
    background-color: ${(props) => props.theme.colors.GREY2};
  `,
  Decoration: css`
    color: ${(props) => props.theme.colors.WHITE};
  `,
  OptionName: css`
    color: ${(props) => props.theme.colors.WHITE};
  `,
  Description: css`
    color: ${(props) => props.theme.colors.BLACK};
    opacity: 0.5;
  `,
  Price: css`
    color: ${(props) => props.theme.colors.BLACK};
    opacity: 0.5;
  `,
  Detail: css`
    color: ${(props) => props.theme.colors.BLACK};
    opacity: 0.5;
  `,
};
