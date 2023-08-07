import { styled } from "styled-components";
import CategoryList from "../../common/CategoryList/CategoryList";
import DetailModelWrapper from "./DetailModelView/DetailModelWrapper";

function DetailModelContent() {
  const navIndex = 1;
  return (
    <Container>
      <CategoryList />
      <DetailModelWrapper navIndex={navIndex} />
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  height: 100%;
`;

export default DetailModelContent;
