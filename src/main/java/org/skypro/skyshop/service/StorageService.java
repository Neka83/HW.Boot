package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.*;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {

    private final Map<UUID, Product> productStorage = new HashMap<>();
    private final Map<UUID, Article> articleStorage = new HashMap<>();

    public StorageService() { initTestData(); }

    public Collection<Product> getAllProducts() { return productStorage.values(); }
    public Collection<Article> getAllArticles() { return articleStorage.values(); }

    public Collection<Searchable> getAllSearchables() {
        List<Searchable> all = new ArrayList<>();
        all.addAll(productStorage.values());
        all.addAll(articleStorage.values());
        return all;
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(productStorage.get(id));
    }

    private void initTestData() {
        Article a1 = new Article(UUID.randomUUID(), "Как готовить пасту дома");
        Article a2 = new Article(UUID.randomUUID(), "10 советов по экономии бюджета");
        Article a3 = new Article(UUID.randomUUID(), "История кофе");

        articleStorage.put(a1.getId(), a1);
        articleStorage.put(a2.getId(), a2);
        articleStorage.put(a3.getId(), a3);

        Product p1 = new SimpleProduct(UUID.randomUUID(), "Яблоко", 100);
        Product p2 = new SimpleProduct(UUID.randomUUID(), "Банан", 120);
        Product p3 = new SimpleProduct(UUID.randomUUID(), "Молоко", 80);
        Product p4 = new SimpleProduct(UUID.randomUUID(), "Хлеб", 50);
        Product p5 = new SimpleProduct(UUID.randomUUID(), "Сыр", 200);
        Product p6 = new DiscountedProduct(UUID.randomUUID(), "Шоколад", 150, 20);
        Product p7 = new FixPriceProduct(UUID.randomUUID(), "Газировка");

        productStorage.put(p1.getId(), p1);
        productStorage.put(p2.getId(), p2);
        productStorage.put(p3.getId(), p3);
        productStorage.put(p4.getId(), p4);
        productStorage.put(p5.getId(), p5);
        productStorage.put(p6.getId(), p6);
        productStorage.put(p7.getId(), p7);
    }
}