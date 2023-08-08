import styled from "styled-components";
import { useTrimState } from "../../../contexts/TrimContext";
import OptionBox from "../../../components/common/OptionBox/OptionBox";

export default function TrimSelect() {
  const { trimList } = useTrimState();
  return (
    <Container>
      {trimList.map((trim) => {
        return (
          <OptionBox
            $id={trim.id}
            $name={trim.name}
            $description={trim.description}
            $imgUrl={trim.imgUrl}
            $ratio={trim.ratio}
            $price={trim.price}
          />
        );
      })}
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: row;
  width: 51.5rem;
  gap: 0.5rem;
`;
