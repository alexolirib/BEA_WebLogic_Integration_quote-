package requestquote.services;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import weblogic.jws.CallbackService;
import com.bea.control.ServiceControl.Conversation;
import weblogic.jws.MessageBuffer;
import weblogic.jws.Transactional;
import weblogic.jws.WLHttpTransport;
import weblogic.jws.soap.SOAPBinding;
import weblogic.wsee.jws.JwsContext;

@Transactional(true)
@WLHttpTransport(serviceUri = "requestquote/services/TaxCalc.jws")
@WebService(serviceName = "TaxCalc",
            targetNamespace = "http://www.openuri.org/")
@javax.jws.soap.SOAPBinding(style = javax.jws.soap.SOAPBinding.Style.DOCUMENT, 
                            use = javax.jws.soap.SOAPBinding.Use.LITERAL, 
                            parameterStyle = javax.jws.soap.SOAPBinding.ParameterStyle.WRAPPED)
public class TaxCalc implements java.io.Serializable
{
    @weblogic.jws.Callback()
    public Callback callback;

    @Conversation(phase = Conversation.Phase.START)
    @SOAPBinding(style = javax.jws.soap.SOAPBinding.Style.DOCUMENT, 
                 use = javax.jws.soap.SOAPBinding.Use.LITERAL, 
                 parameterStyle = javax.jws.soap.SOAPBinding.ParameterStyle.WRAPPED)
    @WebMethod()
    @WebResult(name = "requestTaxRateResult")
    public void requestTaxRate(String stateID) {
        float taxRate = calcTaxRate(stateID);
        callback.returnTaxRate(taxRate);
    }

    private float calcTaxRate(String stateID) {

        if (stateID.equalsIgnoreCase("CA") || stateID.equalsIgnoreCase("California")) {
            return (new Float(0.08125)).floatValue();
        }

        if (stateID.equalsIgnoreCase("NJ") || stateID.equalsIgnoreCase("New Jersey")) {
            return (new Float(0.0800)).floatValue();
        }

        return (new Float(0)).floatValue();
    }

    @CallbackService()
    public interface Callback extends weblogic.wsee.jws.CallbackInterface {

	    @Conversation(phase = Conversation.Phase.FINISH)
	    @MessageBuffer()
	    @WebMethod()
	    @Oneway
	    public void returnTaxRate(float taxRate);
    }
    static final long serialVersionUID = 1L;
}
