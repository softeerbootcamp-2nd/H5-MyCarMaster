import { Container, IntroduceBox, HomeText, Video, Point } from "./Home.style";
import { Button } from "@common/index";
import theme from "@styles/Theme";
import homeVideo from "@assets/video/homeVideo.mp4";
import white_logo from "@assets/images/white_logo.svg";
import { ModelSelectView, Header } from "@layout/index";
import { useState } from "react";

function Home() {
  const [isFold, setIsFold] = useState(false);

  const modelSelectHandler = () => {
    setIsFold(true);
  };

  return (
    <>
      <Video autoPlay muted loop>
        <source src={homeVideo} type="video/mp4" />
      </Video>
      <Container>
        <Header
          logo={white_logo}
          isHome={true}
          status={isFold ? "dark" : "default"}
        />
        <IntroduceBox>
          <HomeText $size={2.5} $font={theme.fonts.Bold25}>
            내게 맞는 견적부터 카마스터 연결까지
          </HomeText>
          <HomeText $size={1.5} $font={theme.fonts.Medium15}>
            마이 카마스터와 함께해요
          </HomeText>

          <Button
            $x={15}
            $y={2.75}
            $backgroundcolor="#FFFFFF"
            $textcolor="#222222"
            $bordercolor="#FFFFFF"
            text="마이 카마스터 시작하기"
            $font={theme.fonts.Medium12}
            handleClick={modelSelectHandler}
          />
        </IntroduceBox>
        <Point $start={isFold}>
          <ModelSelectView isFold={isFold} />
        </Point>
      </Container>
    </>
  );
}
export default Home;
