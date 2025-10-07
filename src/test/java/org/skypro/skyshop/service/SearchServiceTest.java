package org.skypro.skyshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearchServiceTest {

    private StorageService storageService;
    private SearchService searchService;

    @BeforeEach
    void setUp() {
        storageService = mock(StorageService.class);
        searchService = new SearchService(storageService);
    }

    @Test
    void searchReturnsEmptyWhenNoObjects() {
        when(storageService.getAllSearchables()).thenReturn(new ArrayList<>());

        Collection<SearchResult> results = searchService.search("test");

        assertTrue(results.isEmpty());
    }

    @Test
    void searchReturnsEmptyWhenNoMatch() {
        Searchable item = mock(Searchable.class);
        when(item.getSearchText()).thenReturn("apple");
        when(storageService.getAllSearchables()).thenReturn(List.of(item));

        Collection<SearchResult> results = searchService.search("banana");

        assertTrue(results.isEmpty());
    }

    @Test
    void searchReturnsMatchingObject() {
        Searchable item = mock(Searchable.class);
        when(item.getSearchText()).thenReturn("apple");
        when(item.getId()).thenReturn(java.util.UUID.randomUUID());
        when(item.getName()).thenReturn("apple");
        when(item.getContentType()).thenReturn("PRODUCT");

        when(storageService.getAllSearchables()).thenReturn(List.of(item));

        Collection<SearchResult> results = searchService.search("app");

        assertEquals(1, results.size());
        assertEquals("PRODUCT", results.iterator().next().getContentType());
    }
}