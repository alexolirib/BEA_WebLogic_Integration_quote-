package requestquote.services;
import com.bea.control.ServiceControl;

import org.apache.beehive.controls.api.events.EventSet;
import org.apache.beehive.controls.api.bean.ControlExtension;

@ServiceControl.Location(urls = {"http:requestquote/services/AvailProcessor.jws"})
@ServiceControl.HttpSoapProtocol
@ServiceControl.SOAPBinding(style = ServiceControl.SOAPBinding.Style.DOCUMENT, use = ServiceControl.SOAPBinding.Use.LITERAL, parameterStyle = ServiceControl.SOAPBinding.ParameterStyle.WRAPPED)
@ServiceControl.WSDL(resourcePath = "requestquote/services/AvailProcessor.wsdl", service = "AvailProcessor")
@ControlExtension
public interface AvailProcessorControl extends ServiceControl
{
    static final long serialVersionUID = 1L;

    @ServiceControl.HttpSoapProtocol
    @ServiceControl.SOAPBinding(style = ServiceControl.SOAPBinding.Style.DOCUMENT, use = ServiceControl.SOAPBinding.Use.LITERAL, parameterStyle = ServiceControl.SOAPBinding.ParameterStyle.WRAPPED)
    @EventSet(unicast=true)
    public interface Callback extends ServiceControl.Callback
    {
        @ServiceControl.ExternalCallbackEvent
        public void avail(int itemID_arg,int qty_arg,boolean avail_arg,java.lang.String date_arg);
    }


    public void getAvail(int itemID_arg,int quantity_arg);
}
