package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.UUID;

public class Article implements Searchable {
    private final UUID id;
    private final String title;

    public Article(UUID id, String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Название статьи не может быть пустым");
        }
        this.id = id;
        this.title = title;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    @JsonIgnore
    public String getSearchTerm() {
        return getTitle();
    }

    @Override
    @JsonIgnore
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public String toString() {
        return title;
    }
}