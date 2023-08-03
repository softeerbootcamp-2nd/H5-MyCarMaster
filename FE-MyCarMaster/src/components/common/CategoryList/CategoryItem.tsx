import styled from "styled-components";

type CategoryItemProp = {
  name?: string;
  active?: boolean;
};

function CategoryItem({ name, active }: CategoryItemProp) {
  return (
    <Container>
      <Text active={active}>{name}</Text>
    </Container>
  );
}

const Container = styled.li``;

const Text = styled.p<CategoryItemProp>`
  font-size: 1rem;
  color: ${(props) => (props.active ? "#1A3276" : "#C5C9D2")};
  border-bottom: ${(props) => props.active && "2px solid #1A3276"};
`;

export default CategoryItem;
