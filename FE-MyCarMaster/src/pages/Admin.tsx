import theme from "@styles/Theme";
import { useState, useEffect } from "react";
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
import { Button, Loader } from "@common/index";
import { useNavigate } from "react-router-dom";
import { AdminView } from "@layout/index";
import { get } from "@utils/fetch";

const STATUS_TEXT = {
  SUCCESS: {
    text: "Check!",
    status: "success",
  },
  ERROR: {
    text: "올바르지 않은 형식입니다.",
    status: "error",
  },
  INVALID: {
    text: "올바르지 않은 접근입니다.",
    status: "invalid",
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

type ClientType = {
  id: number;
  estimateUrl: string;
  client: {
    name: string;
    phone: string;
    email: string;
  };
};

export default function Admin() {
  const [isOpen, setIsOpen] = useState(true);
  const [email, setEmail] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [statusEmail, setStatusEmail] = useState(STATUS_TEXT.NONE);
  const [statusPhoneNumber, setStatusPhoneNumber] = useState(STATUS_TEXT.NONE);
  const [isLogin, setIsLogin] = useState(false);
  const [clientList, setClientList] = useState<ClientType[]>([]);
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const SERVER_URL = import.meta.env.VITE_APP_SERVER_URL;

  useEffect(() => {
    const input = document.querySelector("input");
    input?.focus();
  }, []);

  const isGoHomeHandler = () => {
    navigate("/");
  };

  const LoginHandler = () => {
    if (
      statusEmail.status === "success" &&
      statusPhoneNumber.status === "success"
    ) {
      setLoading(true);
      get(`${SERVER_URL}/consultings?email=${email}&phone=${phoneNumber}`).then(
        (res) => {
          setLoading(false);
          if (res.code === 2000) {
            setClientList(res.result.consultings);
            setIsOpen(false);
            setIsLogin(true);
          } else {
            setStatusEmail(STATUS_TEXT.INVALID);
            setStatusPhoneNumber(STATUS_TEXT.INVALID);
          }
        }
      );
    }
  };

  const ChangeTextHandler = (e: React.FocusEvent<HTMLInputElement>) => {
    if (e.target.placeholder === "EMAIL") {
      setEmail(e.target.value);

      const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
      const emailCheck = emailPattern.test(e.target.value);

      if (emailCheck) {
        setStatusEmail(STATUS_TEXT.SUCCESS);
      } else if (e.target.value === "" || e.target.value.length < 5) {
        setStatusEmail(STATUS_TEXT.NONE);
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

      const phoneNumberLength = formattedValue.replace(/-/g, "").length;
      if (phoneNumberLength < 11) {
        setStatusPhoneNumber(STATUS_TEXT.CHECK);
      } else {
        setStatusPhoneNumber(STATUS_TEXT.SUCCESS);
      }
    }
  };

  return (
    <Flex $flexDirection="column" $justifyContent="center" $alignItems="center">
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
                  onFocus={ChangeTextHandler}
                  $status={statusEmail.status}
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
                  onFocus={ChangeTextHandler}
                  $status={statusPhoneNumber.status}
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
                {loading && <Loader />}
              </Flex>
            </Flex>
          </AdminLogin>
        </ModalOverlay>
      )}
      {!isOpen && isLogin && (
        <>
          <Flex
            $width="100%"
            $justifyContent="center"
            $alignItems="center"
            $height="3rem"
            $gap="1rem"
          >
            <AdminLoginTitle $font={theme.fonts.Medium15}>
              {email.slice(0, 2)}** 카마스터 관리자 페이지
            </AdminLoginTitle>
          </Flex>
          <AdminView clientList={clientList} />
        </>
      )}
    </Flex>
  );
}
