package requestquote; 

import org.apache.beehive.controls.api.bean.ControlExtension;
import org.apache.xmlbeans.XmlObject;
import com.bea.control.FileControl;
import com.bea.wli.control.dynamicProperties.FileControlPropertiesDocument;

@ControlExtension()
@FileControl.FileInfo(directoryName = ".",
                      fileMask = "quote.xml")
public interface FileQuote extends FileControl 
{ 
 
    @FileControl.IOOperation(ioType = FileIOType.WRITE,
                             fileContent = "{someData}")
    FileControlPropertiesDocument write(XmlObject someData);

    @FileControl.IOOperation(ioType = FileIOType.READ)
    XmlObject read();
}
