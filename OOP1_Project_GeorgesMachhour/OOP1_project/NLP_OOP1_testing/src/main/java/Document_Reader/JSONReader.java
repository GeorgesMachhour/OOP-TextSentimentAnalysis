package Document_Reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;


public class JSONReader implements FileRead {
    private String path;
    private String text;


    public JSONReader() {
        text="";
    }

    @Override
    public void readFromFile() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Reading JSON to Map
            Map<String, Object> jsonMap = objectMapper.readValue(
                    new File(path),
                    Map.class
            );

            // Reading JSON to Custom Object
            Review rev = objectMapper.readValue(
                    new File(path),
                    Review.class
            );

            text = rev.getReview();

        } catch (IOException e) {
            text = null;
        }
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    public String getExtractedText() {
        return this.text;
    }

    private static class Review{
        private String review;

        public String getReview() {
            return review;
        }
    }
}
