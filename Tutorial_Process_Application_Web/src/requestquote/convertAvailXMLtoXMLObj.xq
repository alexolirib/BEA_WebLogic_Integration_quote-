xquery version "1.0" encoding "Cp1252";
(:: pragma bea:dtfFile-class type="requestquote.PriceAvailTransformations" ::)

declare namespace xf = "http://tempuri.org/RequestQuoteWeb/src/requestquote/convertAvailXMLtoXMLObj/";
declare namespace ns0 = "http://www.example.org/avail";
declare namespace ns1 = "http://www.w3.org/2001/XMLSchema";

declare function xf:convertAvailXMLtoXMLObj($_availRequestDoc as element(ns0:availRequest))
    as element() {
        $_availRequestDoc
};

declare variable $_availRequestDoc as element(ns0:availRequest) external;

xf:convertAvailXMLtoXMLObj($_availRequestDoc)
