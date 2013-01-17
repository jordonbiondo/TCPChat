import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.OutputKeys;
import java.io.StringWriter;
 
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/*
<?xml version="1.0" encoding="UTF-8"?>
<MESSAGE>
  <FROM>user uuid</FROM>
  <TYPE> text or message </TYPE>
  <TO> user or all or group </TO>
  <ACTION> block nameChange </ACTION>
  <TEXT>message text</TEXT>
  <ID>message uuid</ID>
</MESSAGE>
*/

class MessageType {
    public final static String text = "text";
    public final static String action = "action";
}
public class MessageMaker {
    

    public static String textMessage(ChatClient from, String to, String text) {
	DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder docBuilder = null;
	try {
	    docBuilder = docFactory.newDocumentBuilder();
	} catch(ParserConfigurationException pce) {
	    pce.printStackTrace();
	}
	
	// root elements
	Document doc = docBuilder.newDocument();
	Element msg = doc.createElement("MSG");
	doc.appendChild(rootElement);
	
	// staff elements
	Element type = doc.createElement("TYPE");
	type.appendChild(doc.createTextNode("TXT"));
	rootElement.appendChild(type);
	
	// set attribute to staff element
	//Attr attr = doc.createAttribute("id");
	//attr.setValue("1");
	//staff.setAttributeNode(attr);
	
	// shorten way
	// staff.setAttribute("id", "1");
	
	// firstname elements
	Element to = doc.createElement("TO");
	firstname.appendChild(doc.createTextNode("someuser"));
	msg.appendChild(firstname);
	
	// lastname elements
	Element to = doc.createElement("lastname");
	lastname.appendChild(doc.createTextNode("mook kim"));
	msg.appendChild(lastname);
	
	// nickname elements
	Element nickname = doc.createElement("nickname");
	nickname.appendChild(doc.createTextNode("mkyong"));
	type.appendChild(nickname);
	
	// salary elements
	Element salary = doc.createElement("salary");
	salary.appendChild(doc.createTextNode("100000"));
	type.appendChild(salary);
	try {
	    TransformerFactory tf = TransformerFactory.newInstance();
	    Transformer transformer = tf.newTransformer();
	    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	    StringWriter writer = new StringWriter();
	    transformer.transform(new DOMSource(doc), new StreamResult(writer));
	    String output = writer.getBuffer().toString().replaceAll("\n|\r", "");
	    return output;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return "asf";
    }
    
}