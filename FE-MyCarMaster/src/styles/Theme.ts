import { DefaultTheme } from "styled-components";

const colors = {
  Black: "#222222",
  White: "#FFFFFF",
  CoolGrey1: "#C5C9D2",
  CoolGrey2: "#81899E",
  Grey1: "#F6F6F6",
  Grey2: "#DDDDDD",
  Grey3: "#7B7B7B",
  NavyBlue1: "#E7ECF9",
  NavyBlue2: "#D2D9EC",
  NavyBlue3: "#C6D2F0",
  NavyBlue4: "#96A9DC",
  NavyBlue5: "#1A3276",
  Gold4: "#FAF2ED",
  Gold5: "#9B6D54",
};

const fontSize = {
  Nano: "0.625rem",
  Microscopic: "0.75rem",
  Tiny: "0.8125rem",
  ExtraSmall: "0.875rem",
  Small: "1rem",
  Normal: "1.25rem",
  Large: "1.375rem",
  ExtraLarge: "1.5rem",
  Huge: "1.75rem",
  Gigantic: "2rem",
  Enormous: "2.25rem",
};

const fontWeight = {
  regular: 400,
  medium: 500,
  bold: 700,
};

const lineHeight = {
  ExtraCompact: "1rem",
  Compact: "1.25rem",
  Normal: "1.5rem",
  Loose: "1.75rem",
  Tight: "2rem",
  Spacious: "2.25rem",
  Expanded: "2.5rem",
  ExtraExpanded: "2.75rem",
};

const fonts = {
  display: {
    fontFamily: "HyundaiSansBold",
    fontSize: fontSize.Enormous,
    fontStyle: "normal",
    fontWeight: fontWeight.medium,
    lineHeight: lineHeight.ExtraExpanded,
  },

  titleLarge: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.Large,
    fontStyle: "normal",
    fontWeight: fontWeight.bold,
    lineHeight: lineHeight.Compact,
  },

  titleSmall: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.Small,
    fontStyle: "normal",
    fontWeight: fontWeight.bold,
    lineHeight: lineHeight.Compact,
  },

  contentLarge: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.Small,
    fontStyle: "normal",
    fontWeight: fontWeight.medium,
    lineHeight: lineHeight.Normal,
  },

  contentMedium: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.Tiny,
    fontStyle: "normal",
    fontWeight: fontWeight.regular,
    lineHeight: lineHeight.Compact,
  },

  subContent: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.Nano,
    fontStyle: "normal",
    fontWeight: fontWeight.regular,
    lineHeight: lineHeight.ExtraCompact,
  },
};

export type ColorsTypes = typeof colors;
export type FontTypes = typeof fonts;

const theme: DefaultTheme = {
  colors,
  fonts,
};

export default theme;
