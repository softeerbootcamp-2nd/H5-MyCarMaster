import { useState, useEffect } from "react";

type FontFaceType = {
  family: string;
  url: string;
  options?: FontFaceDescriptors;
};

function useFonts(fonts: FontFaceType[]) {
  const [fontFaces] = useState(
    fonts.map((font) => {
      return new FontFace(font.family, `url(${font.url})`, font.options);
    })
  );

  async function loadFontFace(fontFace = new FontFace("", "")) {
    const loadedFont = await fontFace.load();
    document.fonts.add(loadedFont);
  }

  useEffect(() => {
    fontFaces.forEach((fontFace) => {
      loadFontFace(fontFace);
    });
  }, [fontFaces]);
}

export { useFonts };
