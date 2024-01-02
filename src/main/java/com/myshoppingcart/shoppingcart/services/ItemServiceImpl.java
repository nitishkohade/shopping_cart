package com.myshoppingcart.shoppingcart.services;

import com.myshoppingcart.shoppingcart.dao.ItemRepository;
import com.myshoppingcart.shoppingcart.exceptions.ItemNotFoundException;
import com.myshoppingcart.shoppingcart.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item getItem(Integer sku) {
        Item item = itemRepository.findById(sku);
        if(item == null) {
            throw new ItemNotFoundException("Item with SKU " + sku + " not found");
        }

        return item;
    }

    @Override
    public Boolean deleteItem(Integer sku) {
        Item item = getItem(sku);
        return itemRepository.delete(item.getSku());
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.getAllItems();
    };
}
