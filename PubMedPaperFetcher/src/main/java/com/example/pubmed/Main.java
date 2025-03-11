package com.example.pubmed;

import java.util.List;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        // Check if there is a query parameter provided
        if (args.length < 1) {
            System.out.println("Usage: java com.example.pubmed.Main <query> [output-file]");
            System.out.println("Example: java com.example.pubmed.Main 'cancer research' output.csv");
            return;
        }

        // Extract the query and output file from arguments
        String query = args[0];
        String outputFile = args.length > 1 ? args[1] : "output.csv";

        // Check if the output file already exists
        File file = new File(outputFile);
        if (file.exists()) {
            System.out.println("Warning: The file " + outputFile + " already exists.");
            System.out.print("Do you want to overwrite it? (y/n): ");
            try {
                // Take user input for overwriting file
                char choice = (char) System.in.read();
                if (choice != 'y' && choice != 'Y') {
                    System.out.println("Operation canceled.");
                    return;
                }
            } catch (Exception e) {
                System.out.println("Error reading input: " + e.getMessage());
                return;
            }
        }

        try {
            // Fetch papers from PubMed API
            PaperFetcher fetcher = new PaperFetcher();
            List<Paper> papers = fetcher.fetchPapers(query);

            // If no papers were found, exit early
            if (papers.isEmpty()) {
                System.out.println("No papers found for the query: " + query);
                return;
            }

            // Write the results to the CSV file
            CSVWriter writer = new CSVWriter();
            writer.writeToCSV(papers, outputFile);

            // Confirmation message
            System.out.println("Results written to: " + outputFile);
        } catch (Exception e) {
            // Print detailed error information
            System.err.println("An error occurred while processing the request: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
