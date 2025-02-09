package Document_Reader;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLReader implements FileRead{
    private String path;
    private String text;
    private final String tag = "p";

    public HTMLReader(){

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

    public void readFromFile() {
        try {
            File input = new File(path);
            Document doc = Jsoup.parse(input, "UTF-8", "");
            Elements elements = doc.getElementsByTag(tag);
            this.text = "";
            for (Element element : elements) {
                this.text += element.text();
            }
        } catch (IOException e) {
            this.text = null;
        }
    }
}