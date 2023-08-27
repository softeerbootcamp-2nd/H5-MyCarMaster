import { Container, Message, SubMessage } from "./style";
import { useEffect, useCallback } from "react";
import useFadeAnimation from "@hooks/useFadeAnimation";

type SnackBarProps = {
  messages: string[];
  onClose?: () => void;
};

export default function SnackBar({ messages, onClose }: SnackBarProps) {
  const joinedMessages = messages
    .map((message, index) =>
      index === messages.length - 1 ? `'${message}'` : `'${message}'`
    )
    .join(", ");

  const [isVisible, setVisible, fadeProps] = useFadeAnimation();

  const onCloseHandler = useCallback(() => {
    setVisible(false);
    setTimeout(() => {
      onClose && onClose();
    }, 900);
  }, [onClose, setVisible]);

  useEffect(() => {
    setVisible(true);

    const timer = setTimeout(() => {
      onCloseHandler();
    }, 3000);

    return () => {
      clearTimeout(timer);
    };
  }, []);

  return (
    <>
      {isVisible && (
        <Container onClick={onCloseHandler} {...fadeProps} $show={isVisible}>
          <Message>
            {messages.length ? (
              <>
                선택하신 기능이 포함된 옵션인&nbsp;
                {messages.length >= 2 && <br />}
                {joinedMessages} (이)가 추가되었어요.
              </>
            ) : (
              "선택된 옵션에 포함된 트림으로 선택되었어요."
            )}
          </Message>
          <SubMessage>
            {messages.length
              ? "옵션 선택 페이지에서 추가로 선택할 수 있어요"
              : `옵션 선택 페이지에서 수정이 가능해요`}
          </SubMessage>
        </Container>
      )}
    </>
  );
}
