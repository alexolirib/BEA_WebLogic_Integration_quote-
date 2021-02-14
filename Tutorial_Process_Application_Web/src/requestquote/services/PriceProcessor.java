package requestquote.services;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import weblogic.jws.CallbackService;
import weblogic.jws.Context;
import com.bea.control.ServiceControl.Conversation;
import weblogic.jws.MessageBuffer;
import weblogic.jws.Transactional;
import weblogic.jws.WLHttpTransport;
import weblogic.jws.soap.SOAPBinding;
import weblogic.wsee.jws.JwsContext;

@Transactional(true)
@WLHttpTransport(serviceUri = "requestquote/services/PriceProcessor.jws")
@WebService(serviceName = "PriceProcessor",
            targetNamespace = "http://www.openuri.org/")
@javax.jws.soap.SOAPBinding(style = javax.jws.soap.SOAPBinding.Style.DOCUMENT, 
                            use = javax.jws.soap.SOAPBinding.Use.LITERAL, 
                            parameterStyle = javax.jws.soap.SOAPBinding.ParameterStyle.WRAPPED)
public class PriceProcessor implements java.io.Serializable
{
    @weblogic.jws.Callback()
    public Callback callback;

    @Context()
    JwsContext context;

    @Conversation(phase = Conversation.Phase.START)
    @SOAPBinding(style = javax.jws.soap.SOAPBinding.Style.DOCUMENT, 
                 use = javax.jws.soap.SOAPBinding.Use.LITERAL, 
                 parameterStyle = javax.jws.soap.SOAPBinding.ParameterStyle.WRAPPED)
    @WebMethod()
    @WebResult(name = "getPriceResult")
    public void getPrice(int itemID) {
        float price = calcPrice(itemID);
        callback.returnPrice(itemID, price);

    }

    private float calcPrice(int itemID) {

        if (itemID <= 12) {
            return (new Float(125)).floatValue();
        }

        if (itemID <= 150) {
            return (new Float(175)).floatValue();
        }

        if (itemID <= 300) {
            return (new Float(25.50)).floatValue();
        }

        return (new Float(88)).floatValue();
    }

    @CallbackService()
    public interface Callback extends weblogic.wsee.jws.CallbackInterface 
    {

	    @Conversation(phase = Conversation.Phase.FINISH)
	    @MessageBuffer()
	    @WebMethod()
	    @Oneway
	    public void returnPrice(int itemID, float price);
    }
    
    static final long serialVersionUID = 1L;
}
