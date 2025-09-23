package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;
import org.skypro.skyshop.exception.NoSuchProductException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {

    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProductToBasket(UUID id) {
        Product product = storageService.getProductById(id)
                .orElseThrow(() -> new NoSuchProductException("Продукт с id " + id + " не найден"));
        productBasket.addProduct(id);
    }

    public UserBasket getUserBasket() {
        List<BasketItem> items = productBasket.getProducts().entrySet().stream()
                .map(e -> new BasketItem(
                        storageService.getProductById(e.getKey())
                                .orElseThrow(() -> new IllegalStateException("Товар не найден")),
                        e.getValue()))
                .collect(Collectors.toList());

        return new UserBasket(items);
    }
}