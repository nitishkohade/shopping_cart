package com.myshoppingcart.shoppingcart.dao;

import com.myshoppingcart.shoppingcart.models.Cart;

public interface CartRepository {
    Cart save(Cart cart);
    Cart findById(Integer id);
    Boolean delete(Integer id);
}
