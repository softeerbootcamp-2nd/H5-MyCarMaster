import { useNavigate } from "react-router-dom";
import { Flex } from "@styles/core.style";
import { Container, HeadText, DescriptionText } from "./style";
import {
  useQuotationState,
  useQuotationDispatch,
} from "@contexts/QuotationContext";
import { Button, Modals } from "@common/index";
import theme from "@styles/Theme";
import SelectListWrapper from "./SelectListWrapper";
import FoldScreen from "./FoldScreen";
import indexNameSwitching from "@utils/indexNameSwitching";
import { useState } from "react";
import { ModalType } from "@/constants/Modal.constants";
import { NavigationIndex } from "@/constants/Navigate.constants";

export default function Footer() {
  const { navigationId, isFirst, detailQuotation } = useQuotationState();
  const quotationDispatch = useQuotationDispatch();
  const name = indexNameSwitching(navigationId) as string;
  const navigate = useNavigate();
  const [isOpen, setIsOpen] = useState(false);

  const buttonHandler = (cal: number) => {
    const navigationIndex = navigationId + cal;
    quotationDispatch({
      type: "NAVIGATE",
      payload: {
        navigationId: navigationIndex,
        isFirst: {
          ...isFirst,
          [navigationIndex]: false,
        },
      },
    });
  };

  const navigateQuotationHandler = () => {
    if (detailQuotation.wheelDriveQuotation.name === "") {
      setIsOpen(true);
    } else {
      quotationDispatch({
        type: "NAVIGATE",
        payload: {
          navigationId: navigationId + 1,
          isFirst: { ...isFirst, [navigationId + 1]: false },
        },
      });
      navigate("/quotation");
    }
  };

  const alertHandler = () => {
    quotationDispatch({
      type: "NAVIGATE",
      payload: {
        navigationId: NavigationIndex.WHEELTYPE,
      },
    });
  };

  return (
    <Container>
      <SelectListWrapper />
      <Flex $width="12rem" $flexDirection="column">
        <Flex
          $height="10.25rem"
          $flexDirection="column"
          $justifyContent="space-between"
        >
          <Flex $flexDirection="column">
            {name && (
              <>
                <HeadText>{name} 선택</HeadText>
                <DescriptionText>원하는 {name}을 선택해주세요.</DescriptionText>
              </>
            )}
          </Flex>
          <Flex
            $flexDirection="column"
            $gap="0.25rem"
            $justifyContent="flex-end"
          >
            {navigationId !== 0 && (
              <Button
                $x={12}
                $y={2.25}
                $backgroundcolor={`${theme.colors.WHITE}`}
                $textcolor={`${theme.colors.NAVYBLUE5}`}
                $bordercolor={`${theme.colors.NAVYBLUE5}`}
                text={"이전"}
                $font={theme.fonts.Regular12}
                handleClick={() => buttonHandler(-1)}
              />
            )}

            <Button
              $x={12}
              $y={2.25}
              $backgroundcolor={`${theme.colors.NAVYBLUE5}`}
              $textcolor={`${theme.colors.WHITE}`}
              $bordercolor={`${theme.colors.NAVYBLUE5}`}
              text={navigationId === 6 ? "견적서 완성" : "다음"}
              $font={theme.fonts.Regular12}
              handleClick={
                navigationId === 6
                  ? navigateQuotationHandler
                  : () => buttonHandler(1)
              }
            />
          </Flex>
        </Flex>
      </Flex>
      {navigationId === 0 && (
        <FoldScreen text={"내게 맞는 트림 찾기"} $switch={"searchTrim"} />
      )}
      {navigationId === 6 && (
        <FoldScreen text={"기본 옵션 보기"} $switch={"option"} />
      )}
      {isOpen && (
        <Modals
          setIsOpen={setIsOpen}
          isAlert={true}
          type={ModalType.ALERT}
          onClick={alertHandler}
          text={[
            "구동 방식이 선택되지 않았습니다.",
            "구동 방식 선택 페이지로 이동합니다.",
          ]}
        />
      )}
    </Container>
  );
}
