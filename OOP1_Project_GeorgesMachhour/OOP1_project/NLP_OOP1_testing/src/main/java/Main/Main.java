package Main;

import Document_Reader.*;
import NLP_Analyzer.SentimentAnalysis;
import MongoDB.Mongo;
import MongoDB.Review;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the path of the file (Enter `type` for Typing): ");
            String input = scanner.nextLine().trim().toLowerCase();

            Mongo mongo = new Mongo();
            SentimentAnalysis sentimentAnalysis = new SentimentAnalysis();
            Review review = null;

            if (input.equals("type")) {
                review = handleTypedInput(scanner, mongo, sentimentAnalysis);
            } else {
                review = handleFileInput(input, mongo, sentimentAnalysis);
            }

            if (review != null) {
                System.out.println("Final Sentiment: " + review.getSentiment());
            } else {
                System.out.println("Operation could not be completed.");
            }
        }
    }

    private static Review handleTypedInput(Scanner scanner, Mongo mongo, SentimentAnalysis sentimentAnalysis) {
        System.out.print("Enter the text: ");
        String typedText = scanner.nextLine().trim();

        Review review = mongo.getReviewByReview(typedText);
        if (review == null) {
            System.out.println("Review not found in the database. Analyzing the text...");
            sentimentAnalysis.setText(typedText);
            sentimentAnalysis.analyzeText();
            review = new Review("typed_input", typedText, sentimentAnalysis.GetSentiment());
            mongo.createReview(review.getPath(), review.getReview(), review.getSentiment());
        } else {
            System.out.println("Review found in the database.");
        }
        return review;
    }

    private static Review handleFileInput(String filePath, Mongo mongo, SentimentAnalysis sentimentAnalysis) {
        FileRead fileReader = getFileReader(filePath);
        if (fileReader == null) {
            System.out.println("This file type is not supported YET.");
            return null;
        }

        fileReader.setPath(filePath);
        System.out.println("Reading the file...");
        fileReader.readFromFile();

        String extractedText = fileReader.getExtractedText();
        if (extractedText == null || extractedText.isEmpty()) {
            System.out.println("Error reading the file. It may not exist, be corrupted, or be empty.");
            return null;
        }

        System.out.println("File read successfully.");
        System.out.println("Extracted text: " + extractedText);

        Review review = mongo.getReviewByPath(filePath);
        if (review == null) {
            System.out.println("Review not found in the database. Analyzing the text...");
            sentimentAnalysis.setText(extractedText);
            sentimentAnalysis.analyzeText();
            review = new Review(filePath, extractedText, sentimentAnalysis.GetSentiment());
            try {
                mongo.createReview(filePath, extractedText, review.getSentiment());
            } catch (Exception e) {
                System.out.println("Error creating review in the database.");
            }
        } else {
            System.out.println("Review found in the database.");
        }
        return review;
    }

    private static FileRead getFileReader(String filePath) {
        if (filePath.endsWith(".docx")) {
            return new MSWordReader();
        } else if (filePath.endsWith(".html")) {
            return new HTMLReader();
        } else if (filePath.endsWith(".xml")) {
            return new XMLReader();
        } else if (filePath.endsWith(".json")) {
            return new JSONReader();
        } else if (filePath.endsWith(".pdf")) {
            return new PdfReader();
        }
        return null;
    }
}
