(:: pragma bea:dtfFile-class type="requestquote.RequestQuoteTransformation" ::)

declare namespace xf = "http://tempuri.org/Tutorial_Process_Application_Web/src/requestquote/RequestQuotepriceProcessorControl_returnPrice/";
declare namespace ns-1 = "http://www.example.org/price";

declare function xf:RequestQuotepriceProcessorControl_returnPrice($itemID_arg as xs:int,
    $price_arg as xs:float)
    as element(ns-1:priceRequest) {
        <ns-1:priceRequest>
            <ns-1:widgetId>{ xs:integer( $itemID_arg ) }</ns-1:widgetId>
            <ns-1:price>{ $price_arg }</ns-1:price>
        </ns-1:priceRequest>
};

declare variable $itemID_arg as xs:int external;
declare variable $price_arg as xs:float external;

xf:RequestQuotepriceProcessorControl_returnPrice($itemID_arg,
    $price_arg)
