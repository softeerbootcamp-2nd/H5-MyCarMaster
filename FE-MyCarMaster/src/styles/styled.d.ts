import "styled-components";
import { ColorsTypes, FontSizeTypes } from "./Theme";

declare module "styled-components" {
  export interface DefaultTheme {
    colors: ColorsTypes;
    fontSize: FontSizeTypes;
  }
}
