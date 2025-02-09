# OOP-TextSentimentAnalysis
# Sentiment Analysis in Java

## Overview
This project is an academic assignment for the Object-Oriented Programming 1 (OOP1) course. It focuses on sentiment analysis using Natural Language Processing (NLP) techniques in Java. The application processes and analyzes textual data from various document formats to determine sentiment (positive, negative, or neutral) based on user queries.

## Features
- Command-line interface for user input
- Sentiment analysis using NLP techniques
- Supports multiple document formats (Word, PDF, XML, JSON, etc.)
- Retrieves relevant documents based on keyword/topic queries
- Stores query history in MongoDB for optimized searches
- CRUD operations for managing stored queries

## Technologies Used
- Java
- Object-Oriented Programming (OOP) principles
- Natural Language Processing (NLP)
- MongoDB (for query storage and retrieval)

## Installation
1. Clone this repository:
   ```sh
   git clone https://github.com/yourusername/OOP1_SentimentAnalysis.git
   ```
2. Open the project in an IDE (e.g., IntelliJ IDEA, Eclipse, or VS Code with Java support).
3. Ensure you have Java (JDK 11 or later) installed.
4. Install required dependencies (if any external libraries are used for NLP or database handling).
5. Set up MongoDB (local or remote) and update the database connection details in the code.

## Usage
1. Run the Java program from the command line or IDE.
2. Enter a query containing keywords, a topic, or a question.
3. The program will:
   - Search for relevant documents.
   - Perform sentiment analysis on the retrieved text.
   - Display the sentiment result (positive, negative, or neutral).
4. If MongoDB is enabled, previously processed queries will be retrieved instantly.

## Project Structure
```
OOP1_project_GeorgesMachhour/
├── .idea/                  # Project settings (IDE-specific)
├── JavaDoc/                # Auto-generated Java documentation
├── OOP1_project/           # Main project source code
│   ├── NLP_OOP1_testing/   # Java source files
├── Report/                 # Project report with execution steps
```

## Future Improvements
- Implement a graphical user interface (GUI) for a better user experience.
- Enhance NLP processing with advanced models.
- Improve query handling and document retrieval efficiency.

## Author
Georges Machhour

## License
This project is for academic purposes and may not be used commercially without permission.

