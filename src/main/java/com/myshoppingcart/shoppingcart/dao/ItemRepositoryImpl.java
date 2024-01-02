package com.myshoppingcart.shoppingcart.dao;

import com.myshoppingcart.shoppingcart.models.Item;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class ItemRepositoryImpl implements ItemRepository {
    private final Map<Integer, Item> items = new ConcurrentHashMap<>();
    private final AtomicInteger sequence = new AtomicInteger(0);

    @Override
    public Item save(Item item) {
        if (item.getSku() == null) {
            int itemId = sequence.incrementAndGet();
            item.setSku(itemId);
        }
        items.put(item.getSku(), item);
        return item;
    }

    @Override
    public Item findById(Integer sku) {
        return items.get(sku);
    }

    @Override
    public Boolean delete(Integer sku) {
        return items.remove(sku) != null;
    }

    @Override
    public List<Item> getAllItems() {
        return items.values()
                .stream()
                .collect(Collectors.toList());
    }
}
