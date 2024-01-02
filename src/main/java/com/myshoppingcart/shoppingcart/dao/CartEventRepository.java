package com.myshoppingcart.shoppingcart.dao;

import com.myshoppingcart.shoppingcart.models.CartEvent;

import java.util.List;

public interface CartEventRepository {
    public void save(CartEvent cartEvent);

    public List<CartEvent> findByCartId(Integer cartId);
}
