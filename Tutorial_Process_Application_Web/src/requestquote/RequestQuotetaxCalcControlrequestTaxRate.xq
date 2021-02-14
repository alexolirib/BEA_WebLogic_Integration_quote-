(:: pragma bea:dtfFile-class type="requestquote.RequestQuoteTransformation" ::)

declare namespace xf = "http://tempuri.org/Tutorial_Process_Application_Web/src/requestquote/RequestQuotetaxCalcControlrequestTaxRate/";
declare namespace ns-1 = "http://www.example.org/request";

declare function xf:RequestQuotetaxCalcControlrequestTaxRate($requestXML as element(ns-1:quoteRequest))
    as xs:string {
        data($requestXML/ns-1:shipAddress/@state)
};

declare variable $requestXML as element(ns-1:quoteRequest) external;

xf:RequestQuotetaxCalcControlrequestTaxRate($requestXML)
