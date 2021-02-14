package requestquote; 

import com.bea.wli.transform.DataTransformation;
import com.bea.transform.TransformSource;
import java.io.Serializable;

@DataTransformation()
public abstract class RequestQuoteTransformation implements com.bea.transform.TransformSource
{
    static final long serialVersionUID = 1L;


	@com.bea.wli.transform.XQueryTransform(transformType = com.bea.wli.transform.XQueryTransform.TransformMethodType.XQUERY_REF, value = "RequestQuotetaxCalcControlrequestTaxRate.xq", schemaValidate = @com.bea.wli.transform.XQueryTransform.SchemaValidate(returnValue = false, parameters = false))
	public abstract java.lang.String taxCalcControlrequestTaxRate(org.example.request.QuoteRequestDocument requestXML);


	@com.bea.wli.transform.XQueryTransform(transformType = com.bea.wli.transform.XQueryTransform.TransformMethodType.XQUERY_REF, value = "RequestQuotepriceProcessorControlgetPrice.xq", schemaValidate = @com.bea.wli.transform.XQueryTransform.SchemaValidate(returnValue = false, parameters = false))
	public abstract int priceProcessorControlgetPrice(org.example.request.WidgetRequestDocument.WidgetRequest iter_forEach1);


	@com.bea.wli.transform.XQueryTransform(transformType = com.bea.wli.transform.XQueryTransform.TransformMethodType.XQUERY_REF, value = "RequestQuotepriceProcessorControl_returnPrice.xq", schemaValidate = @com.bea.wli.transform.XQueryTransform.SchemaValidate(returnValue = false, parameters = false))
	public abstract org.example.price.PriceRequestDocument priceProcessorControl_returnPrice(int itemID_arg, float price_arg);


	public static class availProcessorControlgetAvailObject implements java.io.Serializable {
		public int itemID_arg;

		public int quantity_arg;
	}


	@com.bea.wli.transform.XQueryTransform(transformType = com.bea.wli.transform.XQueryTransform.TransformMethodType.XQUERY_REF, value = "RequestQuoteavailProcessorControlgetAvail.xq", schemaValidate = @com.bea.wli.transform.XQueryTransform.SchemaValidate(returnValue = false, parameters = false))
	public abstract availProcessorControlgetAvailObject availProcessorControlgetAvail(org.example.request.WidgetRequestDocument.WidgetRequest iter_forEach1);


	@com.bea.wli.transform.XQueryTransform(transformType = com.bea.wli.transform.XQueryTransform.TransformMethodType.XQUERY_REF, value = "RequestQuoteavailProcessorControl_avail.xq", schemaValidate = @com.bea.wli.transform.XQueryTransform.SchemaValidate(returnValue = false, parameters = false))
	public abstract org.example.avail.AvailRequestDocument availProcessorControl_avail(int itemID_arg, int qty_arg, boolean avail_arg, java.lang.String date_arg);
    
}
