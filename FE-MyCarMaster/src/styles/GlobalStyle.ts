import { createGlobalStyle } from "styled-components";

const GlobalStyle = createGlobalStyle`
    a{
        text-decoration: none;
        color: inherit;
    }
    *{
        box-sizing: border-box;
    }
    html, body, div, span, h1, h2, h3, h4, h5, h6, p, 
    a, dl, dt, dd, ol, ul, li, form, label, table{
        margin: 0;
        padding: 0;
        border: 0;
        vertical-align: baseline;
    }
    body{
        line-height: 1;
        font-family: 'Noto Sans KR', sans-serif;
        background-color: #FFFFFF;
    }
    ol, ul{
        list-style: none;
    }
    button {
        border: 0;
        padding: 0;
        background: transparent;
        cursor: pointer;
    }


    @font-face {
        font-family: HyundaiSansBold;
        font-display: swap;
        src: local('HyundaiSansBold'), url('./fonts/HyundaiSansHeadKRBold.woff2') format('woff2'),
      url('./fonts/fonts/HyundaiSansHeadKRBold.woff') format('woff');
    }
    @font-face {
        font-family: HyundaiSansMedium;
        font-display: swap;
        src: local('HyundaiSansMedium'), url('./fonts/HyundaiSansHeadKRMedium.woff2') format('woff2'),
      url('./fonts/HyundaiSansHeadKRMedium.woff') format('woff');
    }
    @font-face {
        font-family: HyundaiSansRegular;
        font-display: swap;
        src: local('HyundaiSansRegular'), url('./fonts/HyundaiSansHeadKRRegular.woff2') format('woff2'),
      url('./fonts/HyundaiSansHeadKRRegular.woff') format('woff');
    }

    // public font setting
`;

export default GlobalStyle;
