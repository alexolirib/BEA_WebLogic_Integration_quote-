(:: pragma bea:dtfFile-class type="requestquote.RequestQuoteTransformation" ::)
(:: pragma bea:local-element-parameter parameter="$iter_forEach1" type="ns-1:quoteRequest/ns-1:widgetRequest" ::)

declare namespace xf = "http://tempuri.org/Tutorial_Process_Application_Web/src/requestquote/RequestQuotepriceProcessorControlgetPrice/";
declare namespace ns-1 = "http://www.example.org/request";

declare function xf:RequestQuotepriceProcessorControlgetPrice($iter_forEach1 as element())
    as xs:int {
        xs:int( data($iter_forEach1/ns-1:widgetId) )
};

declare variable $iter_forEach1 as element() external;

xf:RequestQuotepriceProcessorControlgetPrice($iter_forEach1)
