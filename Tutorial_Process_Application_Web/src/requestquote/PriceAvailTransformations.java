package requestquote; 

import com.bea.wli.common.XQuery;
import com.bea.wli.transform.DataTransformation;
import com.bea.wli.transform.XQueryTransform;
import com.bea.xml.XmlObjectList;

@DataTransformation()
@XQuery(version = XQuery.Version.v2004)
public abstract class PriceAvailTransformations implements com.bea.transform.TransformSource
{

    @XQueryTransform(transformType = XQueryTransform.TransformMethodType.XQUERY_REF,
                     value = "convertAvailListToXML.xq",
                     schemaValidate = @XQueryTransform.SchemaValidate(returnValue = false,
                                                                      parameters = false))
    public abstract org.example.avail.AvailQuoteDocument convertAvailListToXML(XmlObjectList _XmlObjectListDoc);

    @XQueryTransform(transformType = com.bea.wli.transform.XQueryTransform.TransformMethodType.XQUERY_REF, 
    				 value = "convertAvailXMLtoXMLObj.xq", 
    				 schemaValidate = @XQueryTransform.SchemaValidate(returnValue = false, 
    						 										  parameters = false))
    public abstract org.apache.xmlbeans.XmlObject convertAvailXMLtoXMLObj(org.example.avail.AvailRequestDocument _availRequestDoc);
	
	@XQueryTransform(transformType = XQueryTransform.TransformMethodType.XQUERY_REF, 
					 value = "convertPriceXMLtoXMLObj.xq", 
					 schemaValidate = @XQueryTransform.SchemaValidate(returnValue = false, 
															 parameters = false))
	public abstract org.apache.xmlbeans.XmlObject convertPriceXMLtoXMLObj(org.example.price.PriceRequestDocument _priceRequestDoc);

    @XQueryTransform(transformType = XQueryTransform.TransformMethodType.XQUERY_REF,
                     value = "convertPriceListToXML.xq",
                     schemaValidate = @XQueryTransform.SchemaValidate(returnValue = false,
                                                                      parameters = false))
    public abstract org.example.price.PriceQuoteDocument convertPriceListToXML(org.example.request.QuoteRequestDocument _quoteRequestDoc, XmlObjectList _XmlObjectListDoc);


    static final long serialVersionUID = 1L;
} 
