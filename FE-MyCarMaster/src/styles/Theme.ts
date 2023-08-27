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
  SMOOTH_RED: "#FF0000",
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
  Overwhelming: "4rem",
};

const fontWeight = {
  regular: 100,
  bold: 600,
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
  Medium40: {
    fontFamily: "HyundaiSansMedium",
    fontSize: fontSize.Overwhelming,
    fontWeight: fontWeight.regular,
  },
  Medium25: {
    fontFamily: "HyundaiSansMedium",
    fontSize: fontSize.Colossal,
    fontWeight: fontWeight.regular,
  },
  Medium20: {
    fontFamily: "HyundaiSansMedium",
    fontSize: fontSize.Gigantic,
    fontWeight: fontWeight.regular,
  },

  Medium17: {
    fontFamily: "HyundaiSansMedium",
    fontSize: fontSize.Huge,
    fontWeight: fontWeight.regular,
  },
  Medium15: {
    fontFamily: "HyundaiSansMedium",
    fontSize: fontSize.ExtraLarge,
    fontWeight: fontWeight.regular,
  },
  Medium13: {
    fontFamily: "HyundaiSansMedium",
    fontSize: fontSize.Large,
    fontWeight: fontWeight.regular,
  },
  Medium12: {
    fontFamily: "HyundaiSansMedium",
    fontSize: fontSize.Normal,
    fontWeight: fontWeight.regular,
  },
  Medium12_15: {
    fontFamily: "HyundaiSansMedium",
    fontSize: fontSize.Normal,
    Active: {
      fontSize: fontSize.ExtraLarge,
    },
    fontWeight: fontWeight.regular,
  },
  Medium12_13: {
    fontFamily: "HyundaiSansMedium",
    fontSize: fontSize.Normal,
    Active: {
      fontSize: fontSize.Large,
    },
    fontWeight: fontWeight.regular,
  },
  Medium11: {
    fontFamily: "HyundaiSansMedium",
    fontSize: fontSize.Small2,
    fontWeight: fontWeight.regular,
  },
  Medium10: {
    fontFamily: "HyundaiSansMedium",
    fontSize: fontSize.Small,
    fontWeight: fontWeight.regular,
  },
  Medium8: {
    fontFamily: "HyundaiSansMedium",
    fontSize: fontSize.ExtraSmall,
    fontWeight: fontWeight.regular,
  },

  Regular25: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.Colossal,
    fontWeight: fontWeight.regular,
  },

  Regular22: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.Enormous,
    fontWeight: fontWeight.regular,
  },

  Regular17: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.Huge,
    fontWeight: fontWeight.regular,
  },

  Regular15: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.ExtraLarge,
    fontWeight: fontWeight.regular,
  },
  Regular13: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.Large,
    fontWeight: fontWeight.regular,
  },

  Regular12: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.Normal,
    fontWeight: fontWeight.regular,
  },

  Regular11: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.Small2,
    fontWeight: fontWeight.regular,
  },

  Regular10: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.Small,
    fontWeight: fontWeight.regular,
  },

  Regular9: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.ExtraSmall,
    fontWeight: fontWeight.regular,
  },
  Regular8: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.Tiny,
    fontWeight: fontWeight.regular,
  },
  Regular6: {
    fontFamily: "HyundaiSansRegular",
    fontSize: fontSize.Nano,
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
