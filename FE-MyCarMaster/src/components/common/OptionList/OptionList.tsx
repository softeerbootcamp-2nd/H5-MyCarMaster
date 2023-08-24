import {
  Container,
  ListContainer,
  NameText,
  Icon,
  OptionItem,
  Line,
} from "./style";
import { useState, useRef, useEffect } from "react";

type OptionListProps = {
  $data:
    | {
        id: number;
        category: string;
        name: string;
        imgUrl: string;
        description: string;
      }[]
    | undefined;
  $name: string;
  onClick?: (e: React.MouseEvent) => void;
};

export default function OptionList({ $name, $data }: OptionListProps) {
  const [isOpen, setIsOpen] = useState(false);
  const isFirst = useRef(true);

  useEffect(() => {
    if (isFirst.current) {
      isFirst.current = false;
      return;
    }
  }, [isOpen]);

  if (!$data) {
    return <></>;
  }

  return (
    <Container $isOpen={isOpen}>
      <ListContainer $isOpen={isOpen}>
        <NameText>{$name}</NameText>
        <Icon $isOpen={isOpen} onClick={() => setIsOpen(!isOpen)} />
      </ListContainer>
      <Line $isOpen={isOpen} $isFirst={isFirst.current} />
      {isOpen && (
        <ul>
          {$data.map((item) => (
            <OptionItem $isOpen={isOpen} key={item.id}>
              {item.name}
            </OptionItem>
          ))}
        </ul>
      )}
    </Container>
  );
}
