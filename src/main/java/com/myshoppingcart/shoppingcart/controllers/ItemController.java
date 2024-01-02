package com.myshoppingcart.shoppingcart.controllers;

import com.myshoppingcart.shoppingcart.models.Item;
import com.myshoppingcart.shoppingcart.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<Item> addItem(@RequestBody Item item) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.addItem(item));
    }

    @GetMapping("/{sku}")
    public ResponseEntity<Item> getItem(@PathVariable Integer sku) {
        return ResponseEntity.ok(itemService.getItem(sku));
    }

    @GetMapping
    public ResponseEntity<List<Item>> getItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }

    @DeleteMapping("/{sku}")
    public ResponseEntity<Boolean> deleteItem(@PathVariable Integer sku) {
        return ResponseEntity.ok(itemService.deleteItem(sku));
    }
}
