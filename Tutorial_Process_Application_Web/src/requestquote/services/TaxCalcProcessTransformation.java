package requestquote.services; 

import com.bea.wli.common.XQuery;
import com.bea.wli.transform.DataTransformation;
import com.bea.wli.transform.XQueryTransform;

@DataTransformation()
@XQuery(version = XQuery.Version.v2004)
public abstract class TaxCalcProcessTransformation implements java.io.Serializable
{   
    static final long serialVersionUID = 1L;

	@XQueryTransform(transformType = XQueryTransform.TransformMethodType.XQUERY_REF, 
					 value = "stateToString.xq", 
					 schemaValidate = @XQueryTransform.SchemaValidate(returnValue = false, 
							 										  parameters = false))
	public abstract java.lang.String stateToString(org.example.request.QuoteRequestDocument _quoteRequestDoc);
} 
