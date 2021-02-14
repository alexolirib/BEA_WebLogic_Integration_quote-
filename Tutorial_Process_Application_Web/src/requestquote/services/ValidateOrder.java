package requestquote.services; 

import org.apache.beehive.controls.api.bean.Control;
import com.bea.control.ServiceBrokerControl;
import com.bea.jpd.JpdContext;
import com.bea.wli.control.broker.MessageBroker;
import com.bea.wli.jpd.CallbackInterface;
import com.bea.wli.jpd.Process;

@Process(process = 
"<process name=\"ValidateOrder\">" + 
"  <clientRequest name=\"Subscribe to ValidateOrder MB Channel\" method=\"subscription\">" + 
"    <description>This business process is started via subscription to a Message Broker channel (ValidateOrder). That is, this business process starts when a mesage is published to the channel.</description>" + 
"  </clientRequest>" + 
"  <forEach name=\"For Each Widget\" variable=\"iter_requestXML1\" expression=\"get_requestXML1($requestXML)\">" + 
"    <description>Loop through each widget requested in the Request For Quote document.</description>" + 
"    <decision name=\"Valid Order?\">" + 
"      <if name=\"Yes\" condition=\"cond_iter_requestXML1_1($iter_requestXML1)\">" + 
"        <description>Base decision about whether this is a valid order on the number of widgets requested by the client. If &lt;=400, order is valid. If &gt;400, order is invalid.</description>" + 
"      </if>" + 
"      <default name=\"No\">" + 
"        <controlSend name=\"Publish Deny Quote\" method=\"mbPublishStopMsgPublish\">" + 
"          <description>If order is invalid, publish a message to the StopQuote MB channel. Message is String which describes reason why quote is denied.</description>" + 
"        </controlSend>" + 
"        <finish name=\"Finish\">" + 
"          <description>If quote is invalid and a &apos;deny quote&apos; message has been sent, this business process is finished.</description>" + 
"        </finish>" + 
"      </default>" + 
"    </decision>" + 
"  </forEach>" + 
"</process>")
@com.bea.wli.common.XQuery(prolog = "" +
                                    "declare namespace ns0=\"http://www.example.org/request\"; " +
                                    "" +
                                    "" +
                                    " declare function get_requestXML1($requestXML as element()) as element()* {" +
                                    "      " +
                                    "      " +
                                    "      $requestXML/ns0:widgetRequest" +
                                    " };" +
                                    "\n" +
                                    " declare function cond_iter_requestXML1_1($iter_requestXML1 as element()) as  xs:boolean  {" +
                                    "      data($iter_requestXML1/ns0:quantity) <= 400" +
                                    "};" +
                                    " " +
                                    "declare function subscription_filter($message as element()) as  xs:string  {" +
                                    "      " +
                                    "      " +
                                    "      " +
                                    "      data($message)" +
                                    "};",
                           version = com.bea.wli.common.XQuery.Version.v2004)
public class ValidateOrder implements com.bea.jpd.ProcessDefinition
{
    public java.lang.String ffffff;

	static final long serialVersionUID = 1L;
    
    public static java.lang.String stopQuote= "Request for Quote Rejected. Reason: Cannot process quote for orders of more than 400 widgets.";

    public org.example.request.WidgetRequestDocument iter_requestXML1;

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

    @Control()
    private requestquote.services.validatePublish validatePublish;

    @com.bea.wli.jpd.Context()
    JpdContext context; //(..control insertion marker - do not modify this line)
    
   
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




    public void mbPublishStopMsgPublish() throws Exception
    {
        //#START: CODE GENERATED - PROTECTED SECTION - you can safely add code above this comment in this method. #//
        // input transform
        // method call
        validatePublish.publish(this.stopQuote);
        // output transform
        // output assignments
        //#END  : CODE GENERATED - PROTECTED SECTION - you can safely add code below this comment in this method. #//
    }

    @MessageBroker.StaticSubscription(xquery = "", filterValueMatch = "", channelName = "/TutorialPrefix/Tutorial/ValidateOrder", messageBody = "{x0}")
    public void subscription(org.example.request.QuoteRequestDocument x0)
    {
        // #START: CODE GENERATED - PROTECTED SECTION - you can safely add code above this comment in this method. #//
        // input transform
        // parameter assignment
        this.requestXML = x0;
        // #END  : CODE GENERATED - PROTECTED SECTION - you can safely add code below this comment in this method. #//
    }


    @CallbackInterface()
    public interface Callback extends ServiceBrokerControl //(..region end marker - do not modify this line)
    {
    }
    
    /*
     * Code inserted below this comment is for methods corresponding to Perform or Condition nodes
     * created in the Design View.
     *
     * Feel free to make modifications or add new code here.
     */
    
} 
