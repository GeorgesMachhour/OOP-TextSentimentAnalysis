package MongoDB;

import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Optional;

public class Mongo {
    private MongoCollection<Document> reviewCollection;
    private final String connectionString = "mongodb://localhost:27017";
    private final String databaseName = "OOP1_Project";
    private final String collectionName = "Reviews";
    private boolean isConnected = true;

    public Mongo() {
        try {
            MongoClient mongoClient = MongoClients.create(connectionString);
            MongoDatabase database = mongoClient.getDatabase(databaseName);
            reviewCollection = database.getCollection(collectionName);
            isConnected = true;
        } catch (Exception e) {
            isConnected = false;
            System.out.println("Could not connect to the database.");
        }
    }

    public void createReview(String path, String review, String sentiment) {
        if (!isConnected) {
            System.out.println("Could not connect to the database.");
            return;
        }

        Document reviewDoc = new Document("path", path)
                .append("review", review)
                .append("sentiment", sentiment);
        reviewCollection.insertOne(reviewDoc);
    }

    public Review getReviewByPath(String path) {
        if (!isConnected) {
            System.out.println("Could not connect to the database.");
            return null;
        }

        if (path == null){
            System.out.println("No path to check for in database");
            return null;
        }

        Document found = reviewCollection.find(new Document("path", path)).first();
        if (found != null) {
            return new Review(
                    found.getString("path"),
                    found.getString("review"),
                    found.getString("sentiment")
            );
        }
        return null;
    }

    public Review getReviewByReview(String review) {

        if (!isConnected) {
            System.out.println("Could not connect to the database.");
            return null;
        }

        if (review == null){
            System.out.println("No reviews to check for in database");
            return null;
        }

        Document found = reviewCollection.find(new Document("review", review)).first();

        if (found != null) {
            return new Review(
                    found.getString("path"),
                    found.getString("review"),
                    found.getString("sentiment")
            );
        }
        return null;
    }
}