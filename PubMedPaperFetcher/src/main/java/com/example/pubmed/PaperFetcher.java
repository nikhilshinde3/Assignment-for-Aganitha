package com.example.pubmed;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URLEncoder;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class PaperFetcher {

    private static final String PUBMED_API_URL = "https://api.ncbi.nlm.nih.gov/lit/ctxp/v1/pubmed/?format=json&query=";

    public List<Paper> fetchPapers(String query) throws Exception {
        // URL-encode the query to handle spaces and special characters
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        String requestUrl = PUBMED_API_URL + encodedQuery;

        System.out.println("Fetching from URL: " + requestUrl); // Debug info

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(requestUrl))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Debug: Print API response
        System.out.println("API Response: " + response.body());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.body());

        // Null check for the "papers" field
        if (root == null || !root.has("papers")) {
            System.out.println("No 'papers' field found in the response.");
            return new ArrayList<>();
        }

        JsonNode papersNode = root.get("papers");

        List<Paper> papers = new ArrayList<>();
        for (JsonNode paperNode : papersNode) {
            Paper paper = new Paper();
            paper.setPubmedId(getSafeText(paperNode, "pmid"));
            paper.setTitle(getSafeText(paperNode, "title"));
            paper.setPublicationDate(getSafeText(paperNode, "pubdate"));
            paper.setNonAcademicAuthors("Placeholder"); // Add real parsing logic later
            paper.setCompanyAffiliations("Placeholder"); // Add real parsing logic later
            paper.setCorrespondingAuthorEmail("Placeholder"); // Add real parsing logic later

            papers.add(paper);
        }

        if (papers.isEmpty()) {
            System.out.println("No papers found for the query.");
        }

        return papers;
    }

    // Helper method to safely get text fields and avoid null pointers
    private String getSafeText(JsonNode node, String fieldName) {
        return node.has(fieldName) && !node.get(fieldName).isNull() ? node.get(fieldName).asText() : "N/A";
    }
}
