package Document_Reader;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class PdfReader implements FileRead {
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

        try (PDDocument document = PDDocument.load(new File(path))) {
            // Create a PDFTextStripper to extract text
            PDFTextStripper pdfStripper = new PDFTextStripper();

            // Extract text from the entire document
            this.text = pdfStripper.getText(document);

        } catch (IOException e) {
            this.text = null;
        }
    }
}