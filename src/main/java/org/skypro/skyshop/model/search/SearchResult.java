package org.skypro.skyshop.model.search;

public class SearchResult {

    private final String id;
    private final String name;
    private final String contentType;

    private SearchResult(String id, String name, String contentType) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
    }

    public static SearchResult fromSearchable(Searchable item) {
        return new SearchResult(item.getId().toString(), item.getName(), item.getContentType());
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getContentType() { return contentType; }
}