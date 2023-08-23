import styled from "styled-components";

export const Container = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: center;
  position: relative;
  width: 100%;
  gap: 10rem;
  height: 30rem;
  padding: 2rem 0rem;
  background-color: ${({ theme }) => theme.colors.GREY1};
`;

export const HeadText = styled.p`
  font-size: 1.5rem;
  font-style: normal;
  font-weight: 700;
  line-height: 1.5rem;
`;

export const DescriptionText = styled.p`
  font-size: 0.9rem;
  font-style: normal;
  font-weight: 400;
  line-height: 165%;
`;
