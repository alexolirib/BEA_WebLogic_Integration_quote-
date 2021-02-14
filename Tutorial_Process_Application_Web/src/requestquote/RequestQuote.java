package requestquote;

import com.bea.jpd.ProcessDefinition;

import com.bea.jpd.JpdContext;
import org.apache.beehive.controls.api.bean.Control;
import requestquote.services.TaxCalcControl;
import requestquote.services.PriceProcessorControl;
import requestquote.services.AvailProcessorControl;

@com.bea.wli.common.XQuery(prolog = 
"declare namespace ns-1 = \"http://www.example.org/request\";" + 
"declare function exprFunction0($requestXML) as xs:boolean {" + 
"  (((fn:data($requestXML/ns-1:shipAddress/@state) = \"California\") or (fn:data($requestXML/ns-1:shipAddress/@state) = \"CA\")) or (fn:data($requestXML/ns-1:shipAddress/@state) = \"NJ\")) or (fn:data($requestXML/ns-1:shipAddress/@state) = \"New Jersey\")" + 
"};" + 
"declare function exprFunction1($requestXML as element()) as element()* {" + 
"  $requestXML/ns-1:widgetRequest" + 
"};")
@com.bea.wli.jpd.Process(process = 
"<process name=\"RequestQuote\">" + 
"  <clientRequest name=\"Client Requests Quote\" method=\"quoteRequest\"/>" + 
"  <decision name=\"Sales Tax Calculation Needed?\">" + 
"    <if name=\"tax\" condition=\"exprFunction0($requestXML)\">" + 
"      <controlSend name=\"requestTaxRate\" method=\"taxCalcProcessRequestTaxRate\"/>" + 
"      <controlReceive name=\"returnTaxRate\" method=\"taxCalcProcess_returnTaxRate\"/>" + 
"    </if>" + 
"    <default name=\"noTax\"/>" + 
"  </decision>" + 
"  <forEach name=\"For Each\" variable=\"iter_forEach1\" expression=\"exprFunction1($requestXML)\">" + 
"    <parallel name=\"Parallel\">" + 
"      <branch name=\"getPrice\">" + 
"        <controlSend name=\"Request Price\" method=\"priceProcessorControlGetPrice\"/>" + 
"        <controlReceive name=\"Receive Price\" method=\"priceProcessorControl_returnPrice\"/>" + 
"        <controlSend name=\"Create PriceList\" method=\"priceAvailTransformationsConvertPriceXMLtoXMLObj\"/>" + 
"      </branch>" + 
"      <branch name=\"getAvailability\">" + 
"        <controlSend name=\"Control Send\" method=\"availProcessorControlGetAvail\"/>" + 
"        <controlReceive name=\"Control Receive\" method=\"availProcessorControl_avail\"/>" + 
"        <controlSend name=\"convertAvailXMLtoXMLObj\" method=\"priceAvailTransformationsConvertAvailXMLtoXMLObj\"/>" + 
"      </branch>" + 
"    </parallel>" + 
"  </forEach>" + 
"  <controlSend name=\"Convert PriceList To PriceQuote XML\" method=\"priceAvailTransformationsConvertPriceListToXML\"/>" + 
"  <controlSend name=\"Convert AvailList to AvailQuote XML\" method=\"priceAvailTransformationsConvertAvailListToXML\"/>" + 
"  <controlSend name=\"Combine Price and Avail Quotes\" method=\"tutorialJoinJoin\"/>" + 
"  <controlSend name=\"Write Quote to File\" method=\"myFileQuoteWrite\"/>" + 
"  <clientCallback name=\"Send Quote\" method=\"clientResponseCallbackHandler\"/>" + 
"</process>")
public class RequestQuote implements ProcessDefinition {


	@com.bea.wli.jpd.Callback
	public Callback callback;

	public com.bea.wli.control.dynamicProperties.FileControlPropertiesDocument fileProperties;

	public org.example.quote.QuoteDocument Quote;

	@com.bea.wli.jpd.Transform
	private requestquote.TutorialJoin tutorialJoin;

	public org.example.avail.AvailQuoteDocument availQuote;

	public org.example.price.PriceQuoteDocument priceQuote1;

