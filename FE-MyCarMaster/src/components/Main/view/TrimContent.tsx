import styled from "styled-components";
import { useTrimState } from "../../../contexts/TrimContext";

function TrimContent() {
  const { trimId, trimList } = useTrimState();

  return <TrimImage src={trimList[trimId].imgUrl} />;
}

const TrimImage = styled.img`
  width: 100%;
  height: 100%;
  object-fit: contain;
  object-position: center;
`;
export default TrimContent;
