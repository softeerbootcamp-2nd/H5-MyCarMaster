import styled from "styled-components";

export const Container = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  min-height: 7.5rem;
  margin: 0 3%;
  gap: 60rem;
`;

export const Img = styled.img`
  width: 20%;
  min-width: 12rem;
  max-width: 16rem;
  z-index: 100;
  cursor: pointer;
`;

export const ModelSelector = styled.div`
  display: flex;
  align-items: center;
  gap: 0.5rem;
`;

export const ModelName = styled.div`
  ${(props) => props.theme.fonts.Medium12};
  color: ${({ theme }) => theme.colors.NAVYBLUE5};
`;

export const ModelButton = styled.img`
  cursor: pointer;
`;
