(:: pragma bea:dtfFile-class type="requestquote.RequestQuoteTransformation" ::)
(:: pragma bea:local-element-parameter parameter="$iter_forEach1" type="ns-1:quoteRequest/ns-1:widgetRequest" ::)
(:: pragma bea:java-type-return type="requestquote.RequestQuoteTransformation.availProcessorControlgetAvailObject" ::)

declare namespace xf = "http://tempuri.org/Tutorial_Process_Application_Web/src/requestquote/RequestQuoteavailProcessorControlgetAvail/";
declare namespace ns-1 = "http://www.example.org/request";

declare function xf:RequestQuoteavailProcessorControlgetAvail($iter_forEach1 as element())
    as element() {
        <availProcessorControlgetAvailObject>
            <itemID_arg>{ xs:int( data($iter_forEach1/ns-1:widgetId) ) }</itemID_arg>
            <quantity_arg>{ xs:int( data($iter_forEach1/ns-1:quantity) ) }</quantity_arg>
        </availProcessorControlgetAvailObject>
};

declare variable $iter_forEach1 as element() external;

xf:RequestQuoteavailProcessorControlgetAvail($iter_forEach1)
