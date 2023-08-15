import styled from "styled-components";
import MainWrapper from "./MainWrapper";
import NavigationList from "../common/NavigationList/NavigationList";

function MainView() {
  return (
    <Container>
      <MainContent>
        <MainWrapper />
      </MainContent>
      <NavigationList />
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: center;
  gap: 10rem;
  height: 100%;
  width: 100%;
`;

const MainContent = styled.div`
  width: 59.5rem;
`;

export default MainView;
