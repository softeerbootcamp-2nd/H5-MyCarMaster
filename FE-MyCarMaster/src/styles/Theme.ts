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

const fontSize = {
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
const fonts = {
  display: {
    fontFamily: "HyundaiSansBold",
    fontSize: fontSize.Enormous,
    fontStyle: "normal",
    fontWeight: fontWeight.medium,
    lineHeight: lineHeight.ExtraExpanded,
    letterSpacing: "-0.0675rem",
  },
  headLarge: {
    fontFamily: "HyundaiSansBold",
    fontSize: fontSize.Gigantic,
    fontStyle: "normal",
    fontWeight: fontWeight.medium,
    lineHeight: lineHeight.Expanded,
    letterSpacing: "-0.06rem",
  },
  headMedium: {
    fontFamily: "HyundaiSansBold",
    fontSize: fontSize.Huge,
    fontStyle: "normal",
    fontWeight: fontWeight.medium,
    lineHeight: lineHeight.Spacious,
    letterSpacing: "-0.0525rem",
  },
  headSmall: {
    fontFamily: "HyundaiSansBold",
    fontSize: fontSize.ExtraLarge,
    fontStyle: "normal",
    fontWeight: fontWeight.medium,
    lineHeight: lineHeight.Tight,
    letterSpacing: "-0.045rem",
  },
  titleLarge1: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.Large,
    fontStyle: "normal",
    fontWeight: fontWeight.bold,
    lineHeight: lineHeight.Loose,
    letterSpacing: "-0.04125rem",
  },
  titleLarge2: {
    fontFamily: "HyundaiSansMedium",
    fontSize: fontSize.Normal,
    fontStyle: "normal",
    fontWeight: fontWeight.bold,
    lineHeight: lineHeight.Loose,
    letterSpacing: "-0.0375rem",
  },
  titleMedium1: {
    fontFamily: "HyundaiSansMedium",
    fontSize: fontSize.Large,
    fontStyle: "normal",
    fontWeight: fontWeight.medium,
    lineHeight: lineHeight.Tight,
    letterSpacing: "-0.04125rem",
  },
  titleMedium2: {
    fontFamily: "HyundaiSansMedium",
    fontSize: fontSize.Small,
    fontStyle: "normal",
    fontWeight: fontWeight.bold,
    lineHeight: lineHeight.Normal,
    letterSpacing: "-0.3rem",
  },
  titleSmall: {
    fontFamily: "HyundaiSansMedium",
    fontSize: fontSize.ExtraSmall,
    fontStyle: "normal",
    fontWeight: fontWeight.bold,
    lineHeight: lineHeight.Compact,
    letterSpacing: "-0.02625rem",
  },
  bodyLarge: {
    fontFamily: "HyundaiSansBold",
    fontSize: fontSize.Small,
    fontStyle: "normal",
    fontWeight: fontWeight.regular,
    lineHeight: lineHeight.Normal,
    letterSpacing: "-0.03rem",
  },
  bodyMedium1: {
    fontFamily: "HyundaiSansBold",
    fontSize: fontSize.Small,
    fontStyle: "normal",
    fontWeight: fontWeight.medium,
    lineHeight: lineHeight.Normal,
    letterSpacing: "-0.03rem",
  },
  bodyMedium2: {
    fontFamily: "HyundaiSansBold",
    fontSize: fontSize.Small,
    fontStyle: "normal",
    fontWeight: fontWeight.medium,
    lineHeight: lineHeight.Normal,
    letterSpacing: "-0.03rem",
  },
  bodyMedium3: {
    fontFamily: "HyundaiSansBold",
    fontSize: fontSize.ExtraSmall,
    fontStyle: "normal",
    fontWeight: fontWeight.regular,
    lineHeight: lineHeight.Compact,
    letterSpacing: "-0.02625rem",
  },
  bodySmall1: {
    fontFamily: "HyundaiSansBold",
    fontSize: fontSize.Microscopic,
    fontStyle: "normal",
    fontWeight: fontWeight.medium,
    lineHeight: lineHeight.ExtraCompact,
    letterSpacing: "-0.0225rem",
  },
  bodySmall2: {
    fontFamily: "HyundaiSansBold",
    fontSize: fontSize.Tiny,
    fontStyle: "normal",
    fontWeight: fontWeight.regular,
    lineHeight: lineHeight.Compact,
    letterSpacing: "-0.02438rem",
  },
};

export type ColorsTypes = typeof colors;
export type FontTypes = typeof fonts;

const theme: DefaultTheme = {
  colors,
  fonts,
};

export default theme;
