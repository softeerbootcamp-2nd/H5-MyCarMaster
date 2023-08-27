import { Fragment, useEffect, useState } from "react";
import { styled } from "styled-components";
import useFetch from "@hooks/useFetch";
import { BasicOptionProps } from "types/options.types";
import theme from "@styles/Theme";
import XMark from "@assets/icons/XMark.svg";
import OptionList from "../OptionList/OptionList";
import { useTrimState } from "@/contexts/TrimContext";

interface BasicOptionModalProps {
  setIsBasicOptionModalOpen: (isOpen: boolean) => void;
  trimId: number;
  trimName: string;
  trimDescription: string;
}

interface FetchBasicOptionProps extends BasicOptionProps {
  result: {
    defaultOptions: BasicOptionProps[];
  };
}

function BasicOptionModal({
  setIsBasicOptionModalOpen,
  //   trimId,
  trimName,
  trimDescription,
}: BasicOptionModalProps) {
  // 아직 API에 데이터 안들어간 것 같아서 통신 부분 주석 처리
  const SERVER_URL = import.meta.env.VITE_APP_SERVER_URL;
  const { trimId } = useTrimState();

  const [isModalOpen, setIsModalOpen] = useState(false);
  const [showList, setShowList] = useState<BasicOptionProps[] | null>(null);

  const { data } = useFetch<FetchBasicOptionProps>(
    `${SERVER_URL}/trims/${trimId}/default-options`
  );

  useEffect(() => {
    if (data) {
      setShowList(data.result.defaultOptions);
    }
  }, [data]);

  useEffect(() => {
    setIsModalOpen(true);
  }, []);

  const closeBasicOptionModal = () => {
    setIsModalOpen(false);
    setIsBasicOptionModalOpen(false);
  };

  const filterList = (category: string) => {
    const filteredList = showList?.filter((item) => item.category === category);
    return filteredList;
  };

  const handleFocus = (e: React.MouseEvent) => {
    e.stopPropagation();
  };

  return (
    <Fragment>
      {isModalOpen && (
        <ModalOverlay>
          <Container className="modal-scroll">
            <TrimNameContainer>
              <TrimName>{trimName}</TrimName>
              <CloseButton onClick={closeBasicOptionModal}></CloseButton>
            </TrimNameContainer>
            <TrimDescription>{trimDescription}</TrimDescription>
            <BasicOptionContainer>
              {showList === undefined || showList === null ? (
                <p>데이터가 없습니다.</p>
              ) : (
                <>
                  <OptionList
                    $name="파워트레인/성능"
                    $data={filterList("파워트레인/성능")}
                    onClick={handleFocus}
                  />
                  <OptionList
                    $name="지능형 안전기술"
                    $data={filterList("지능형 안전기술")}
                    onClick={handleFocus}
                  />
                  <OptionList
                    $name="안전"
                    $data={filterList("안전")}
                    onClick={handleFocus}
                  />
                  <OptionList
                    $name="외관"
                    $data={filterList("외관")}
                    onClick={handleFocus}
                  />
                  <OptionList
                    $name="내장"
                    $data={filterList("내장")}
                    onClick={handleFocus}
                  />
                  <OptionList
                    $name="시트"
                    $data={filterList("시트")}
                    onClick={handleFocus}
                  />
                  <OptionList
                    $name="편의"
                    $data={filterList("편의")}
                    onClick={handleFocus}
                  />
                </>
              )}
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

  // first show
  animation: fadeIn 0.3s ease-in-out;
`;

const Container = styled.div`
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);

  width: 50rem;
  height: 45rem;
  background-color: ${theme.colors.WHITE};

  gap: 2rem;

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
  ${(props) => props.theme.fonts.Medium20};
`;

const CloseButton = styled.button`
  width: 2rem;
  height: 2rem;
  background-repeat: no-repeat;
  background-image: url(${XMark});
  background-position: center;
`;

const TrimDescription = styled.p`
  ${(props) => props.theme.fonts.Regular10};
  height: 2rem;
`;

const BasicOptionContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 0.75rem;
`;
