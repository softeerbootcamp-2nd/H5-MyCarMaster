import styled from "styled-components";
import theme from "../../../styles/Theme";
import XMark from "../../../assets/icons/XMark.svg";

export const ModalOverlay = styled.div`
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background-color: rgba(0, 0, 0, 0.6);
  overflow: auto;
  z-index: 99;
`;

export const Container = styled.div`
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);

  width: 70rem;
  height: 45rem;
  background-color: ${theme.colors.WHITE};

  padding: 2rem 2.75rem;

  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
`;

export const CloseButton = styled.button`
  width: 2rem;
  height: 2rem;
  background-repeat: no-repeat;
  background-image: url(${XMark});
  background-position: center;
  align-self: flex-end;
`;

export const MapContainer = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 2rem;
`;

export const KakaoMap = styled.div`
  width: 35rem;
  height: 30rem;
`;

export const CarMasterContainer = styled.div`
  width: 30rem;
  height: 30rem;
`;

export const CarMasterHeader = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  gap: 1rem;
`;

export const Title = styled.p`
  ${(props) => props.theme.fonts.Regular12};
`;

export const AddressContainer = styled.div`
  height: 2rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

export const Address = styled.p`
  ${(props) => props.theme.fonts.Regular9};
`;

export const ChangeAddress = styled.button`
  color: ${theme.colors.NAVYBLUE5};
  ${(props) => props.theme.fonts.Regular9};
`;

export const CarMasters = styled.div`
  margin-top: 1rem;
  display: flex;
  flex-direction: column;
`;

export const CarMastersDescription = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  color: ${theme.colors.NAVYBLUE5};
  ${(props) => props.theme.fonts.Regular10};
`;

export const Description = styled.p``;

export const ShowAllCarMasterBtn = styled.button`
  height: 2rem;

  color: ${theme.colors.NAVYBLUE5};
  ${(props) => props.theme.fonts.Regular9};
`;

export const CarMasterList = styled.div`
  width: 100%;
  height: 22rem;
  margin-top: 1rem;

  display: flex;
  flex-direction: column;
  gap: 1rem;
  overflow-y: auto;
`;
