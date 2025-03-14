package org.generationitaly.progetto.progetto.dto;

public class SearchDTO {
    private String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return "SearchDTO [query=" + query + "]";
    }
}
