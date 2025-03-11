package com.example.pubmed;

public class Paper {
    private String pubmedId;
    private String title;
    private String publicationDate;
    private String nonAcademicAuthors;
    private String companyAffiliations;
    private String correspondingAuthorEmail;

    // Getters and Setters
    public String getPubmedId() { return pubmedId; }
    public void setPubmedId(String pubmedId) { this.pubmedId = pubmedId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getPublicationDate() { return publicationDate; }
    public void setPublicationDate(String publicationDate) { this.publicationDate = publicationDate; }

    public String getNonAcademicAuthors() { return nonAcademicAuthors; }
    public void setNonAcademicAuthors(String nonAcademicAuthors) { this.nonAcademicAuthors = nonAcademicAuthors; }

    public String getCompanyAffiliations() { return companyAffiliations; }
    public void setCompanyAffiliations(String companyAffiliations) { this.companyAffiliations = companyAffiliations; }

    public String getCorrespondingAuthorEmail() { return correspondingAuthorEmail; }
    public void setCorrespondingAuthorEmail(String correspondingAuthorEmail) { this.correspondingAuthorEmail = correspondingAuthorEmail; }
}
