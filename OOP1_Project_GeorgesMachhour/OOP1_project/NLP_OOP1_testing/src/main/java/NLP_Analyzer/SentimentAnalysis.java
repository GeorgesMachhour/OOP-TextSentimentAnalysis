package NLP_Analyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

public class SentimentAnalysis implements Analyse {
    private String text;
    private String getSentiment;

    public String GetSentiment() {
        return getSentiment;
    }

    public SentimentAnalysis() {
        this.text = "";
    }

    public SentimentAnalysis(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private List<String> sentenceDivider(String text) {
        // Improved sentence splitting using more robust regex
        return splitSentences(text);
    }

    private List<String> splitSentences(String text) {
        List<String> sentences = new ArrayList<>();
        // More sophisticated sentence splitting
        String[] potentialSentences = text.split("(?<=[.!?])\\s+");
        for (String sentence : potentialSentences) {
            if (!sentence.trim().isEmpty()) {
                sentences.add(sentence.trim());
            }
        }
        return sentences;
    }

    private static String mapSentimentToDescription(String sentiment) {
        switch (sentiment) {
            case "Very Negative": return "Very Negative";
            case "Negative": return "Negative";
            case "Neutral": return "Neutral";
            case "Positive": return "Positive";
            case "Very Positive": return "Very Positive";
            default: return "Neutral";
        }
    }

    private SentimentResult analyzeSentiment(String text) {
        // Create a new Properties object to hold the pipeline configuration
        Properties props = new Properties();
        // Set the annotators property to specify the NLP tasks to be performed
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,parse,sentiment");
        // Create a new StanfordCoreNLP pipeline with the specified properties
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // Create a new Annotation object for the input text
        Annotation document = new Annotation(text);
        // Annotate the document using the pipeline
        pipeline.annotate(document);

        // Get the list of sentences from the annotated document
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        // If no sentences are found, return a neutral sentiment result
        if (sentences.isEmpty()) {
            return new SentimentResult(3, "Neutral");
        }

        // Get the sentiment of the first sentence
        CoreMap firstSentence = sentences.get(0);
        String sentiment = firstSentence.get(SentimentCoreAnnotations.SentimentClass.class);
        // Map the sentiment to a description
        String mappedSentiment = mapSentimentToDescription(sentiment);

        // Calculate the sentiment score based on the mapped sentiment
        int score = calculateSentimentScore(mappedSentiment);
        // Return a new SentimentResult object with the score and mapped sentiment
        return new SentimentResult(score, mappedSentiment);
    }

    private int calculateSentimentScore(String sentiment) {
        switch (sentiment) {
            case "Very Negative": return 1;
            case "Negative": return 2;
            case "Neutral": return 3;
            case "Positive": return 4;
            case "Very Positive": return 5;
            default: return 3; //neutral
        }
    }

    private double CalculateAvg(int totalScore, int count) {
        return count > 0 ? (double) totalScore / count : 3;
    }

    @Override
    public void analyzeText() {
        // Check if the text is null or empty
        if (text == null || text.trim().isEmpty()) {
            System.out.println("No text to analyze");
            return;
        }

        // Divide the text into sentences
        List<String> sentences = sentenceDivider(text);
        int totalScore = 0;

        // Analyze each sentence and accumulate the total score
        for (String sentence : sentences) {
            SentimentResult result = analyzeSentiment(sentence);
            totalScore += result.getScore();
        }

        // Calculate the average sentiment score
        double averageScore = CalculateAvg(totalScore, sentences.size());

        // Perform final sentiment analysis on the entire text
        SentimentResult overallSentiment = analyzeSentiment(text);

        // Set the overall sentiment result
        this.getSentiment = "Overall Sentiment: " + overallSentiment.getSentiment() +"\n"+
                "Overall Sentiment Score: " + overallSentiment.getScore() +"\n"+
                "Average Sentiment Score: " + averageScore;
    }

    // Inner class to encapsulate sentiment result
    private static class SentimentResult {
        private final int score;
        private final String sentiment;

        public SentimentResult(int score, String sentiment) {
            this.score = score;
            this.sentiment = sentiment;
        }

        public int getScore() {
            return score;
        }

        public String getSentiment() {
            return sentiment;
        }
    }
}
