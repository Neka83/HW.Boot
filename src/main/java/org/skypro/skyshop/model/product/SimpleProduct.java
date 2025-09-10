package org.skypro.skyshop.model.product;

import java.util.UUID;

/**
 * Простой продукт с фиксированной ценой
 */
public class SimpleProduct extends Product {

    private final int price;

    public SimpleProduct(UUID id, String name, int price) {
        super(id, name);
        if (price <= 0) {
            throw new IllegalArgumentException("Цена должна быть положительной");
        }
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }
}