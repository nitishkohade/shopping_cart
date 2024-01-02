package com.myshoppingcart.shoppingcart.services;

import com.myshoppingcart.shoppingcart.exceptions.ItemNotFoundException;
import com.myshoppingcart.shoppingcart.models.Item;

import java.util.List;

public interface ItemService {
    public Item addItem(Item item);

    public Item getItem(Integer sku) throws ItemNotFoundException;

    public Boolean deleteItem(Integer sku);

    public List<Item> getAllItems();
}
