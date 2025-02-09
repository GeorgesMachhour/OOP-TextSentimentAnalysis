package Document_Reader;

public interface FileRead {
    public void readFromFile();
    public void setPath(String path);
    public String getPath();
    public String getExtractedText();
}
