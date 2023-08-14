import { styled } from "styled-components";
import GraphItem from "./GraphItem";
import { useDetailState } from "../../../contexts/DetailContext";

function GraphList() {
  const { engineId, engineList } = useDetailState();
  return (
    engineList?.length && (
      <Container>
        <GraphItem
          koName="최고출력"
          enName="PS"
          $value={{ power: engineList[engineId - 1].power }}
        />
        <GraphItem
          koName="최대토크"
          enName="kgf-m"
          $value={{ torque: engineList[engineId - 1].toque }}
        />
        <GraphItem
          koName="복합연비"
          enName="km/L"
          $value={{
            fuelMin: engineList[engineId - 1].fuelMin,
            fuelMax: engineList[engineId - 1].fuelMax,
          }}
        />
      </Container>
    )
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  gap: 2.25rem;
`;

export default GraphList;