	@com.bea.wli.jpd.XmlList
	public com.bea.xml.XmlObjectList availList;

	public org.example.avail.AvailRequestDocument avail;

	@com.bea.wli.jpd.XmlList
	public com.bea.xml.XmlObjectList priceList;

	@com.bea.wli.jpd.Transform
	private requestquote.PriceAvailTransformations priceAvailTransformations;

	public org.example.price.PriceRequestDocument price;

	public org.example.request.WidgetRequestDocument.WidgetRequest iter_forEach1;

	public float taxRate = 0f;

	@com.bea.wli.jpd.Transform
	private requestquote.RequestQuoteTransformation transformations;

	public org.example.request.QuoteRequestDocument requestXML;

	@com.bea.wli.jpd.Context
	JpdContext context;

	static final long serialVersionUID = 1;

	@Control
	private TaxCalcControl taxCalcControl;

	@Control
	private TaxCalculationProcess taxCalcProcess;

	@Control
	private PriceProcessorControl priceProcessorControl;

	@Control
	private AvailProcessorControl availProcessorControl;

	@Control
	private MyFileQuote myFileQuote;

	public void quoteRequest(org.example.request.QuoteRequestDocument requestXML) {
        // #START: CODE GENERATED - PROTECTED SECTION - you can safely add code above this comment in this method. #//
        // input transform
        // parameter assignment
        this.requestXML = requestXML;
        // #END  : CODE GENERATED - PROTECTED SECTION - you can safely add code below this comment in this method. #//
    }

	public void taxCalcProcessRequestTaxRate() throws Exception {
        // #START: CODE GENERATED - PROTECTED SECTION - you can safely add code above this comment in this method. #//
        // input transform
        // method call
        taxCalcProcess.requestTaxRate(this.requestXML);
        // output transform
        // output assignments
        // #END  : CODE GENERATED - PROTECTED SECTION - you can safely add code below this comment in this method. #//
    }

	@org.apache.beehive.controls.api.events.EventHandler(field = "taxCalcProcess", eventSet = requestquote.TaxCalculationProcess.Callback.class, eventName = "returnTaxRate")
	public void taxCalcProcess_returnTaxRate(float salesTaxRate) {
        // #START: CODE GENERATED - PROTECTED SECTION - you can safely add code above this comment in this method. #//
        // input transform
        // parameter assignment
        this.taxRate = salesTaxRate;
        // #END  : CODE GENERATED - PROTECTED SECTION - you can safely add code below this comment in this method. #//
    }

	public void priceProcessorControlGetPrice() throws Exception {
        // #START: CODE GENERATED - PROTECTED SECTION - you can safely add code above this comment in this method. #//
        // input transform
        int obj = transformations.priceProcessorControlgetPrice(this.iter_forEach1);
        // method call
        priceProcessorControl.getPrice(obj);
        // output transform
        // output assignments
        // #END  : CODE GENERATED - PROTECTED SECTION - you can safely add code below this comment in this method. #//
    }

	@org.apache.beehive.controls.api.events.EventHandler(field = "priceProcessorControl", eventSet = requestquote.services.PriceProcessorControl.Callback.class, eventName = "returnPrice")
	public void priceProcessorControl_returnPrice(int itemID_arg, float price_arg) {
        // #START: CODE GENERATED - PROTECTED SECTION - you can safely add code above this comment in this method. #//
        // input transform
        org.example.price.PriceRequestDocument obj = transformations.priceProcessorControl_returnPrice(itemID_arg, price_arg);
        // parameter assignment
        this.price = obj;
        // #END  : CODE GENERATED - PROTECTED SECTION - you can safely add code below this comment in this method. #//
    }

	public void priceAvailTransformationsConvertPriceXMLtoXMLObj() throws Exception {
        // #START: CODE GENERATED - PROTECTED SECTION - you can safely add code above this comment in this method. #//
        // input transform
        // return method call
        org.apache.xmlbeans.XmlObject objReturn = priceAvailTransformations.convertPriceXMLtoXMLObj(this.price);
        // output transform
        // output assignments
        this.priceList.add(objReturn);
        // #END  : CODE GENERATED - PROTECTED SECTION - you can safely add code below this comment in this method. #//
    }

