package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {

    private final int discount;

    public DiscountedProduct(UUID id, String name, int price, int discount) {
        super(id, name, price);
        this.discount = discount;
    }

    @Override
    public int getPrice() {
        return super.getPrice() - discount;
    }
}