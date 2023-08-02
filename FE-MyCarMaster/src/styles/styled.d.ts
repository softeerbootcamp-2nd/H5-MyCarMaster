import "styled-components";
import { ColorsTypes, FontTypes } from "./Theme";

declare module "styled-components" {
  export interface DefaultTheme {
    colors: ColorsTypes;
    fonts: FontTypes;
  }
}
