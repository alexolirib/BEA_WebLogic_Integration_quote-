package requestquote.services;


/**
 */
@org.apache.beehive.controls.api.bean.ControlExtension
@com.bea.wli.common.control.Location(uri = "/Tutorial_Process_Application_Web/requestquote/services/TaxCalcProcess.jpd")
public interface TaxCalculationProcess extends com.bea.control.ProcessControl {
	@org.apache.beehive.controls.api.events.EventSet(unicast = true)
	public interface Callback extends
			com.bea.control.callbacks.MessageBufferFailure {
		public void returnTaxRate(float salesTaxRate);

	}

	public void requestTaxRate(
			org.example.request.QuoteRequestDocument quoteRequest);

	public TaxCalculationProcess create();

	static final long serialVersionUID = 1L;
}

