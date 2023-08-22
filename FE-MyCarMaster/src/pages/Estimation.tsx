import { Header, MainView, Footer } from "@layout/index";
import { Flex } from "@styles/core.style";
import dark_logo from "@assets/images/dark_logo.svg";

export default function Estimation() {
  return (
    <Flex $flexDirection="column">
      <Header logo={dark_logo} isHome={false} />
      <MainView />
      <Footer />
    </Flex>
  );
}
