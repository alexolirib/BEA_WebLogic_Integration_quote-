package requestquote.services;
import com.bea.control.ServiceControl;

import org.apache.beehive.controls.api.events.EventSet;
import org.apache.beehive.controls.api.bean.ControlExtension;

@ServiceControl.Location(urls = {"http:requestquote/services/TaxCalc.jws"})
@ServiceControl.HttpSoapProtocol
@ServiceControl.SOAPBinding(style = ServiceControl.SOAPBinding.Style.DOCUMENT, use = ServiceControl.SOAPBinding.Use.LITERAL, parameterStyle = ServiceControl.SOAPBinding.ParameterStyle.WRAPPED)
@ServiceControl.WSDL(resourcePath = "requestquote/services/TaxCalc.wsdl", service = "TaxCalc")
@ControlExtension
public interface TaxCalcControl extends ServiceControl
{
    static final long serialVersionUID = 1L;

    @ServiceControl.HttpSoapProtocol
    @ServiceControl.SOAPBinding(style = ServiceControl.SOAPBinding.Style.DOCUMENT, use = ServiceControl.SOAPBinding.Use.LITERAL, parameterStyle = ServiceControl.SOAPBinding.ParameterStyle.WRAPPED)
    @EventSet(unicast=true)
    public interface Callback extends ServiceControl.Callback
    {
        @ServiceControl.ExternalCallbackEvent
        public void returnTaxRate(float taxRate_arg);
    }


    public void requestTaxRate(java.lang.String stateID_arg);
}
