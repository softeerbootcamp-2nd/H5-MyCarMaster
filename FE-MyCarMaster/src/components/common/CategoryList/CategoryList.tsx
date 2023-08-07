import styled from "styled-components";
import CategoryItem from "./CategoryItem";

function CategoryList() {
  return (
    <Container>
      <CategoryItem name="전체" $active={true} />
      <CategoryItem name="엔진" $active={false} />
      <CategoryItem name="구동방식" $active={false} />
      <CategoryItem name="타입" $active={false} />
    </Container>
  );
}

const Container = styled.ul`
  display: inline-flex;
  align-items: flex-start;
  gap: 1.5rem;
`;

export default CategoryList;
