xquery version "1.0" encoding "Cp1252";
(:: pragma bea:dtfFile-class type="requestquote.PriceAvailTransformations" ::)

declare namespace xf = "http://tempuri.org/RequestQuoteWeb/src/requestquote/convertPriceListToXML/";
declare namespace ns0 = "http://www.example.org/price";
declare namespace ns1 = "http://www.w3.org/2001/XMLSchema";
declare namespace ns2 = "http://www.example.org/request";

declare function xf:convertPriceListToXML($_quoteRequestDoc as element(ns2:quoteRequest),
    $_XmlObjectListDoc as element()+)
    as element(ns0:priceQuote) {
    <ns0:priceQuote>
    <ns0:customerName>{ data($_quoteRequestDoc/ns2:customerName) }</ns0:customerName>
    <ns0:shipAddress street = "{ data($_quoteRequestDoc/ns2:shipAddress/@street) }"
                     city = "{ data($_quoteRequestDoc/ns2:shipAddress/@city) }"
                     state = "{ data($_quoteRequestDoc/ns2:shipAddress/@state) }"
                     zip = "{ data($_quoteRequestDoc/ns2:shipAddress/@zip) }" />
    <ns0:priceRequests>
        {
            for $XmlObjectList1 in $_XmlObjectListDoc
                return
                    <ns0:priceRequest>{ $XmlObjectList1/@* , $XmlObjectList1/node() }</ns0:priceRequest>
        }
    </ns0:priceRequests>
	</ns0:priceQuote>
};


declare variable $_quoteRequestDoc as element(ns2:quoteRequest) external;
declare variable $_XmlObjectListDoc as element()+ external;

xf:convertPriceListToXML($_quoteRequestDoc,$_XmlObjectListDoc)
