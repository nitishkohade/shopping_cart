package com.myshoppingcart.shoppingcart.models;

public class CartItem {
    private Integer id;
    private Item item;
    private Integer quantity;

    public CartItem(Integer id, Item item, Integer quantity) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", item=" + item +
                ", quantity=" + quantity +
                '}';
    }
}
