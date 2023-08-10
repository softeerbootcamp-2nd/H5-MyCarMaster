import styled, { css, RuleSet } from "styled-components";

export const Decoration = styled.p<CSSProps>`
  ${(props) => props.theme.fonts.BodySmall};
  ${(props) => props.$style}
`;

export const OptionName = styled.p<CSSProps>`
  ${(props) => props.theme.fonts.titleLarge};
  ${(props) => props.$style}
`;

export const Description = styled.p<CSSProps>`
  ${(props) => props.theme.fonts.contentMedium};
  ${(props) => props.$style}
`;

export const Price = styled.p<CSSProps>`
  ${(props) => props.theme.fonts.contentLarge};
  ${(props) => props.$style}
`;

// export const Tag = styled.button`
//   width: 3.25rem;
//   height: 1.25rem;
//   font-size: 0.625rem;
// `;

export const Name = styled.p<CSSProps>`
  ${(props) => props.theme.fonts.titleSmall};
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

export const DetailModelOptionContainer = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 0.5rem;
`;

export const TopContainer = styled.div`
  height: 5.75rem;
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
`;

export const BottomContainer = styled.div`
  display: flex;
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
};

export const ActiveColor = {
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
  OptionName: css`
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
  Name: css`
    color: ${(props) => props.theme.colors.WHITE};
  `,
};

export const DefaultColor = {
  Background: css`
    background-color: ${(props) => props.theme.colors.WHITE};
  `,
  Decoration: css`
    color: ${(props) => props.theme.colors.NAVYBLUE5};
  `,
  OptionName: css`
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
  Name: css`
    color: ${(props) => props.theme.colors.BLACK};
  `,
};

export const NoneColor = {
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
