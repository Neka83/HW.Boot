package org.skypro.skyshop.model.product;

import java.util.UUID;

/**
 * Продукт с базовой ценой и скидкой
 */
public class DiscountedProduct extends Product {

    private final int basePrice;
    private final int discount; // в процентах

    public DiscountedProduct(UUID id, String name, int basePrice, int discount) {
        super(id, name);
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Базовая цена должна быть положительной");
        }
        if (discount < 0 || discount > 90) {
            throw new IllegalArgumentException("Скидка должна быть от 0 до 90%");
        }
        this.basePrice = basePrice;
        this.discount = discount;
    }

    @Override
    public int getPrice() {
        return basePrice * (100 - discount) / 100;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return getName() + ": " + getPrice() + " (скидка " + discount + "%)";
    }
}