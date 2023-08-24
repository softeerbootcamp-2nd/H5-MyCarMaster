import theme from "@/styles/Theme";
import { styled } from "styled-components";

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
`;

export const CompleteContainer = styled.div`
  position: absolute;
  width: 50%;
  height: 50%;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  border: 1px solid ${theme.colors.NAVYBLUE5};
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

export const Text = styled.p`
  font-family: "HyundaiSansRegular";
  font-size: 2rem;
`;

export const SubText = styled.p`
  font-family: "HyundaiSansRegular";
  font-size: 1rem;
  margin-top: 1rem;
`;

export const ButtonContainer = styled.div`
  margin-top: 3rem;
`;
