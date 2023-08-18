import {
  GridContainer,
  GridOptionItem,
  GridOptionImage,
  Text,
  Detail,
  GridOptionTextContainer,
} from "./style";

type ListType = {
  id: number;
  name: string;
  imgUrl: string;
  description: string;
};

type GridOptionListProps = {
  list: ListType[];
  handleClick?: (item: ListType) => void;
};

export default function GridOptionList({
  list,
  handleClick,
}: GridOptionListProps) {
  if (list.length === 0) {
    return <>데이터가 존재하지 않습니다 ..</>;
  }
  return (
    <GridContainer>
      {list.map((item) => (
        <GridOptionItem key={item.id}>
          <GridOptionImage src={item.imgUrl} />
          <GridOptionTextContainer>
            <Text $size={0.875}>{item.name}</Text>
            <Detail onClick={() => handleClick && handleClick(item)}>
              자세히 보기 &gt;
            </Detail>
          </GridOptionTextContainer>
        </GridOptionItem>
      ))}
    </GridContainer>
  );
}
