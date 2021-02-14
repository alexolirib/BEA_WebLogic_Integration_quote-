package requestquote;

import com.bea.control.FileControl;


import org.apache.beehive.controls.api.bean.ControlExtension;

import com.bea.wli.control.dynamicProperties.FileControlPropertiesDocument;
import org.apache.xmlbeans.XmlObject;

/*
 * A custom File control.  
 */
@ControlExtension
@FileControl.FileInfo(directoryName = "C:/Users/Alexandre Ribeiro/Documents/projeto/BEA/file/", fileMask = "quote.xml")
public interface MyFileQuote extends com.bea.control.FileControl {

	@FileControl.IOOperation(ioType = FileIOType.WRITE, fileContent = "{someData}")
	FileControlPropertiesDocument write(XmlObject someData);

	@FileControl.IOOperation(ioType = FileIOType.READ)
	XmlObject read();

	static final long serialVersionUID = 1L;

	public MyFileQuote create();
}