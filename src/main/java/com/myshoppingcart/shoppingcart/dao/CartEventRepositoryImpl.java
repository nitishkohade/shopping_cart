package com.myshoppingcart.shoppingcart.dao;

import com.myshoppingcart.shoppingcart.models.CartEvent;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CartEventRepositoryImpl implements CartEventRepository {
    private final Map<Integer, List<CartEvent>> eventsByCartId = new ConcurrentHashMap<>();

    @Override
    public void save(CartEvent cartEvent) {
        eventsByCartId.computeIfAbsent(cartEvent.getCartId(), k -> new LinkedList<>()).add(cartEvent);
    }

    @Override
    public List<CartEvent> findByCartId(Integer cartId) {
        return eventsByCartId.getOrDefault(cartId, new LinkedList<>());
    }
}
