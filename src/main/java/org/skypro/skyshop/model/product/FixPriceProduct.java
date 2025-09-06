package org.skypro.skyshop.model.product;

import java.util.UUID;

/**
 * Продукт с фиксированной ценой, который считается "специальным"
 */
public class FixPriceProduct extends Product {

    private final int fixPrice;

    public FixPriceProduct(UUID id, String name, int fixPrice) {
        super(id, name);
        if (fixPrice <= 0) {
            throw new IllegalArgumentException("Цена должна быть положительной");
        }
        this.fixPrice = fixPrice;
    }

    @Override
    public int getPrice() {
        return fixPrice;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}