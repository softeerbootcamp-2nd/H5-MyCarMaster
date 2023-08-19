import { styled } from "styled-components";
import TextButton from "../Button/TextButton";
import theme from "../../../styles/Theme";
import { useNavigate } from "react-router-dom";
import { useQuotationDispatch } from "../../../contexts/QuotationContext";

interface QuotationItemProps {
  name: string;
  price: number;
  category: string;
  imgUrl?: string;
  id: number;
  confirm: boolean;
}

function QuotationItem({
  id,
  name,
  price,
  category,
  imgUrl,
  confirm,
}: QuotationItemProps) {
  const navigate = useNavigate();
  const quotationDispatch = useQuotationDispatch();

  const handleClick = (navigationId: number) => {
    quotationDispatch({
      type: "NAVIGATE",
      payload: { navigationId },
    });
    navigate("/estimation");
  };
  return (
    <Container>
      <CategoryContainer>
        <Category>{category}</Category>
        {!confirm && (
          <TextButton
            size={"1"}
            text={"변경하기"}
            handleClick={() => handleClick(id)}
          />
        )}
      </CategoryContainer>
      <ItemContainer>
        {imgUrl ? (
          <ColorContainer>
            <ColorImg src={imgUrl} />
            <ColorName>{name}</ColorName>
          </ColorContainer>
        ) : (
          <ItemName>{name}</ItemName>
        )}
        <ItemPrice>+ {price.toLocaleString("ko-KR")}원</ItemPrice>
      </ItemContainer>
    </Container>
  );
}

export default QuotationItem;

const Container = styled.div`
  width: 59.5rem;
  height: 6.5rem;

  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  border-bottom: 1px solid ${theme.colors.GREY3};
`;

const CategoryContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: flex-start;
  height: 4.5rem;
`;

const Category = styled.p`
  font-family: "HyundaiSansMedium";
  font-size: 1.5rem;
  font-style: normal;
  font-weight: 500;
  line-height: 2rem; /* 133.333% */
`;

const ItemContainer = styled.div`
  width: 42.25rem;
  height: 100%;
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
`;

const ColorContainer = styled.div`
  width: 20rem;
  height: 5.625rem;

  display: flex;
  flex-direction: column;
  justify-content: flex-start;
`;

const ColorImg = styled.img`
  width: 100%;
  height: 3.625rem;
`;

const ColorName = styled.p`
  width: 100%;
  height: 2rem;
  display: flex;
  align-items: center;

  font-family: "HyundaiSansRegular";
  font-size: 1rem;
  font-style: normal;
  font-weight: 500;
  line-height: 1.5rem;

  border: 1px solid ${theme.colors.GREY2};
  border-top: none;
`;

const ItemName = styled.p`
  font-family: "HyundaiSansRegular";
  font-size: 1.375rem;
  font-style: normal;
  font-weight: 500;
  line-height: 1.75rem;
`;

const ItemPrice = styled.p`
  font-family: "HyundaiSansMedium";
  font-size: 1.375rem;
  font-style: normal;
  font-weight: 700;
  line-height: 1.75rem;
`;