	public void availProcessorControlGetAvail() throws Exception {
        // #START: CODE GENERATED - PROTECTED SECTION - you can safely add code above this comment in this method. #//
        // input transform
        requestquote.RequestQuoteTransformation.availProcessorControlgetAvailObject obj = transformations.availProcessorControlgetAvail(this.iter_forEach1);
        // method call
        availProcessorControl.getAvail(obj.itemID_arg, obj.quantity_arg);
        // output transform
        // output assignments
        // #END  : CODE GENERATED - PROTECTED SECTION - you can safely add code below this comment in this method. #//
    }

	@org.apache.beehive.controls.api.events.EventHandler(field = "availProcessorControl", eventSet = requestquote.services.AvailProcessorControl.Callback.class, eventName = "avail")
	public void availProcessorControl_avail(int itemID_arg, int qty_arg, boolean avail_arg, java.lang.String date_arg) {
        // #START: CODE GENERATED - PROTECTED SECTION - you can safely add code above this comment in this method. #//
        // input transform
        org.example.avail.AvailRequestDocument obj = transformations.availProcessorControl_avail(itemID_arg, qty_arg, avail_arg, date_arg);
        // parameter assignment
        this.avail = obj;
        // #END  : CODE GENERATED - PROTECTED SECTION - you can safely add code below this comment in this method. #//
    }

	public void priceAvailTransformationsConvertAvailXMLtoXMLObj() throws Exception {
        // #START: CODE GENERATED - PROTECTED SECTION - you can safely add code above this comment in this method. #//
        // input transform
        // return method call
        org.apache.xmlbeans.XmlObject objReturn = priceAvailTransformations.convertAvailXMLtoXMLObj(this.avail);
        // output transform
        // output assignments
        this.availList.add(objReturn);
        // #END  : CODE GENERATED - PROTECTED SECTION - you can safely add code below this comment in this method. #//
    }

	public void priceAvailTransformationsConvertPriceListToXML() throws Exception {
        // #START: CODE GENERATED - PROTECTED SECTION - you can safely add code above this comment in this method. #//
        // input transform
        // return method call
        this.priceQuote1 = priceAvailTransformations.convertPriceListToXML(this.requestXML, this.priceList);
        // output transform
        // output assignments
        // #END  : CODE GENERATED - PROTECTED SECTION - you can safely add code below this comment in this method. #//
    }

	public void priceAvailTransformationsConvertAvailListToXML() throws Exception {
        // #START: CODE GENERATED - PROTECTED SECTION - you can safely add code above this comment in this method. #//
        // input transform
        // return method call
        this.availQuote = priceAvailTransformations.convertAvailListToXML(this.availList);
        // output transform
        // output assignments
        // #END  : CODE GENERATED - PROTECTED SECTION - you can safely add code below this comment in this method. #//
    }

	public void tutorialJoinJoin() throws Exception {
        // #START: CODE GENERATED - PROTECTED SECTION - you can safely add code above this comment in this method. #//
        // input transform
        // return method call
        this.Quote = tutorialJoin.join(this.priceQuote1, this.availQuote, this.taxRate);
        // output transform
        // output assignments
        // #END  : CODE GENERATED - PROTECTED SECTION - you can safely add code below this comment in this method. #//
    }

	public void clientResponseCallbackHandler() {
        // #START: CODE GENERATED - PROTECTED SECTION - you can safely add code above this comment in this method. #//
        // input transform
        // method call
        callback.quoteResponse(this.Quote);
        // output transform
        // output assignments
        // #END  : CODE GENERATED - PROTECTED SECTION - you can safely add code below this comment in this method. #//
    }

	public void myFileQuoteWrite() throws Exception {
        // #START: CODE GENERATED - PROTECTED SECTION - you can safely add code above this comment in this method. #//
        // input transform
        // return method call
        this.fileProperties = myFileQuote.write(this.Quote);
        // output transform
        // output assignments
        // #END  : CODE GENERATED - PROTECTED SECTION - you can safely add code below this comment in this method. #//
    }

	@com.bea.wli.jpd.CallbackInterface
	public interface Callback extends com.bea.control.ServiceBrokerControl {
		public void quoteResponse(org.example.quote.QuoteDocument responseXML);
	}
















}
