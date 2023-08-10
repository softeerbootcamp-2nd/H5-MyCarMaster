import styled, { keyframes } from "styled-components";
import { ValueProps } from "./GraphItem";
import theme from "../../../styles/Theme";

type ProgressBarProps = {
  progress: number | number[];
  $value: ValueProps;
};

const ProgressBar = ({ progress, $value }: ProgressBarProps) => {
  return (
    <ProgressContainer>
      <ProgressValue
        style={{
          width: Array.isArray(progress) ? `${progress[0]}%` : `${progress}%`,
        }}
        $value={$value}
      />
      {Array.isArray(progress) && (
        <SecondaryProgressValue
          style={{
            width: `${progress[1]}%`,
          }}
          $value={$value}
        />
      )}
    </ProgressContainer>
  );
};

const progressAnimation = keyframes`
  from { width: 0; }
`;

const ProgressContainer = styled.div`
  height: 2px;
  background-color: #ddd;
  position: relative;
`;

const ProgressValue = styled.div<{ $value: ValueProps }>`
  height: 100%;
  background-color: ${(props) =>
    props.$value.power !== undefined
      ? theme.colors.NAVYBLUE5
      : props.$value.torque !== undefined
      ? theme.colors.GOLD5
      : theme.colors.COOLGREY1};
  width: 0;
  animation: ${progressAnimation} 0.5s linear forwards;
`;

const SecondaryProgressValue = styled.div<{ $value: ValueProps }>`
  height: 100%;
  background-color: ${(props) =>
    props.$value.power !== undefined
      ? theme.colors.NAVYBLUE5
      : props.$value.torque !== undefined
      ? theme.colors.GOLD5
      : theme.colors.COOLGREY2};
  width: 0;
  animation: ${progressAnimation} 0.5s linear forwards;
  position: absolute;
  top: 0;
`;

export default ProgressBar;
