package Document_Reader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLReader implements FileRead {
    private String path;
    private String text;
    private final String tag = "review";

    public XMLReader(){

    }

    public void setPath(String path){
        this.path = path;
    };

    public String getPath(){
        return this.path;
    };

    public String getExtractedText(){
        return this.text;
    };

    public void readFromFile(){
        try {
            File xmlFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            // Optional: Normalize the XML document
            doc.getDocumentElement().normalize();

            // Example: Read elements by tag name
            NodeList nodeList = doc.getElementsByTagName(tag);
            this.text = "";
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("Element: " + element.getTagName());
                    this.text += element.getTextContent() + " ";
                }
            }
        } catch (Exception e) {
            this.text = null;
        }
    }
}
