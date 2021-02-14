package requestquote.services; 

import com.bea.control.PublishControl; 
import org.apache.beehive.controls.api.bean.ControlExtension;
import org.apache.xmlbeans.XmlObject; 
 
@ControlExtension()
@PublishControl.ClassPublish(channelName = "/TutorialPrefix/Tutorial/StopQuote")
public interface validatePublish extends PublishControl 
{  
    @PublishControl.MethodPublish(body = "{value}")
    void publish(java.lang.String value); 

    @PublishControl.MethodPublish(metadata = "{metadata}",
                                  body = "{value}")
    void publishWithMetadata(XmlObject metadata, java.lang.String value); 
} 
