import { styled } from "styled-components";
import CategoryList from "../../common/CategoryList/CategoryList";
import ColorWrapper from "./ColorView/ColorWrapper";

function ColorContent() {
  return (
    <Container>
      <CategoryList />
      <ColorWrapper />
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  height: 100%;
`;

export default ColorContent;
