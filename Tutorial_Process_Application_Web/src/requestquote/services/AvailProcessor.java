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
@WLHttpTransport(serviceUri = "requestquote/services/AvailProcessor.jws")
@WebService(serviceName = "AvailProcessor",
            targetNamespace = "http://www.openuri.org/")
@javax.jws.soap.SOAPBinding(style = javax.jws.soap.SOAPBinding.Style.DOCUMENT, 
                            use = javax.jws.soap.SOAPBinding.Use.LITERAL, 
                            parameterStyle = javax.jws.soap.SOAPBinding.ParameterStyle.WRAPPED)
public class AvailProcessor implements java.io.Serializable
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
    @WebResult(name = "getAvailResult")
    public void getAvail(int itemID, int quantity)
    {
        int qty = calcQuantity(quantity);
        boolean avail = calcAvail(quantity);
        String date = calcDate(quantity);
        callback.avail(itemID, qty, avail, date);
    }

    private int calcQuantity(int quantity)
    {

        if (quantity >= 1 && quantity < 100)
        {
            return quantity;
        }

        if (quantity >= 100 && quantity < 300)
        {
            return quantity;
        }

        if (quantity >= 300)
        {
            return 0;
        }

        return 0;
    }

    private boolean calcAvail(int quantity)
    {

        if (quantity >= 1 && quantity < 100)
        {
            return true;
        }

        if (quantity >= 100 && quantity < 300)
        {
            return true;
        }

        if (quantity >= 300)
        {
            return false;
        }

        return false;
    }

    private String calcDate(int quantity)
    {

        if (quantity >= 1 && quantity < 100)
        {
            return "2004-06-30";
        }

        if (quantity >= 100 && quantity < 300)
        {
            return "2004-06-30";
        }

        if (quantity >= 300)
        {
            return "BackOrder";
        }

        return "BackOrder";
    }

    @CallbackService()
    public interface Callback extends weblogic.wsee.jws.CallbackInterface {
    
		@Conversation(phase = Conversation.Phase.FINISH)
		@MessageBuffer()
		@WebMethod()
		@Oneway
        public void avail(int itemID, int qty, boolean avail, String date);

    }
    static final long serialVersionUID = 1L;
}
