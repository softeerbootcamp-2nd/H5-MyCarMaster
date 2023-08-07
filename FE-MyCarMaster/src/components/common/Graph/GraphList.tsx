import React from "react";
import { styled } from "styled-components";
import GraphItem from "./GraphItem";

function GraphList() {
  return (
    <Container>
      <GraphItem koName="최고출력" enName="PS" $value={{ power: 295 }} />
      <GraphItem koName="최대토크" enName="kgf-m" $value={{ torque: 36.2 }} />
      <GraphItem
        koName="복합연비"
        enName="km/L"
        $value={{ fuelMin: 8.0, fuelMax: 9.2 }}
      />
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  gap: 2.25rem;
`;

export default GraphList;
