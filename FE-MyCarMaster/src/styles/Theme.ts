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
  display: "2.25",
  headline: "1.75",
  title: "1.375",
  body: "1",
//   headline: {
//     large: "2",
//     medium: "1.75",
//     small: "1.5",
//   },
//   title: {
//     large1: "1.375",
//     large2: "1.25",
//     medium1: "1.375",
//     medium2: "1",
//     small: "0.875",
//   },
//   body: {
//     body1: "1",
//     body2: "0.875",
//     body3: "0.75",
//   },

};

export type ColorsTypes = typeof colors;
export type FontSizeTypes = typeof fontSize;

const theme: DefaultTheme = {
  colors,
  fontSize,
};

export default theme;
