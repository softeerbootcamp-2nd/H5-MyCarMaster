import { styled } from "styled-components";
import theme from "@styles/Theme";
import ProgressBar from "./ProgressBar";
import {
  MAX_FUEL,
  MAX_POWER,
  MAX_TORQUE,
} from "@constants/Graph.constants";
import { useDetailState } from "@contexts/DetailContext";

export type ValueProps = {
  power?: number;
  torque?: number;
  fuelMin?: number;
  fuelMax?: number;
};

type GraphItemProps<T> = {
  $value: T;
  koName: string;
  enName: string;
};

function GraphItem<T extends ValueProps>({
  $value,
  koName,
  enName,
}: GraphItemProps<T>) {
  const { engineId } = useDetailState();

  const calculateProgress = () => {
    return $value.power !== undefined
      ? ($value.power / MAX_POWER) * 100
      : $value.torque !== undefined
      ? ($value.torque / MAX_TORQUE) * 100
      : $value.fuelMin !== undefined && $value.fuelMax !== undefined
      ? [($value.fuelMax / MAX_FUEL) * 100, ($value.fuelMin / MAX_FUEL) * 100]
      : 0;
  };

  return (
    <Container>
      <NameContainer>
        <KoreanName>{koName}</KoreanName>
        <Bar $value={$value}>|</Bar>
        <Unit>{enName}</Unit>
      </NameContainer>
      <ValueContainer $value={$value}>
        {$value.power && <p>{$value.power}</p>}
        {$value.torque && <p>{$value.torque}</p>}
        {$value.fuelMin && $value.fuelMax && (
          <p>
            {$value.fuelMin} ~ {$value.fuelMax}
          </p>
        )}
      </ValueContainer>
      <ProgressBar
        key={engineId}
        progress={calculateProgress()}
        $value={$value}
      />
    </Container>
  );
}

const Container = styled.div`
  width: 18.75rem;
  display: flex;
  flex-direction: column;
  gap: 0.25rem;
  border-bottom: 1px solid ${theme.colors.GREY2};
`;

const NameContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: flex-start;
  gap: 0.75rem;
`;

const KoreanName = styled.p`
  ${(props) => props.theme.fonts.Medium12}
`;

const Bar = styled.p<{ $value: ValueProps }>`
  color: ${(props) =>
    props.$value.power !== undefined
      ? theme.colors.NAVYBLUE5
      : props.$value.torque !== undefined
      ? theme.colors.GOLD5
      : props.$value.fuelMin !== undefined && props.$value.fuelMax !== undefined
      ? theme.colors.COOLGREY2
      : "black"}; // 기본 색상
`;

const Unit = styled.p`
  ${(props) => props.theme.fonts.Regular10}
`;

const ValueContainer = styled.div<{ $value: ValueProps }>`
  ${(props) => props.theme.fonts.Regular25}
  color: ${(props) =>
    props.$value.power !== undefined
      ? theme.colors.NAVYBLUE5
      : props.$value.torque !== undefined
      ? theme.colors.GOLD5
      : props.$value.fuelMin !== undefined && props.$value.fuelMax !== undefined
      ? theme.colors.COOLGREY2
      : "black"};
`;

export default GraphItem;
