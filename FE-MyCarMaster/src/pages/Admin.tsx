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
    text: "Check !",
    status: "success",
  },
  ERROR: {
    text: "Error ... ",
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
};

export default function Admin() {
  const [isOpen, setIsOpen] = useState(true);
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");
  const [statusId, setStatusId] = useState(STATUS_TEXT.NONE);
  const [statusPassword, setStatusPassword] = useState(STATUS_TEXT.NONE);
  const navigate = useNavigate();

  const isGoHomeHandler = () => {
    navigate("/");
  };

  const LoginHandler = () => {
    setIsOpen(false);
  };

  const ChangeTextHandler = (e: React.FocusEvent<HTMLInputElement>) => {
    if (e.target.placeholder === "ID") {
      setId(e.target.value);

      if (e.target.value === "admin") {
        setStatusId(STATUS_TEXT.SUCCESS);
      } else {
        // setStatusId(STATUS_TEXT.ERROR);
      }
    } else {
      setPassword(e.target.value);

      if (e.target.value === "1234") {
        setStatusPassword(STATUS_TEXT.SUCCESS);
      } else {
        // setStatusPassword(STATUS_TEXT.ERROR);
      }
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
              {id && <AdminContentText>ID</AdminContentText>}
              <Flex $flexDirection="column" $gap="0.5rem" $position="relative">
                <Input
                  placeholder="ID"
                  onChange={ChangeTextHandler}
                  value={id}
                />
                <CheckText $status={statusId.status}>{statusId.text}</CheckText>
              </Flex>

              {password && <AdminContentText>Password</AdminContentText>}
              <Flex $flexDirection="column" $gap="0.5rem" $position="relative">
                <Input
                  placeholder="Password"
                  onChange={ChangeTextHandler}
                  value={password}
                  type="password"
                />
                <CheckText $status={statusPassword.status}>
                  {statusPassword.text}
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
