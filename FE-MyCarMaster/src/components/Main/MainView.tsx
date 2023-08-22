import MainWrapper from "./MainWrapper";
import { MainContent } from "./style";
import { NavigationList } from "@common/index";
import { Flex } from "@styles/core.style";

export default function MainView() {
  return (
    <Flex $gap={"10rem"} $justifyContent="center" >
      <MainContent>
        <MainWrapper />
      </MainContent>
      <NavigationList confirm={false} />
    </Flex>
  );
}
