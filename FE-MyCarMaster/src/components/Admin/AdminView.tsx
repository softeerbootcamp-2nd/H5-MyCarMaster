import { useState } from "react";
import { Flex } from "@styles/core.style";
import theme from "@styles/Theme";
import {
  ClientBox,
  QuotationBox,
  ClientBoxText,
  QuotationLookBox,
  QuotationIFrame,
  ClientQuotationDetailBox,
} from "./style";

type ClientType = {
  id: number;
  estimateUrl: string;
  client: {
    name: string;
    phone: string;
    email: string;
  };
};

type AdminViewProps = {
  clientList: ClientType[];
};

export default function AdminView({ clientList }: AdminViewProps) {
  const [isQuotationUrl, setIsQuotationUrl] = useState<string | null>(null);
  const [clientId, setClientId] = useState<number>(0);
  const handleQuotationUrl = (url: string, id: number) => {
    setIsQuotationUrl(url);
    setClientId(id);
  };
  return (
    <QuotationBox>
      {isQuotationUrl === null && (
        <Flex
          $flexDirection="row"
          $wrap={true}
          $alignContent="flex-start"
          $gap="1rem"
        >
          {clientList &&
            clientList.map((client, index) => (
              <ClientBox
                key={index}
                $justifyContent="center"
                $alignItems="center"
                onClick={() => handleQuotationUrl(client.estimateUrl, index)}
              >
                <Flex $flexDirection="column" $gap="0.5rem">
                  <ClientBoxText $font={theme.fonts.Medium15}>
                    이름 : {client.client.name.slice(0, 1)}**
                  </ClientBoxText>
                  <ClientBoxText $font={theme.fonts.Regular10}>
                    전화번호 : {client.client.phone.slice(0, 9)}****
                  </ClientBoxText>
                  <ClientBoxText $font={theme.fonts.Regular10}>
                    이메일 : {client.client.email}
                  </ClientBoxText>
                </Flex>
              </ClientBox>
            ))}
        </Flex>
      )}

      {isQuotationUrl && (
        <>
          <ClientQuotationDetailBox
            $flexDirection="column"
            $position="absolute"
          >
            <ClientBoxText
              $font={theme.fonts.Medium10}
              $color={theme.colors.SMOOTH_RED}
              $isHover={true}
              onClick={() => setIsQuotationUrl(null)}
            >
              돌아가려면 클릭해주세요.
            </ClientBoxText>
            <ClientBoxText $font={theme.fonts.Medium15}>
              이름 : {clientList[clientId].client.name}
            </ClientBoxText>
            <ClientBoxText $font={theme.fonts.Regular10}>
              전화번호 : {clientList[clientId].client.phone}
            </ClientBoxText>
            <ClientBoxText $font={theme.fonts.Regular10}>
              이메일 : {clientList[clientId].client.email}
            </ClientBoxText>
          </ClientQuotationDetailBox>

          <QuotationLookBox>
            {/* client.estimateUrl에 해당하는 견적서를 보여주는 페이지 */}
            <QuotationIFrame src={isQuotationUrl} title="견적서" />
          </QuotationLookBox>
        </>
      )}
    </QuotationBox>
  );
}
