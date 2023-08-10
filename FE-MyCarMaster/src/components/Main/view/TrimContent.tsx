import styled from "styled-components";
import { Trims, useTrimState } from "../../../contexts/TrimContext";
import useFetch from "../../../hooks/useFetch";

function TrimContent() {
  const { trimId, trimList } = useTrimState();
  const SERVER_URL = import.meta.env.VITE_APP_SERVER_URL;

  const { data, loading, error } = useFetch<Trims[]>(`${SERVER_URL}/trims`, {
    method: "GET",
  });

  return <TrimImage src={trimList[trimId].imgUrl} />;
}

const TrimImage = styled.img`
  width: 100%;
  height: 100%;
  object-fit: contain;
  object-position: center;
`;

export default TrimContent;
