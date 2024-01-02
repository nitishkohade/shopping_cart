package com.myshoppingcart.shoppingcart.models;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {
    private Integer sku;
    private String displayName;
    private BigDecimal price;

    public Item(){}

    public Item(String displayName, BigDecimal price) {
        this.displayName = displayName;
        this.price = price;
    }

    public Integer getSku() {
        return sku;
    }

    public void setSku(Integer sku) {
        this.sku = sku;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item item)) return false;
        return Objects.equals(getSku(), item.getSku());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSku());
    }

    @Override
    public String toString() {
        return "Item{" +
                "sku=" + sku +
                ", displayName='" + displayName + '\'' +
                ", price=" + price +
                '}';
    }
}