xquery version "1.0" encoding "Cp1252";
(:: pragma bea:dtfFile-class type="requestquote.TutorialJoin" ::)


declare namespace xf = "http://tempuri.org/RequestQuoteWeb/src/requestquote/join/";
declare namespace ns0 = "http://www.example.org/price";
declare namespace ns1 = "http://www.example.org/avail";
declare namespace ns3 = "http://www.w3.org/2001/XMLSchema";
declare namespace ns2 = "http://www.example.org/quote";

declare function xf:join($priceQuoteDoc as element(ns0:priceQuote),
    $availQuoteDoc as element(ns1:availQuote),
    $taxRate as xs:float)
    as element(ns2:quote) {
    <ns2:quote>
    <name>{ data($priceQuoteDoc/ns0:customerName) }</name>
    <address>{ fn:concat($priceQuoteDoc/ns0:shipAddress/@street , ",", $priceQuoteDoc/ns0:shipAddress/@city ,",", fn:upper-case($priceQuoteDoc/ns0:shipAddress/@state) , ",", $priceQuoteDoc/ns0:shipAddress/@zip) }</address>
    {
        for $priceRequest in $priceQuoteDoc/ns0:priceRequests/ns0:priceRequest,
            $availRequest in $availQuoteDoc/ns1:availRequest
        where data($priceRequest/ns0:widgetId) = data($availRequest/ns1:widgetId)
        return
            <quoteResponse>
                <widgetId>{ data($priceRequest/ns0:widgetId) }</widgetId>
                <unitPrice>{ data($priceRequest/ns0:price) }</unitPrice>
                <requestedQuantity>{ data($availRequest/ns1:requestedQuantity) }</requestedQuantity>
                <fillOrder>{ data($availRequest/ns1:quantityAvail) }</fillOrder>
                {
                    for $shipDate in $availRequest/ns1:shipDate
                    return
                        <shipDate>{ data($shipDate) }</shipDate>
                }
                <taxRate>{ $taxRate }</taxRate>
                <totalCost>{ calculateTotalPrice($taxRate,
                                  $availRequest/ns1:requestedQuantity,
                                  $priceRequest/ns0:price,
                                  $availRequest/ns1:quantityAvail) }</totalCost>
            </quoteResponse>
    }
</ns2:quote>
};

declare variable $priceQuoteDoc as element(ns0:priceQuote) external;
declare variable $availQuoteDoc as element(ns1:availQuote) external;
declare variable $taxRate as xs:float external;

xf:join($priceQuoteDoc,
    $availQuoteDoc,
    $taxRate)
