xquery version "1.0" encoding "Cp1252";
(:: pragma bea:dtfFile-class type="requestquote.services.TaxCalcProcessTransformation" ::)


declare namespace xf = "http://tempuri.org/RequestQuoteWeb/src/requestquote/services/stateToString/";
declare namespace ns0 = "http://www.w3.org/2001/XMLSchema";
declare namespace ns1 = "http://www.example.org/request";

declare function xf:stateToString($_quoteRequestDoc as element(ns1:quoteRequest))
    as xs:string{
        data($_quoteRequestDoc/ns1:shipAddress/@state)
};

declare variable $_quoteRequestDoc as element(ns1:quoteRequest) external;

xf:stateToString($_quoteRequestDoc)
