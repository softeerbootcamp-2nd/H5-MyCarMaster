import styled from "styled-components";

type ModelBoxProp = {
  name: string;
  id: number;
  price: number;
  imgUrl: string;
  active?: boolean;
  isNew?: boolean;
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
  isNew,
  onClick,
}: ModelBoxProp) {
  return (
    <Container onClick={(e) => onClick && onClick(e, id)} $active={active}>
      <ModelContainer>
        <ModelImage src={imgUrl} />
        <ModelName>{name}</ModelName>
        <ModelPrice>{price.toLocaleString("ko-KR")}원~</ModelPrice>
      </ModelContainer>
      {isNew && <NewIcon $isNew={isNew}>NEW</NewIcon>}
    </Container>
  );
}

const Container = styled.div<{ $active?: boolean }>`
  width: 23%;
  height: 12rem;
  cursor: pointer;
  position: relative;
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
  height: 8rem;
  object-fit: fill;
`;

const ModelName = styled.p`
  width: 100%;
  height: 100%;
  ${(props) => props.theme.fonts.Medium12}
  text-align: center;
`;

const ModelPrice = styled.p`
  width: 100%;
  height: 100%;
  ${(props) => props.theme.fonts.Regular10}
  color: ${(props) => props.theme.colors.GREY3};
  text-align: center;
`;

const NewIcon = styled.div<{ $isNew?: boolean }>`
  position: absolute;
  top: 0;
  left: 0;
  width: 3.5rem;
  height: 1.5rem;
  margin: 0.5rem;
  ${(props) => props.theme.fonts.Medium10}
`;
