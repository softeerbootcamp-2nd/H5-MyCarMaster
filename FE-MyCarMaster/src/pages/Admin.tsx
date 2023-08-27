import theme from "@styles/Theme";
import { useState } from "react";
import {
  AdminLogin,
  AdminLoginTitle,
  AdminShortContent,
  Input,
  ModalOverlay,
  AdminContentText,
  CheckText,
} from "./Admin.style";
import { Flex } from "@styles/core.style";
import { Button } from "@common/index";
import { useNavigate } from "react-router-dom";

const STATUS_TEXT = {
  SUCCESS: {
    text: "Check!",
    status: "success",
  },
  ERROR: {
    text: "올바르지 않은 형식입니다.",
    status: "error",
  },
  DEFAULT: {
    text: "",
    status: "default",
  },
  NONE: {
    text: "",
    status: "none",
  },
  CHECK: {
    text: "숫자만 입력해주세요.",
    status: "check",
  },
};

export default function Admin() {
  const [isOpen, setIsOpen] = useState(true);
  const [email, setEmail] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [statusEmail, setStatusEmail] = useState(STATUS_TEXT.NONE);
  const [statusPhoneNumber, setStatusPhoneNumber] = useState(STATUS_TEXT.NONE);
  const navigate = useNavigate();

  const isGoHomeHandler = () => {
    navigate("/");
  };

  const LoginHandler = () => {
    if (
      statusEmail.status === "success" &&
      statusPhoneNumber.status === "success"
    ) {
      console.log(email, phoneNumber);
      // 여기서 로그인 처리
      setIsOpen(false);
    }
  };

  const ChangeTextHandler = (e: React.FocusEvent<HTMLInputElement>) => {
    if (e.target.placeholder === "EMAIL") {
      setEmail(e.target.value);

      const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
      const emailCheck = emailPattern.test(e.target.value);

      if (emailCheck) {
        setStatusEmail(STATUS_TEXT.SUCCESS);
      } else {
        setStatusEmail(STATUS_TEXT.ERROR);
      }
    } else {
      const cleanedValue = e.target.value.replace(/\D/g, "");
      const formattedValue = cleanedValue.replace(
        /(\d{3})(\d{4})(\d{4})/,
        "$1-$2-$3"
      );
      setPhoneNumber(formattedValue);

      if (phoneNumber.length <= 9) {
        setStatusPhoneNumber(STATUS_TEXT.CHECK);
      } else {
        setStatusPhoneNumber(STATUS_TEXT.SUCCESS);
      }
    }
  };

  const FocusTextHandler = () => {
    if (phoneNumber.length < 1) {
      setPhoneNumber("010");
    }
  };

  return (
    <Flex>
      {isOpen && (
        <ModalOverlay>
          <AdminLogin>
            <AdminLoginTitle>관리자 접속</AdminLoginTitle>
            <AdminShortContent>
              이 페이지는 카마스터 관리자 페이지입니다. 허용된 관리자만 접속할
              수 있습니다.
            </AdminShortContent>
            <Flex $flexDirection="column" $gap="0.5rem">
              {email && <AdminContentText>EMAIL</AdminContentText>}
              <Flex $flexDirection="column" $gap="0.5rem" $position="relative">
                <Input
                  placeholder="EMAIL"
                  onChange={ChangeTextHandler}
                  value={email}
                />
                <CheckText $status={statusEmail.status}>
                  {statusEmail.text}
                </CheckText>
              </Flex>

              {phoneNumber && <AdminContentText>PhoneNumber</AdminContentText>}
              <Flex $flexDirection="column" $gap="0.5rem" $position="relative">
                <Input
                  placeholder="PhoneNumber"
                  onChange={ChangeTextHandler}
                  onFocus={FocusTextHandler}
                  value={phoneNumber}
                />
                <CheckText $status={statusPhoneNumber.status}>
                  {statusPhoneNumber.text}
                </CheckText>
              </Flex>
              <Flex
                $padding="1rem"
                $alignItems="center"
                $justifyContent="center"
                $gap="1rem"
              >
                <Button
                  $x={10}
                  $y={3}
                  $textcolor={theme.colors.BLACK}
                  $backgroundcolor={theme.colors.WHITE}
                  $bordercolor={theme.colors.BLACK}
                  text={"돌아가기"}
                  $font={theme.fonts.Regular12}
                  handleClick={() => isGoHomeHandler()}
                />
                <Button
                  $x={10}
                  $y={3}
                  $textcolor={theme.colors.BLACK}
                  $backgroundcolor={theme.colors.WHITE}
                  $bordercolor={theme.colors.BLACK}
                  text={"로그인"}
                  $font={theme.fonts.Regular12}
                  handleClick={() => LoginHandler()}
                />
              </Flex>
            </Flex>
          </AdminLogin>
        </ModalOverlay>
      )}
      {!isOpen && (
        <Flex $flexDirection="column" $gap="1rem">
          Data ...
        </Flex>
      )}
    </Flex>
  );
}
