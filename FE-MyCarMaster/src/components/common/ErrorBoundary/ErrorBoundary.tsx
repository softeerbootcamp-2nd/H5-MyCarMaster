import theme from "@/styles/Theme";
import { Component, ErrorInfo, ReactNode } from "react";
import { Button } from "..";
import { Flex } from "@/styles/core.style";
import { ButtonContainer, CompleteContainer, SubText, Text } from "./style";

interface ErrorBoundaryProps {
  children: ReactNode;
  handleClick: () => void;
}

interface ErrorBoundaryState {
  hasError: boolean;
}

class ErrorBoundary extends Component<ErrorBoundaryProps, ErrorBoundaryState> {
  constructor(props: ErrorBoundaryProps) {
    super(props);
    this.state = { hasError: false };
  }

  static getDerivedStateFromError(error: Error): ErrorBoundaryState {
    // 다음 렌더링에서 폴백 UI가 보이도록 상태를 업데이트합니다.
    console.log(error);
    return { hasError: true };
  }

  componentDidCatch(error: Error, errorInfo: ErrorInfo): void {
    // 에러 리포팅 서비스에 에러를 기록할 수도 있습니다.
    console.log(error, errorInfo);
  }

  render(): ReactNode {
    if (this.state.hasError) {
      // 폴백 UI를 커스텀하여 렌더링할 수 있습니다.
      return (
        <Flex $flexDirection={"column"} $width={"100%"} $height={"100%"}>
          <CompleteContainer>
            <Text>오류가 발생하였습니다.</Text>
            <SubText>다시 시도해주세요.</SubText>
            <ButtonContainer>
              <Button
                $x={12}
                $y={2.25}
                $backgroundcolor={theme.colors.NAVYBLUE5}
                $bordercolor={theme.colors.NAVYBLUE5}
                $textcolor={theme.colors.WHITE}
                text={"홈으로 돌아가기"}
                handleClick={this.props.handleClick}
              />
            </ButtonContainer>
          </CompleteContainer>
        </Flex>
      );
    }

    return this.props.children;
  }
}

export default ErrorBoundary;
