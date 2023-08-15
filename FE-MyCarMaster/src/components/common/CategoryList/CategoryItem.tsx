import styled from "styled-components";

type CategoryItemProp = {
  name?: string;
  $active?: boolean;
  onClickHandler?: () => void;
};

function CategoryItem({ name, $active, onClickHandler }: CategoryItemProp) {
  return (
    <Container onClick={onClickHandler}>
      <Text $active={$active}>{name}</Text>
    </Container>
  );
}

const Container = styled.li`
  cursor: pointer;
`;

const Text = styled.p<CategoryItemProp>`
  font-size: 1.5rem;
  color: ${(props) => (props.$active ? "#1A3276" : "#C5C9D2")};
  border-bottom: ${(props) => props.$active && "2px solid #1A3276"};
`;

export default CategoryItem;
