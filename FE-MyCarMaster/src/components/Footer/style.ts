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
  ${(props) => props.theme.fonts.Medium15};
  line-height: 2.5rem;
`;

export const DescriptionText = styled.p`
  ${(props) => props.theme.fonts.Regular9};
  line-height: 1.5rem;
`;
