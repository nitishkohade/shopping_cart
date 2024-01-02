package com.myshoppingcart.shoppingcart.models;

import com.myshoppingcart.shoppingcart.enums.CartEventType;

import java.time.LocalDateTime;

public class CartEvent {
    private CartEventType type;
    private Integer cartId;
    private Integer itemId;
    private Integer quantity;
    private LocalDateTime dateTime;

    public CartEvent(){}

    public CartEvent(CartEventType type, Integer cartId, Integer itemId, Integer quantity) {
        this.type = type;
        this.cartId = cartId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.dateTime = LocalDateTime.now();
    }

    public CartEventType getType() {
        return type;
    }

    public void setType(CartEventType type) {
        this.type = type;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "CartEvent{" +
                "type=" + type +
                ", cartId=" + cartId +
                ", itemId=" + itemId +
                ", quantity=" + quantity +
                ", dateTime=" + dateTime +
                '}';
    }
}
