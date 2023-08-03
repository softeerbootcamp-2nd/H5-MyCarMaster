import styled from "styled-components";
import MainWrapper from "./MainWrapper";
import NavigationList from "../common/NavigationList/NavigationList";

function MainView() {
  const navIndex = 0;
  return (
    <Container>
      <MainContent>
        <MainWrapper navIndex={navIndex} />
      </MainContent>
      <NavigationList />
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  height: 400px;
`;

const MainContent = styled.div`
  width: 51.5rem;
`;

export default MainView;
