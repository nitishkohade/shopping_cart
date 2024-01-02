package com.myshoppingcart.shoppingcart.controllers;

import com.myshoppingcart.shoppingcart.models.Cart;
import com.myshoppingcart.shoppingcart.models.CartItem;
import com.myshoppingcart.shoppingcart.models.Item;
import com.myshoppingcart.shoppingcart.services.CartService;
import com.myshoppingcart.shoppingcart.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;
    private final ItemService itemService;

    @Autowired
    public CartController(CartService cartService, ItemService itemService) {
        this.cartService = cartService;
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<Cart> createCart() {
        Cart newCart = new Cart();
        Cart savedCart = cartService.createCart(newCart);
        System.out.println(savedCart);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCart);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCart(@PathVariable Integer cartId) {
        return ResponseEntity.ok(cartService.getCart(cartId));
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Boolean> deleteCart(@PathVariable Integer cartId) {
        Boolean isCartEmpty = cartService.emptyCart(cartId);
        return ResponseEntity.ok(isCartEmpty);
    }

    @PostMapping("/{cartId}/items/{itemId}")
    public ResponseEntity<Cart> addItemToCart(@PathVariable Integer cartId, @PathVariable Integer itemId) {
        Item item = itemService.getItem(itemId);
        CartItem cartItem = cartService.findCartItemInCart(cartId, item.getSku());
        Cart cart = null;
        if(cartItem == null) {
            cart = cartService.addItemToCart(cartId, item);
        } else {
            cart = cartService.updateItemQuantity(cartId, cartItem.getId(), cartItem.getQuantity() + 1);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(cart);
    }

    @DeleteMapping("/{cartId}/items/{itemId}")
    public ResponseEntity<Cart> removeItemFromCart(@PathVariable Integer cartId, @PathVariable Integer itemId) {
        Cart cart = cartService.removeItemFromCart(cartId, itemId);
        return ResponseEntity.status(HttpStatus.OK).body(cart);
    }

    @PatchMapping("/{cartId}/items/{itemId}")
    public ResponseEntity<Cart> updateItemQuantity(@PathVariable Integer cartId,
                                                     @PathVariable Integer itemId,
                                                     @RequestParam Integer quantity) {
        Cart updatedCart = cartService.updateItemQuantity(cartId, itemId, quantity);
        return ResponseEntity.ok(updatedCart);
    }
}
