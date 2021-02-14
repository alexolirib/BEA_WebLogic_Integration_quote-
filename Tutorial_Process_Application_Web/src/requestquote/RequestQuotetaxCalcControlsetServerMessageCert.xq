xquery version "1.0" encoding "Cp1252";
(:: pragma bea:dtfFile-class type="requestquote.RequestQuoteTransformation" ::)
(:: pragma bea:java-type-return type="java.security.cert.X509Certificate" ::)


declare namespace xf = "http://tempuri.org/Tutorial_Process_Application_Web/src/requestquote/RequestQuotetaxCalcControlsetServerMessageCert/";
declare namespace ns-1 = "http://www.example.org/request";

declare function xf:RequestQuotetaxCalcControlsetServerMessageCert($requestXML as element(ns-1:quoteRequest))
    as element() {
        <X509Certificate/>
};

declare variable $requestXML as element(ns-1:quoteRequest) external;

xf:RequestQuotetaxCalcControlsetServerMessageCert($requestXML)
