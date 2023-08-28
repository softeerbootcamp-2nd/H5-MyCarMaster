import { css, styled } from "styled-components";
import theme from "../../../styles/Theme";

export const Container = styled.div<{ $isClicked: boolean }>`
  min-height: 6rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: ${theme.colors.GREY1};
  padding: 1rem 1.25rem;
  cursor: pointer;

  ${(props) =>
    props.$isClicked &&
    css`
      background-color: ${theme.colors.NAVYBLUE1};
      border: 1px solid ${theme.colors.NAVYBLUE4};
    `}
`;

export const CarMasterImage = styled.img`
  width: 3rem;
  height: 3rem;
  border-radius: 3rem;
`;

export const CarMasterInfoContainer = styled.div`
  width: 14.375rem;
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
`;

export const CarMasterInfo = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

export const CarMasterName = styled.p`
  ${(props) => props.theme.fonts.Regular10};
`;

export const CarMasterPhoneNum = styled.p`
  ${(props) => props.theme.fonts.Regular10};
`;

export const CarMasterIntro = styled.p`
  ${(props) => props.theme.fonts.Regular9};
`;

export const Agency = styled.p`
  width: 6rem;
  color: ${theme.colors.GREY3};
  ${(props) => props.theme.fonts.Regular8};
`;
