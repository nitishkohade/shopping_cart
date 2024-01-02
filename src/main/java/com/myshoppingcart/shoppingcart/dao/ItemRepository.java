package com.myshoppingcart.shoppingcart.dao;

import com.myshoppingcart.shoppingcart.models.Item;

import java.util.List;

public interface ItemRepository {
    Item save(Item item);
    Item findById(Integer sku);
    Boolean delete(Integer sku);
    List<Item> getAllItems();
}
