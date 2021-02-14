xquery version "1.0" encoding "Cp1252";
(:: pragma bea:dtfFile-class type="requestquote.PriceAvailTransformations" ::)


declare namespace xf = "http://tempuri.org/RequestQuoteWeb/src/requestquote/convertAvailListToXML/";
declare namespace ns0 = "http://www.example.org/avail";
declare namespace ns1 = "http://www.w3.org/2001/XMLSchema";

declare function xf:convertAvailListToXML($_XmlObjectListDoc as element()+)
    as element(ns0:availQuote) {
    <ns0:availQuote>
    {
        for $XmlObjectList1 in $_XmlObjectListDoc
            return
                <ns0:availRequest>{ $XmlObjectList1/@* , $XmlObjectList1/node() }
                </ns0:availRequest>
    }
	</ns0:availQuote>
};


declare variable $_XmlObjectListDoc as element()+ external;

xf:convertAvailListToXML($_XmlObjectListDoc)
