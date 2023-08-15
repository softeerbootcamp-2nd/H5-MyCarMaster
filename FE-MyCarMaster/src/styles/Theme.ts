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
  ACTIVE_BLUE: "#007FA8",
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

const fonts = {
  BodyMedium: {
    fontFamily: "HyundaiSansMedium",
    fontSize: fontSize.ExtraSmall, // 0.75
    fontStyle: "normal",
    fontWeight: fontWeight.medium, // 500
  },

  BodySmall: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.Tiny, // 0.625
    fontStyle: "normal",
    fontWeight: fontWeight.regular, // 400
  },

  ContentLarge: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.Large, // 1
    fontStyle: "normal",
    fontWeight: fontWeight.regular, // 400
  },

  ContentMedium1: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.Normal, // 0.875
    fontStyle: "normal",
    fontWeight: fontWeight.regular,
  },

  ContentMedium2: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.Small, // 0.8125
    fontStyle: "normal",
    fontWeight: fontWeight.regular,
  },

  ContentSmall: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.ExtraSmall, // 0.75
    fontStyle: "normal",
    fontWeight: fontWeight.medium,
  },

  ContentSmall2: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.Tiny,
    fontStyle: "normal",
    fontWeight: fontWeight.medium,
  },

  display: {
    fontFamily: "HyundaiSansBold",
    fontSize: fontSize.Enormous,
    fontStyle: "normal",
    fontWeight: fontWeight.medium,
  },

  titleLarge: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.Huge,
    fontStyle: "normal",
    fontWeight: fontWeight.bold,
  },

  titleSmall: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.Large,
    fontStyle: "normal",
    fontWeight: fontWeight.bold,
  },

  contentLarge: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.Large,
    fontStyle: "normal",
    fontWeight: fontWeight.medium,
  },

  contentMedium: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.Small,
    fontStyle: "normal",
    fontWeight: fontWeight.regular,
  },
};

export type ColorsTypes = typeof colors;
export type FontTypes = typeof fonts;

const theme: DefaultTheme = {
  colors,
  fonts,
};

export default theme;
