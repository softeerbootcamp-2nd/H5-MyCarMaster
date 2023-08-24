type FontFaceType = {
  family: string;
  url: string;
  options?: FontFaceDescriptors;
};

export const fonts: FontFaceType[] = [
  {
    family: "HyundaiSansBold",
    url: "./fonts/HyundaiSansHeadKRBold.woff2",
  },
  {
    family: "HyundaiSansMedium",
    url: "./fonts/HyundaiSansHeadKRMedium.woff2",
  },
  {
    family: "HyundaiSansRegular",
    url: "./fonts/HyundaiSansHeadKRRegular.woff2",
  },
];
