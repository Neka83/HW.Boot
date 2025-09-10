package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.*;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {

    private final Map<UUID, Product> productStorage;
    private final Map<UUID, Article> articleStorage;

    public StorageService() {
        this.productStorage = new HashMap<>();
        this.articleStorage = new HashMap<>();
        initTestData();
    }

    public Collection<Product> getAllProducts() {
        return new ArrayList<>(productStorage.values());
    }

    public Collection<Article> getAllArticles() {
        return new ArrayList<>(articleStorage.values());
    }

    // Метод для поиска всех объектов как Searchable
    public Collection<Searchable> getAllSearchables() {
        List<Searchable> all = new ArrayList<>();
        all.addAll(productStorage.values());
        all.addAll(articleStorage.values());
        return all;
    }

    private void initTestData() {
        // Статьи
        Article article1 = new Article(UUID.randomUUID(), "Как готовить пасту дома");
        Article article2 = new Article(UUID.randomUUID(), "10 советов по экономии бюджета");
        Article article3 = new Article(UUID.randomUUID(), "История кофе");

        articleStorage.put(article1.getId(), article1);
        articleStorage.put(article2.getId(), article2);
        articleStorage.put(article3.getId(), article3);

        // Продукты
        Product apple = new SimpleProduct(UUID.randomUUID(), "Яблоко", 100);
        Product banana = new SimpleProduct(UUID.randomUUID(), "Банан", 120);
        Product milk = new SimpleProduct(UUID.randomUUID(), "Молоко", 80);
        Product bread = new SimpleProduct(UUID.randomUUID(), "Хлеб", 50);
        Product cheese = new SimpleProduct(UUID.randomUUID(), "Сыр", 200);
        Product chocolate = new DiscountedProduct(UUID.randomUUID(), "Шоколад", 150, 20);
        Product soda = new FixPriceProduct(UUID.randomUUID(), "Газировка");

        productStorage.put(apple.getId(), apple);
        productStorage.put(banana.getId(), banana);
        productStorage.put(milk.getId(), milk);
        productStorage.put(bread.getId(), bread);
        productStorage.put(cheese.getId(), cheese);
        productStorage.put(chocolate.getId(), chocolate);
        productStorage.put(soda.getId(), soda);
    }

}