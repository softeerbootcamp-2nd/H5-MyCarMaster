import styled from "styled-components";
import { FontType } from "@/styles/Theme";

type CategoryItemProp = {
  name?: string;
  $active?: boolean;
  $font?: FontType;
  onClickHandler?: () => void;
};

function CategoryItem({
  name,
  $active,
  $font,
  onClickHandler,
}: CategoryItemProp) {
  return (
    <Container onClick={onClickHandler}>
      <Text $active={$active} $font={$font}>
        {name}
      </Text>
    </Container>
  );
}

const Container = styled.li`
  cursor: pointer;
  
`;

const Text = styled.p<CategoryItemProp>`
  ${(props) => props.$font};
  font-size: ${(props) => props.$active && props.$font?.Active?.fontSize};
  color: ${(props) => (props.$active ? "#1A3276" : "#C5C9D2")};
  border-bottom: ${(props) => props.$active && "2px solid #1A3276"};
`;

export default CategoryItem;
