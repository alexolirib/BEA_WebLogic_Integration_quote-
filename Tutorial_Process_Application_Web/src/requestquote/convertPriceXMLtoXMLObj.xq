xquery version "1.0" encoding "Cp1252";
(:: pragma bea:dtfFile-class type="requestquote.PriceAvailTransformations" ::)

declare namespace xf = "http://tempuri.org/RequestQuoteWeb/src/requestquote/convertPriceXMLtoXMLObj/";
declare namespace ns0 = "http://www.example.org/price";
declare namespace ns1 = "http://www.w3.org/2001/XMLSchema";

declare function xf:convertPriceXMLtoXMLObj($_priceRequestDoc as element(ns0:priceRequest))
    as element(*) {
        $_priceRequestDoc
};

declare variable $_priceRequestDoc as element(ns0:priceRequest) external;

xf:convertPriceXMLtoXMLObj($_priceRequestDoc)

