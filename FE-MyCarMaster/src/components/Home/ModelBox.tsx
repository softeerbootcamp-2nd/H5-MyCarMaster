import styled from "styled-components";

type ModelBoxProp = {
  name: string;
  id: number;
  price: number;
  imgUrl: string;
  active?: boolean;
  onClick?: (
    e: React.MouseEvent<HTMLDivElement, MouseEvent>,
    id: number
  ) => void;
};
export default function ModelBox({
  name,
  id,
  price,
  imgUrl,
  active,
  onClick,
}: ModelBoxProp) {
  return (
    <Container onClick={(e) => onClick && onClick(e, id)} $active={active}>
      <ModelContainer>
        <ModelImage src={imgUrl} />
        <ModelName>{name}</ModelName>
        <ModelPrice>{price.toLocaleString("ko-KR")}만원~</ModelPrice>
      </ModelContainer>
    </Container>
  );
}

const Container = styled.div<{ $active?: boolean }>`
  width: 23%;
  height: 50%;
  cursor: pointer;
  border: 1px solid
    ${(props) =>
      props.$active
        ? props.theme.colors.NAVYBLUE4
        : props.theme.colors.COOLGREY1};
  background-color: ${(props) =>
    props.$active ? props.theme.colors.NAVYBLUE1 : props.theme.colors.GREY1};

  &:hover {
    border: 2px solid ${(props) => props.theme.colors.NAVYBLUE4};
  }
`;

const ModelContainer = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const ModelImage = styled.img`
  width: 100%;
  height: 100%;
  object-fit: contain;
`;

const ModelName = styled.p`
  width: 100%;
  height: 100%;
  ${(props) => props.theme.fonts.ContentMedium1}
  text-align: center;
`;

const ModelPrice = styled.p`
  width: 100%;
  height: 100%;
  ${(props) => props.theme.fonts.ContentMedium2}
  text-align: center;
`;
