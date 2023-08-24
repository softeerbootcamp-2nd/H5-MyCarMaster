import styled, { css, RuleSet } from "styled-components";

export const Decoration = styled.p<CSSProps>`
  ${(props) => props.theme.fonts.Regular8};
  ${(props) => props.$style}
  line-height: 1.25rem;
`;

export const Name = styled.p<CSSProps>`
  ${(props) => props.$size}
  ${(props) => props.$style}
`;

export const Description = styled.p<CSSProps>`
  ${(props) => props.theme.fonts.Regular10};
  ${(props) => props.$style}
  line-height: 1.4rem;
  margin: auto 0;
`;

export const Price = styled.p<CSSProps>`
  ${(props) => props.theme.fonts.Medium15};
  ${(props) => props.$style}
`;

export const Ratio = styled.p`
  ${(props) => props.theme.fonts.Regular8};
  color: ${(props) => props.theme.colors.NAVYBLUE4};
`;

export const Detail = styled.p<CSSProps>`
  ${(props) => props.theme.fonts.Medium8};
  ${(props) => props.$style}

  &:hover {
    text-decoration: underline;
  }
`;

export const TopContainer = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
`;

export const BottomContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  gap: 0.5rem;
`;

export const ButtonContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 0.5rem;
`;

export const DecorationContainer = styled.div`
  display: flex;
  justify-content: space-between;
  width: 100%;
  margin-bottom: 0.5rem;
`;

export const Container = styled.div<{ $style?: RuleSet }>`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: flex-start;
  gap: 0.5rem;
  ${(props) => props.$style}

  border: 1px solid ${(props) => props.theme.colors.GREY2};
  width: 14.5rem;
  height: 12.25rem;
  padding: 1rem 1.25rem;

  cursor: pointer;

  &:hover {
    transition: transform 0.5s ease-in-out;
    transform: scale(1.05);
  }
  &:not(:hover) {
    transition: transform 0.5s ease-in-out;
    transform: scale(1);
  }
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

  LongSizeDown: css`
    ${(props) => props.theme.fonts.Medium11};
    line-height: 1.7rem;
  `,

  SizeDown: css`
    ${(props) => props.theme.fonts.Medium13};
    line-height: 2rem;
  `,

  SizeUp: css`
    ${(props) => props.theme.fonts.Medium17};
    line-height: 1.4rem;
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
