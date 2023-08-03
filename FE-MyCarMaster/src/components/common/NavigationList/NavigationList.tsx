import { styled } from "styled-components";
import NavigationItem from "./NavigationItem";
import EstimatedPrice from "./EstimatedPrice";

function NavigationList() {
  return (
    <Container>
      <NavigationItem
        name={"트림"}
        $active={true}
        optionName={"Le Blanc (르블랑)"}
      />
      <NavigationItem
        name={"세부모델"}
        $active={false}
        optionName={"가솔린3.8 4WD 7인승"}
      />
      <NavigationItem
        name={"색상"}
        $active={false}
        optionName={"그라파이드 그레이 메탈릭"}
      />
      <NavigationItem name={"옵션"} $active={false} optionName={"빌트인 캠"} />
      <NavigationItem name={"견적서 완성"} $active={false} />
      <EstimatedPrice sumPrice={9999999} />
    </Container>
  );
}

const Container = styled.ul`
  display: flex;
  flex-direction: column;
  gap: 0.25rem;
`;

export default NavigationList;
