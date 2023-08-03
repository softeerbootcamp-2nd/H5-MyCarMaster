import styled from "styled-components";

// dummy data
import exclusive from "../../../assets/images/exclusive.png";

function TrimContent() {
  return (
    <>
      <TrimImage src={exclusive} />
    </>
  );
}

const TrimImage = styled.img`
  width: 100%;
  height: 100%;
  object-fit: contain;
  object-position: center;
`;
export default TrimContent;
