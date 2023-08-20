import React, { Fragment, useEffect, useState } from "react";
import { styled } from "styled-components";
import theme from "../../../styles/Theme";
import XMark from "../../../assets/icons/XMark.svg";
import Button from "../Button/Button";
import { DescriptionOptionModalProps } from "../../../types/options.types";
import { ButtonContainer } from "../OptionBox/style";

interface DescriptionModalProps {
  onClick?: () => void;
  setIsDescriptionModalOpen: (isDescriptionModalOpen: boolean) => void;
  option: DescriptionOptionModalProps;
  isTrimSelect: boolean;
}

function OptionDescriptionModal({
  onClick,
  setIsDescriptionModalOpen,
  option,
  isTrimSelect,
}: DescriptionModalProps) {
  const [isModalOpen, setIsModalOpen] = useState(false);

  useEffect(() => {
    setIsModalOpen(true);
  }, []);

  const closeDescriptionModal = (e: React.MouseEvent) => {
    setIsModalOpen(false);
    setIsDescriptionModalOpen(false);
    e.stopPropagation();
  };

  const selectHandler = (e: React.MouseEvent) => {
    e.stopPropagation();
    onClick!();
    setIsModalOpen(false);
    setIsDescriptionModalOpen(false);
  };

  return (
    <Fragment>
      {isModalOpen && (
        <ModalOverlay onClick={closeDescriptionModal}>
          <Container>
            <OptionNameContainer>
              <OptionName>{option.name}</OptionName>
              <CloseButton onClick={closeDescriptionModal}></CloseButton>
            </OptionNameContainer>
            {isTrimSelect && option.summary && (
              <OptionSummary>{option.summary}</OptionSummary>
            )}
            <OptionImage src={option.imgUrl} />
            <OptionDescription>{option.description}</OptionDescription>
            <Note>
              * 홈페이지의 사진과 설명은 참고용이며 실제 차량에 탑재되는 기능과
              설명은 상이할 수 있으니, 차량 구입 전 카마스터를 통해 확인
              바랍니다.
            </Note>
            {isTrimSelect && (
              <ButtonContainer>
                <Button
                  $x={9.625}
                  $y={2.25}
                  $backgroundcolor={theme.colors.NAVYBLUE5}
                  $bordercolor={theme.colors.NAVYBLUE5}
                  $textcolor={theme.colors.WHITE}
                  text="선택하기"
                  handleClick={(e) => selectHandler(e as React.MouseEvent)}
                />
              </ButtonContainer>
            )}
          </Container>
        </ModalOverlay>
      )}
    </Fragment>
  );
}

export default OptionDescriptionModal;

const ModalOverlay = styled.div`
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
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
  justify-content: space-between;
`;

const OptionNameContainer = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const OptionName = styled.p`
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

const OptionSummary = styled.p`
  font-family: "HyundaiSansRegular";
  font-size: 1rem;
  font-style: normal;
  font-weight: 400;

  height: 2rem;
  margin-top: 1rem;
`;

const OptionImage = styled.img`
  width: 100%;
`;

const OptionDescription = styled.p`
  font-family: "HyundaiSansRegular";
  font-size: 1rem;
  font-style: normal;
  font-weight: 400;
`;

const Note = styled.p`
  font-family: "HyundaiSansRegular";
  font-size: 0.75rem;
  font-style: normal;
  font-weight: 400;
`;
