package Document_Reader;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class MSWordReader implements FileRead{
    private String path;
    private String text;

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
            FileInputStream fis = new FileInputStream(this.path);
            XWPFDocument document = new XWPFDocument(fis);
            List<XWPFParagraph> paragraphs = document.getParagraphs();
            this.text = "";
            for (XWPFParagraph para : paragraphs) {
                this.text += para.getText();
            }
            fis.close();
        } catch (IOException e) {
            this.text = null;
        }
    };

}