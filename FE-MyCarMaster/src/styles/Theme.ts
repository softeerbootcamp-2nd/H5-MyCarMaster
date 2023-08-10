import { DefaultTheme } from "styled-components";

const colors = {
  BLACK: "#222222",
  WHITE: "#FFFFFF",
  COOLGREY1: "#C5C9D2",
  COOLGREY2: "#81899E",
  GREY1: "#F6F6F6",
  GREY2: "#DDDDDD",
  GREY3: "#7B7B7B",
  GOLD4: "#FAF2ED",
  GOLD5: "#9B6D54",
  NAVYBLUE1: "#E7ECF9",
  NAVYBLUE2: "#D2D9EC",
  NAVYBLUE3: "#C6D2F0",
  NAVYBLUE4: "#96A9DC",
  NAVYBLUE5: "#1A3276",
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
  BodyMedium: {
    fontFamily: "HyundaiSansMedium",
    fontSize: fontSize.Microscopic, // 0.75
    fontStyle: "normal",
    fontWeight: fontWeight.medium, // 500
    lineHeight: lineHeight.ExtraCompact, // 1
  },

  BodySmall: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.Nano, // 0.625
    fontStyle: "normal",
    fontWeight: fontWeight.regular, // 400
    lineHeight: lineHeight.ExtraCompact, // 1
  },

  ContentLarge: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.Small, // 1
    fontStyle: "normal",
    fontWeight: fontWeight.regular, // 400
    lineHeight: lineHeight.Normal, // 1.25
  },

  //between Large and Medium
  ContentMedium1: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.ExtraSmall, // 0.875
    fontStyle: "normal",
    fontWeight: fontWeight.regular,
    lineHeight: lineHeight.Compact, // 1
  },

  ContentMedium2: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.Tiny, // 0.8125
    fontStyle: "normal",
    fontWeight: fontWeight.regular,
    lineHeight: lineHeight.Compact, // 1
  },

  ContentSmall: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.Microscopic, // 0.75
    fontStyle: "normal",
    fontWeight: fontWeight.medium,
    lineHeight: lineHeight.Compact, // 1
  },

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
};

export type ColorsTypes = typeof colors;
export type FontTypes = typeof fonts;

const theme: DefaultTheme = {
  colors,
  fonts,
};

export default theme;
