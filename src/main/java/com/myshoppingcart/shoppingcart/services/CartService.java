package com.myshoppingcart.shoppingcart.services;

import com.myshoppingcart.shoppingcart.exceptions.InvalidCartItemQuantityException;
import com.myshoppingcart.shoppingcart.models.Cart;
import com.myshoppingcart.shoppingcart.models.CartItem;
import com.myshoppingcart.shoppingcart.models.Item;

public interface CartService {

    public Cart createCart(Cart cart);

    public Cart getCart(Integer id);

    public Cart addItemToCart(Integer cartId, Item item);

    public Cart updateItemQuantity(Integer cartId, Integer itemId, Integer quantity) throws InvalidCartItemQuantityException;

    public Cart removeItemFromCart(Integer cartId, Integer itemId);

    public Boolean emptyCart(Integer cartId);

    public CartItem findCartItemInCart(Integer cartId, Integer itemId);
}
