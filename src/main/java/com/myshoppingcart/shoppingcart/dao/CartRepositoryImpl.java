package com.myshoppingcart.shoppingcart.dao;

import com.myshoppingcart.shoppingcart.models.Cart;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CartRepositoryImpl implements CartRepository {
    private final Map<Integer, Cart> carts = new ConcurrentHashMap<>();
    private int sequence = 0;

    @Override
    public Cart save(Cart cart) {
        if (cart.getId() == null) {
            cart.setId(++sequence);
        }
        carts.put(cart.getId(), cart);
        return carts.get(cart.getId());
    }

    @Override
    public Cart findById(Integer id) {
        return carts.get(id);
    }

    @Override
    public Boolean delete(Integer id) {
        return carts.remove(id) != null;
    }
}
