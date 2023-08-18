import { Fragment, useEffect, useState } from "react";
import { styled } from "styled-components";
// import useFetch from "../../../hooks/useFetch";
// import { BasicOptionProps } from "../../../types/options.types";
import theme from "../../../styles/Theme";
import XMark from "../../../assets/icons/XMark.svg";
import OptionList from "../OptionList/OptionList";

interface BasicOptionModalProps {
  setIsBasicOptionModalOpen: (isOpen: boolean) => void;
  trimId: number;
  trimName: string;
  trimDescription: string;
}

// interface FetchBasicOptionProps extends BasicOptionProps {
//   result: {
//     defaultOptions: BasicOptionProps[];
//   };
// }

function BasicOptionModal({
  setIsBasicOptionModalOpen,
  //   trimId,
  trimName,
  trimDescription,
}: BasicOptionModalProps) {
  // 아직 API에 데이터 안들어간 것 같아서 통신 부분 주석 처리
  //   const SERVER_URL = import.meta.env.VITE_APP_SERVER_URL;

  const [isModalOpen, setIsModalOpen] = useState(false);

  //   const { data } = useFetch<FetchBasicOptionProps>(
  //     `${SERVER_URL}/trims/${trimId}/default-options`
  //   );

  //   useEffect(() => {
  //     if (data) {
  //       console.log(data);
  //     }
  //   }, [data]);

  useEffect(() => {
    setIsModalOpen(true);
  }, []);

  const closeBasicOptionModal = () => {
    setIsModalOpen(false);
    setIsBasicOptionModalOpen(false);
  };

  return (
    <Fragment>
      {isModalOpen && (
        <ModalOverlay>
          <Container>
            <TrimNameContainer>
              <TrimName>{trimName}</TrimName>
              <CloseButton onClick={closeBasicOptionModal}></CloseButton>
            </TrimNameContainer>
            <TrimDescription>{trimDescription}</TrimDescription>
            <BasicOptionContainer>
              <OptionList $name="파워트레인/성능" />
              <OptionList $name="지능형 안전 기술" />
              <OptionList $name="안전" />
              <OptionList $name="외관" />
              <OptionList $name="내장" />
              <OptionList $name="시트" />
              <OptionList $name="편의" />
            </BasicOptionContainer>
          </Container>
        </ModalOverlay>
      )}
    </Fragment>
  );
}

export default BasicOptionModal;

const ModalOverlay = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  background-color: rgba(0, 0, 0, 0.6);
  z-index: 999;
`;

const Container = styled.div`
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);

  width: 50rem;
  height: 45rem;
  background-color: ${theme.colors.WHITE};

  padding: 2rem 2.75rem;

  display: flex;
  flex-direction: column;

  overflow: scroll;
`;

const TrimNameContainer = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const TrimName = styled.p`
  font-family: "HyundaiSansMedium";
  font-size: 2rem;
  font-style: normal;
  font-weight: 500;
`;

const CloseButton = styled.button`
  width: 2rem;
  height: 2rem;
  background-repeat: no-repeat;
  background-image: url(${XMark});
  background-position: center;
`;

const TrimDescription = styled.p`
  font-family: "HyundaiSansRegular";
  font-size: 1rem;
  font-style: normal;
  font-weight: 400;

  height: 2rem;
  margin-top: 1rem;
`;

const BasicOptionContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 0.75rem;

  margin-top: 1.5rem;
`;
