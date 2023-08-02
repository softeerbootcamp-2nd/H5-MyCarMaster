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
        font-style: normal;
        font-display: swap;
        src: url('src/assets/fonts/HyundaiSansHeadKRBold.ttf') format('truetype');
    }
    @font-face {
        font-family: HyundaiSansMedium;
        font-style: normal;
        font-display: swap;
        src: url('src/assets/fonts/HyundaiSansHeadKRMedium.ttf') format('truetype');
    }
    @font-face {
        font-family: HyundaiSansRegular;
        font-style: normal;
        font-display: swap;
        src: url('src/assets/fonts/HyundaiSansHeadKRRegular.ttf') format('truetype');
    }
`;

export default GlobalStyle;
