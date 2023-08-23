import { Header } from "@/components";
import { Button } from "@/components/common";
import theme from "@/styles/Theme";
import dark_logo from "@assets/images/dark_logo.svg";
import { useLocation, useNavigate } from "react-router-dom";
import { styled } from "styled-components";

function ConsultComplete() {
  const location = useLocation();
  const navigate = useNavigate();

  const handleClick = () => {
    setTimeout(() => {
      navigate("/");
      window.location.reload();
    }, 0);
  };

  return (
    <Container>
      <Header logo={dark_logo} isHome={true} />
      <CompleteContainer>
        <Text>구매 상담 신청이 완료되었습니다.</Text>
        <Email>{location.state.email}에서 작성하신 견적서를 확인하세요.</Email>
        <ButtonContainer>
          <Button
            $x={12}
            $y={2.25}
            $backgroundcolor={theme.colors.NAVYBLUE5}
            $bordercolor={theme.colors.NAVYBLUE5}
            $textcolor={theme.colors.WHITE}
            text="홈으로 돌아가기"
            handleClick={handleClick}
          />
        </ButtonContainer>
      </CompleteContainer>
    </Container>
  );
}

export default ConsultComplete;

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
`;

const CompleteContainer = styled.div`
  position: absolute;
  width: 50%;
  height: 50%;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  border: 1px solid ${theme.colors.NAVYBLUE5};
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const Text = styled.p`
  font-family: "HyundaiSansRegular";
  font-size: 2rem;
`;

const Email = styled.p`
  margin-top: 2rem;
  font-family: "HyundaiSansRegular";
  font-size: 1rem;
`;

const ButtonContainer = styled.div`
  margin-top: 3rem;
`;
