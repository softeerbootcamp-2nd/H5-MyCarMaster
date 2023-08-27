import { FlexBlur, LoaderSpinner, LoaderImage } from "./style";
import Loading from "@assets/icons/Loading.png";

export default function Loader() {
  return (
    <FlexBlur
      $justifyContent="center"
      $alignItems="center"
      $width="100%"
      $height="100%"
    >
      <LoaderSpinner>
        <LoaderImage src={Loading} alt="loading" />
      </LoaderSpinner>
    </FlexBlur>
  );
}
