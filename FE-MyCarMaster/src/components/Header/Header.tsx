import { Fragment, useState } from "react";
import { useNavigate } from "react-router-dom";
import ArrowBottom from "@assets/icons/ArrowBottom.svg";
import white_logo from "@assets/images/white_logo.svg";
import dark_logo from "@assets/images/dark_logo.svg";
import { useModelState } from "@contexts/ModelContext";
import { Modals } from "@common/index";
import { ModalType } from "@constants/Modal.constants";
import { Container, Img, ModelSelector, ModelName, ModelButton } from "./style";

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

export default Header;
