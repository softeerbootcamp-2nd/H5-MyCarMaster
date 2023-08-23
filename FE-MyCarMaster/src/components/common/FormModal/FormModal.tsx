import { styled } from "styled-components";
import theme from "../../../styles/Theme";
import Button from "../Button/Button";
import React, { useEffect, useState } from "react";
import { post } from "../../../utils/fetch";
import { useNavigate } from "react-router-dom";

interface ModalProps {
  estimateId: string;
  carMasterId: number;
  setFormModalOn: (isOpen: boolean) => void;
}

interface FormDatas {
  name: string;
  email: string;
  phone: string;
}

export function FormModal({
  estimateId,
  carMasterId,
  setFormModalOn,
}: ModalProps) {
  const SERVER_URL = import.meta.env.VITE_APP_SERVER_URL;
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [formDatas, setFormDatas] = useState<FormDatas>({
    phone: "",
    name: "",
    email: "",
  });
  const [errors, setErrors] = useState<FormDatas>({
    phone: "",
    name: "",
    email: "",
  });
  const navigate = useNavigate();

  useEffect(() => {
    setIsModalOpen(true);
  }, []);

  const closeModal = () => {
    setIsModalOpen(false);
    setFormModalOn(false);
  };

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement>,
    field: keyof FormDatas
  ) => {
    setFormDatas({
      ...formDatas,
      [field]: e.target.value,
    });
  };

  const validateForm = () => {
    const newError: FormDatas = {
      phone: "",
      name: "",
      email: "",
    };
    let isValid = true;

    if (formDatas.phone.trim() === "") {
      newError.phone = "휴대폰 번호를 입력해주세요.";
      isValid = false;
    }
    if (formDatas.name.trim() === "") {
      newError.name = "이름을 입력해주세요.";
      isValid = false;
    }
    if (formDatas.email.trim() === "") {
      newError.email = "이메일을 입력해주세요.";
      isValid = false;
    }
    setErrors(newError);
    return isValid;
  };

  const handleConfirm = (e: React.MouseEvent) => {
    e.preventDefault();

    if (validateForm()) {
      const submitData = {
        estimateId,
        carMasterId,
        client: formDatas,
      };
      post(`${SERVER_URL}/consultings`, submitData).then((res) => {
        console.log(res);
        if (res.code === 2000) {
          setIsModalOpen(false);
          setFormModalOn(false);
          navigate("/");
        }
      });
    }
  };

  return (
    <>
      {isModalOpen && (
        <ModalOverlay>
          <Container>
            <Text>입력란을 작성한 후 신청해주세요.</Text>
            <Form>
              <Input
                type="text"
                value={formDatas.name}
                placeholder="성함"
                onChange={(e) => handleChange(e, "name")}
              />
              {errors.name && <ErrorText>{errors.name}</ErrorText>}
              <Input
                type="tel"
                value={formDatas.phone}
                placeholder="휴대폰 번호"
                onChange={(e) => handleChange(e, "phone")}
              />
              {errors.phone && <ErrorText>{errors.phone}</ErrorText>}
              <Input
                type="email"
                value={formDatas.email}
                placeholder="이메일"
                onChange={(e) => handleChange(e, "email")}
              />
              {errors.email && <ErrorText>{errors.email}</ErrorText>}
            </Form>
            <ButtonContainer>
              <Button
                $x={9.625}
                $y={2.25}
                $backgroundcolor={`${theme.colors.WHITE}`}
                $textcolor={`${theme.colors.NAVYBLUE5}`}
                $bordercolor={`${theme.colors.NAVYBLUE5}`}
                text={"취소"}
                handleClick={closeModal}
              />
              <Button
                $x={9.625}
                $y={2.25}
                $backgroundcolor={`${theme.colors.NAVYBLUE5}`}
                $textcolor={`${theme.colors.WHITE}`}
                $bordercolor={`${theme.colors.NAVYBLUE5}`}
                text={"신청"}
                handleClick={(e) => handleConfirm(e as React.MouseEvent)}
              />
            </ButtonContainer>
          </Container>
        </ModalOverlay>
      )}
    </>
  );
}

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

  width: 45rem;
  height: 25rem;
  background-color: ${theme.colors.WHITE};

  padding: 2rem;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

const ButtonContainer = styled.div`
  display: flex;
  gap: 1rem;

  margin-top: 2rem;
`;

const Text = styled.p`
  width: 100%;
  text-align: center;
  font-family: "HyundaiSansRegular";
  font-size: 1.5rem;
`;

const Form = styled.form`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin-top: 1.5rem;
`;

const Input = styled.input`
  width: 20rem;
  height: 3rem;
  padding: 0 1rem;

  font-family: "HyundaiSansRegular";
  font-size: 1rem;
  margin: 0.5rem 0;
`;

const ErrorText = styled.div`
  color: red;
  font-size: 0.8rem;
`;
