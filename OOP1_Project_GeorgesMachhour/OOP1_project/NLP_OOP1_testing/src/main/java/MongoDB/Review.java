package MongoDB;

public class Review {
    private String path;
    private String review;
    private String sentiment;

    public Review(String path, String review, String sentiment) {
        this.path = path;
        this.review = review;
        this.sentiment = sentiment;
    }

    public Review() {
        this.path = null;
        this.review = null;
        this.sentiment = null;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }


    @Override
    public String toString() {
        return "Review{" +
                "path='" + path + '\'' +
                ", review='" + review + '\'' +
                ", sentiment='" + sentiment + '\'' +
                '}';
    }
}
