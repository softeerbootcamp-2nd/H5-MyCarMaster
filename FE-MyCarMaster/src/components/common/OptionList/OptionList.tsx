import {
  Container,
  ListContainer,
  Text,
  Icon,
  OptionItem,
  Line,
} from "./style";
import { useState, useRef, useEffect } from "react";

// 임시 데이터
const data = [
  { id: 1, name: "엔진오일" },
  { id: 2, name: "브레이크오일" },
  { id: 3, name: "에어컨필터" },
];

type OptionListProps = {
  $name: string;
};

export default function OptionList({ $name }: OptionListProps) {
  const [isOpen, setIsOpen] = useState(false);

  // initalil isFirst
  const isFirst = useRef(true);

  useEffect(() => {
    if (isFirst.current) {
      isFirst.current = false;
      return;
    }
  }, [isOpen]);

  return (
    <Container $isOpen={isOpen}>
      <ListContainer $isOpen={isOpen}>
        <Text $size={1.25}>{$name}</Text>
        <Icon $isOpen={true} onClick={() => setIsOpen(!isOpen)} />
      </ListContainer>
      <Line $isOpen={isOpen} $isFirst={isFirst.current} />
      {isOpen && (
        <ul>
          {data.map((item) => (
            <OptionItem $isOpen={isOpen} key={item.id}>
              {item.name}
            </OptionItem>
          ))}
        </ul>
      )}
    </Container>
  );
}
