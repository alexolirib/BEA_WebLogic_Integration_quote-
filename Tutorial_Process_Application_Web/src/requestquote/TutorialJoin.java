package requestquote; 

import com.bea.wli.common.XQuery;
import com.bea.wli.transform.DataTransformation;
import com.bea.wli.transform.XQueryFunction;

@DataTransformation()
@XQuery(version = XQuery.Version.v2004)
public abstract class TutorialJoin implements com.bea.transform.TransformSource
{
    static final long serialVersionUID = 1L;
    
	@com.bea.wli.transform.XQueryTransform(transformType = com.bea.wli.transform.XQueryTransform.TransformMethodType.XQUERY_REF, 
										   value = "join.xq", 
										   schemaValidate = @com.bea.wli.transform.XQueryTransform.SchemaValidate(returnValue = false, 
												   																  parameters = false))
	public abstract org.example.quote.QuoteDocument join(org.example.price.PriceQuoteDocument priceQuoteDoc, org.example.avail.AvailQuoteDocument availQuoteDoc, float taxRate);

    @XQueryFunction()
    public static float calculateTotalPrice(float taxRate, int quantity, float price, boolean fillOrder)
    {
		float totalTax, costNoTax, totalCost;
		if (fillOrder)
		{
			// Calculate the total tax
			totalTax = taxRate * quantity * price;
			// Calculate the total cost without tax
			costNoTax = quantity * price;
			// Add the tax and the cost to get the total cost
			totalCost = totalTax + costNoTax;
		}
		else
		{
			totalCost = 0;
		}
		return totalCost;
    }
} 
