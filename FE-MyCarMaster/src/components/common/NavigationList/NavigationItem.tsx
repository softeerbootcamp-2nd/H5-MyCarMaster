import { styled } from "styled-components";
import theme from "../../../styles/Theme";
import CircleCheck from "../../../assets/icons/CircleCheck.svg";

type NavigationItemProp = {
  name?: string;
  $active: boolean;
  optionName?: string;
};

function NavigationItem({ name, $active, optionName }: NavigationItemProp) {
  return (
    <Container $active={$active}>
      <TopContainer>
        <Category>{name}</Category>
        <CheckCircle src={CircleCheck} />
      </TopContainer>
      <BottomContainer>{optionName}</BottomContainer>
    </Container>
  );
}

const Container = styled.li<NavigationItemProp>`
  width: 9.625rem;
  height: 3.3125rem;
  padding: 0.5rem 0.75rem;

  display: flex;
  flex-direction: column;
  justify-content: space-between;
  gap: 0.25rem;

  background-color: ${({ $active }) =>
    $active ? `${theme.colors.NavyBlue1}` : `${theme.colors.White}`};
  border: ${({ $active }) =>
    $active
      ? `1px solid ${theme.colors.NavyBlue4}`
      : `1px solid ${theme.colors.Grey2}`};
`;

const TopContainer = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const BottomContainer = styled.div`
  font-family: "Hyundai Sans Text KR";
  font-size: 0.5rem;
  font-style: normal;
  font-weight: 400;
  line-height: 165%; /* 0.825rem */
  letter-spacing: -0.015rem;
`;

const Category = styled.p`
  font-family: "Hyundai Sans Text KR";
  font-size: 0.75rem;
  font-style: normal;
  font-weight: 700;
  line-height: 165%; /* 1.2375rem */
  letter-spacing: -0.0225rem;
`;

const CheckCircle = styled.img``;

export default NavigationItem;
