(:: pragma bea:dtfFile-class type="requestquote.RequestQuoteTransformation" ::)

declare namespace xf = "http://tempuri.org/Tutorial_Process_Application_Web/src/requestquote/RequestQuoteavailProcessorControl_avail/";
declare namespace ns-1 = "http://www.example.org/avail";

declare function xf:RequestQuoteavailProcessorControl_avail($itemID_arg as xs:int,
    $qty_arg as xs:int,
    $avail_arg as xs:boolean,
    $date_arg as xs:string)
    as element(ns-1:availRequest) {
        <ns-1:availRequest>
            <ns-1:widgetId>{ xs:integer( $itemID_arg ) }</ns-1:widgetId>
            <ns-1:requestedQuantity>{ $qty_arg }</ns-1:requestedQuantity>
            <ns-1:quantityAvail>{ $avail_arg }</ns-1:quantityAvail>
            <ns-1:shipDate>{ $date_arg }</ns-1:shipDate>
        </ns-1:availRequest>
};

declare variable $itemID_arg as xs:int external;
declare variable $qty_arg as xs:int external;
declare variable $avail_arg as xs:boolean external;
declare variable $date_arg as xs:string external;

xf:RequestQuoteavailProcessorControl_avail($itemID_arg,
    $qty_arg,
    $avail_arg,
    $date_arg)
