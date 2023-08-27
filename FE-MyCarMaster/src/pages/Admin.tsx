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
import { Button } from "@common/index";
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

const tempClientList = [
  {
    id: 1,
    estimateUrl:
      "https://beta.my-car-master.shop/estimates/652baec3-eba2-486b-953f-ca4869f2b6d4",
    client: {
      name: "김민수",
      phone: "010-1234-5678",
      email: "abc@naver.com",
    },
  },
  {
    id: 2,
    estimateUrl:
      "https://beta.my-car-master.shop/estimates/652baec3-eba2-486b-953f-ca4869f2b6d4",
    client: {
      name: "고구려",
      phone: "010-1234-9999",
      email: "abdd@gamil.com",
    },
  },
  {
    id: 3,
    estimateUrl:
      "https://beta.my-car-master.shop/estimates/652baec3-eba2-486b-953f-ca4869f2b6d4",
    client: {
      name: "박민수",
      phone: "010-1234-9999",
      email: "abdd@gamil.com",
    },
  },
  {
    id: 4,
    estimateUrl:
      "https://beta.my-car-master.shop/estimates/652baec3-eba2-486b-953f-ca4869f2b6d4",
    client: {
      name: "정민수",
      phone: "010-1234-9999",
      email: "abdd@gamil.com",
    },
  },
  {
    id: 5,
    estimateUrl:
      "https://beta.my-car-master.shop/estimates/652baec3-eba2-486b-953f-ca4869f2b6d4",
    client: {
      name: "유민수",
      phone: "010-1234-9999",
      email: "abdd@gamil.com",
    },
  },
  {
    id: 6,
    estimateUrl:
      "https://beta.my-car-master.shop/estimates/652baec3-eba2-486b-953f-ca4869f2b6d4",
    client: {
      name: "양민수",
      phone: "010-1234-9999",
      email: "abdd@gamil.com",
    },
  },
];

export default function Admin() {
  const [isOpen, setIsOpen] = useState(true);
  const [email, setEmail] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [statusEmail, setStatusEmail] = useState(STATUS_TEXT.NONE);
  const [statusPhoneNumber, setStatusPhoneNumber] = useState(STATUS_TEXT.NONE);
  const [isLogin, setIsLogin] = useState(false);
  const navigate = useNavigate();

  const SERVER_URL = import.meta.env.VITE_APP_SERVER_URL;

  // focus input
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
      get(`${SERVER_URL}/consultings?email=${email}&phone=${phoneNumber}`)
        .then((res) => {
          console.log(res);
          if (res.status === 200) {
            setIsOpen(false);
            setIsLogin(true);
            console.log("관리자임");
          } else {
            console.log("관리자가 아님");
          }
        })
        .catch(() => {
          console.log("관리자가 아님");
        });
    }
    setIsOpen(false);
    setIsLogin(true);
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
      {!isOpen && isLogin && <AdminView clientList={tempClientList} />}
    </Flex>
  );
}
