package com.example.pubmed;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter {

    public void writeToCSV(List<Paper> papers, String filename) throws IOException {
        try (FileWriter writer = new FileWriter(filename);
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                     .withHeader("PubmedID", "Title", "Publication Date", "Non-academic Author(s)", "Company Affiliation(s)", "Corresponding Author Email"))) {

            for (Paper paper : papers) {
                csvPrinter.printRecord(paper.getPubmedId(), paper.getTitle(), paper.getPublicationDate(),
                        paper.getNonAcademicAuthors(), paper.getCompanyAffiliations(), paper.getCorrespondingAuthorEmail());
            }
        }
    }
}
