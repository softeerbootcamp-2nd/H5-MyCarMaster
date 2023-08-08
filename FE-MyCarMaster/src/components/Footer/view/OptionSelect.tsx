import styled from "styled-components";
import { useOptionState } from "../../../contexts/OptionContext";
import { useQuotationState } from "../../../contexts/QuotationContext";
import OptionBox from "../../../components/common/OptionBox/OptionBox";

export default function OptionSelect() {
  const { optionList, selectedOption, consideredOption } = useOptionState();
  const { navigationId } = useQuotationState();
  return (
    <Container>
      {optionList.map((option) => {
        return (
          <OptionBox
            $id={option.id}
            $name={option.name}
            $description={option.description}
            $imgUrl={option.imgUrl}
            $ratio={option.ratio}
            $price={option.price}
            $selected={selectedOption.includes(option.id)}
            $considered={consideredOption.includes(option.id)}
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
