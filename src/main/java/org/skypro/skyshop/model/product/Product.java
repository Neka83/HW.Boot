package org.skypro.skyshop.model.product;

import org.skypro.skyshop.model.search.Searchable;

import java.util.UUID;

public abstract class Product implements Searchable {

    private final UUID id;
    private final String name;
    private final int price;

    protected Product(UUID id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public int getPrice() { return price; }
    public String getContentType() { return "PRODUCT"; }
}