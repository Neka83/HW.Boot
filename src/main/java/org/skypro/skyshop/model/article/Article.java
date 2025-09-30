package org.skypro.skyshop.model.article;

import org.skypro.skyshop.model.search.Searchable;

import java.util.UUID;

public class Article implements Searchable {

    private final UUID id;
    private final String title;

    public Article(UUID id, String title) {
        this.id = id;
        this.title = title;
    }

    public UUID getId() { return id; }
    public String getName() { return title; }
    public String getContentType() { return "ARTICLE"; }
}