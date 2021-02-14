package requestquote.services; 

import com.bea.control.*;
import com.bea.jpd.JpdContext;
import com.bea.data.RawData;
import com.bea.wli.common.*;
import com.bea.wli.control.broker.MessageBroker;
import com.bea.wli.jpd.*;
import com.bea.wli.jpd.Callback;
import com.bea.wli.jpd.Process;
import org.apache.beehive.controls.api.bean.Control;
import org.apache.beehive.controls.api.events.EventHandler;
import org.apache.xmlbeans.XmlObject;

@Process(process = "<process name=\"TaxCalc\">" +
                   "  <clientRequest name=\"Client Request\" method=\"requestTaxRate\"/>" +
                   "  <controlSend name=\"Identify Shipping Address\" method=\"taxCalcProcessTransformationStateToString\"/>" +
                   "  <perform name=\"Calculate Tax Rate\" method=\"perform1\"/>" +
                   "  <clientCallback name=\"Client Response\" method=\"clientResponseCallbackHandler\"/>" +
                   "</process>")
@com.bea.wli.common.XQuery(prolog = "declare namespace ns0=\"http://www.example.org/request\";" +
                                    "declare function cond_requestXML_1($requestXML as element()) as  xs:boolean  {" +
                                    "      (data($requestXML/ns0:shipAddress/@state) = \"CA\") or (data($requestXML/ns0:shipAddress/@state) = \"California\")" +
                                    "};" +
                                    "declare function cond_requestXML_2($requestXML as element()) as  xs:boolean  {" +
                                    "      (data($requestXML/ns0:shipAddress/@state) = \"NJ\") or (data($requestXML/ns0:shipAddress/@state) = \"New Jersey\")" +
                                    "};", 
                           version = com.bea.wli.common.XQuery.Version.v2004)
public class TaxCalcProcess implements com.bea.jpd.ProcessDefinition
{
    static final long serialVersionUID = 1L;
    
    public float taxRate;

    public java.lang.String requestString;

    public org.example.request.QuoteRequestDocument requestXML;

    @com.bea.wli.jpd.Callback()
    public Callback callback;
    
    /*
     * The area above this comment contains Variable Declarations created from the Design View.
     * The area below this comment contains Control Declarations created from the Design View.
     * 
     * Code in these areas is generated automatically.
     * 
     * 2-Way editing is fully supported in these areas of code.
     * Warning: changing Process Variables and Control Declarations already in use by your application
     * may generate errors in your application if you do not update these declarations
     * in all locations where they are used.
     */

    @Transform()
    private requestquote.services.TaxCalcProcessTransformation taxCalcProcessTransformation;

    @com.bea.wli.jpd.Context()
    JpdContext context; //(..control insertion marker - do not modify this line)

	@Control
	private TaxCalculationProcess taxCalcProcess;
    
    /*
     * The area below this comment contains java methods referenced by communication nodes in the Process Language.
     *
     * Code in this area is generated automatically.
     *
     * 2-Way editing is partially supported in this area of code.
     * Warning: you can safely add code inside a method body but it must be outside of any PROTECTED SECTION
     * block comments. Modifications to code within these comments will prevent you from viewing information
     * in the Design View builder that generated this code.
     */



    public void taxCalcProcessTransformationStateToString() throws Exception
    {
        //#START: CODE GENERATED - PROTECTED SECTION - you can safely add code above this comment in this method. #//
        // input transform
        // method call
        this.requestString = taxCalcProcessTransformation.stateToString(this.requestXML);
        // output transform
        // output assignments
        //#END  : CODE GENERATED - PROTECTED SECTION - you can safely add code below this comment in this method. #//
    }


    public void requestTaxRate(org.example.request.QuoteRequestDocument quoteRequest)
    {
        //#START: CODE GENERATED - PROTECTED SECTION - you can safely add code above this comment in this method. #//
        // input transform
        // parameter assignment
        this.requestXML = quoteRequest;
        //#END  : CODE GENERATED - PROTECTED SECTION - you can safely add code below this comment in this method. #//
    }




    public void clientResponseCallbackHandler()
    {
        //#START: CODE GENERATED - PROTECTED SECTION - you can safely add code above this comment in this method. #//
        // input transform
        // method call
        callback.returnTaxRate(this.taxRate);
        // output transform
        // output assignments
        //#END  : CODE GENERATED - PROTECTED SECTION - you can safely add code below this comment in this method. #//
    }

    @CallbackInterface()
    public interface Callback extends ServiceBrokerControl //(..region end marker - do not modify this line)
    {

        void returnTaxRate(float salesTaxRate);
    }

    public void perform1() throws Exception{
        float taxRate = calcTaxRate(requestString);
    }
    
    private float calcTaxRate (String requestString) throws Exception{

        if (requestString.equalsIgnoreCase("CA") || requestString.equalsIgnoreCase("California")) {
            return taxRate = (new Float(0.08125)).floatValue();
        }

       if (requestString.equalsIgnoreCase("NJ") || requestString.equalsIgnoreCase("New Jersey")) {
          return taxRate = (new Float(0.0800)).floatValue();
        }

        return taxRate = 0;
    }   
}
    /*
     * Code inserted below this comment is for methods corresponding to Perform or Condition nodes
     * created in the Design View.
     *
     * Feel free to make modifications or add new code here.
     */
    

    

   


   
