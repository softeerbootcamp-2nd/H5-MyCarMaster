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
  Small2: "1.125rem",
  Normal: "1.25rem",
  Large: "1.375rem",
  ExtraLarge: "1.5rem",
  Huge: "1.75rem",
  Gigantic: "2rem",
  Enormous: "2.25rem",
  Colossal: "2.5rem",
};

const fontWeight = {
  regular: 400,
  medium: 500,
  bold: 700,
};

export type FontType = {
  fontFamily: string;
  fontSize: string;
  fontWeight: number;
  Active?: {
    fontSize: string;
  };
};

const fonts = {
  Bold25: {
    fontFamily: "HyundaiSansBold",
    fontSize: fontSize.Colossal,
    fontWeight: fontWeight.medium,
  },

  Bold20: {
    fontFamily: "HyundaiSansBold",
    fontSize: fontSize.Gigantic,
    fontWeight: fontWeight.medium,
    lineHeight: 2.0,
  },

  Medium17: {
    fontFamily: "HyundaiSansMedium",
    fontSize: fontSize.Huge,
    fontWeight: fontWeight.medium,
  },
  Medium15: {
    fontFamily: "HyundaiSansMedium",
    fontSize: fontSize.ExtraLarge,
    fontWeight: fontWeight.medium,
  },
  Medium13: {
    fontFamily: "HyundaiSansMedium",
    fontSize: fontSize.Large,
    fontWeight: fontWeight.medium,
  },
  Medium12: {
    fontFamily: "HyundaiSansMedium",
    fontSize: fontSize.Normal,
    fontWeight: fontWeight.medium,
  },
  Medium12_15: {
    fontFamily: "HyundaiSansMedium",
    fontSize: fontSize.Normal,
    Active: {
      fontSize: fontSize.ExtraLarge,
    },
    fontWeight: fontWeight.medium,
  },
  Medium12_13: {
    fontFamily: "HyundaiSansMedium",
    fontSize: fontSize.Normal,
    Active: {
      fontSize: fontSize.Large,
    },
    fontWeight: fontWeight.medium,
  },
  Medium11: {
    fontFamily: "HyundaiSansMedium",
    fontSize: fontSize.Small2,
    fontWeight: fontWeight.medium,
  },
  Medium10: {
    fontFamily: "HyundaiSansMedium",
    fontSize: fontSize.Small,
    fontWeight: fontWeight.medium,
  },
  Medium8: {
    fontFamily: "HyundaiSansMedium",
    fontSize: fontSize.ExtraSmall,
    fontWeight: fontWeight.medium,
  },

  Regular15: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.ExtraLarge,
    fontWeight: fontWeight.medium,
  },

  Regular12: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.Normal,
    fontWeight: fontWeight.medium,
  },

  Regular11: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.Small2,
    fontWeight: fontWeight.medium,
  },

  Regular10: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.Small,
    fontWeight: fontWeight.medium,
  },

  Regular9: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.ExtraSmall,
    fontWeight: fontWeight.medium,
  },
  Regular8: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.Tiny,
    fontWeight: fontWeight.medium,
  },
  Regular6: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.Nano,
    fontWeight: fontWeight.medium,
  },
};

export type ColorsTypes = typeof colors;
export type FontTypes = typeof fonts;

const theme: DefaultTheme = {
  colors,
  fonts,
};

export default theme;
