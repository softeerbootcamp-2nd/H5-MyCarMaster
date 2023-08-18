import styled from "styled-components";
import ArrowBottom from "../../assets/icons/ArrowBottom.svg";
import white_logo from "../../assets/images/white_logo.svg";
import dark_logo from "../../assets/images/dark_logo.svg";
import { useModelState } from "../../contexts/ModelContext";
import { useNavigate } from "react-router-dom";
import { Fragment, useState } from "react";
import { Modals } from "../common/Modals/Modals";
import { ModalType } from "../../constants/Modal.constants";

type HeaderProps = {
  isHome: boolean;
  logo: string;
  status?: "white" | "dark" | "default";
};

function Header({ isHome, logo, status }: HeaderProps) {
  const navigate = useNavigate();

  const [isOpen, setIsOpen] = useState(false);

  const handleNavigate = () => {
    setIsOpen(false);
    setTimeout(() => {
      navigate("/");
      window.location.reload();
    }, 0);
  };

  const { modelName } = useModelState();
  return (
    <Fragment>
      <Container>
        <Img
          src={
            status && status === "white"
              ? white_logo
              : status === "dark"
              ? dark_logo
              : logo
          }
          onClick={() => setIsOpen(true)}
        />
        {!isHome && (
          <>
            <ModelSelector>
              <ModelName>{modelName}</ModelName>
              <ModelButton src={ArrowBottom} />
            </ModelSelector>
          </>
        )}
      </Container>
      {isOpen && (
        <Modals
          type={ModalType.CLOSE}
          onClick={handleNavigate}
          setIsOpen={setIsOpen}
        />
      )}
    </Fragment>
  );
}

const Container = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 25%;
  min-height: 7.5rem;
  margin: 0 3%;
  gap: 60rem;
`;

const Img = styled.img`
  width: 20%;
  min-width: 12rem;
  max-width: 16rem;
  z-index: 100;
  cursor: pointer;
`;

const ModelSelector = styled.div`
  display: flex;
  align-items: center;
  gap: 0.5rem;
`;

const ModelName = styled.div`
  font-size: 1.2rem;
  font-weight: 500;
  color: ${({ theme }) => theme.colors.NAVYBLUE5};
`;

const ModelButton = styled.img`
  cursor: pointer;
`;

export default Header;
