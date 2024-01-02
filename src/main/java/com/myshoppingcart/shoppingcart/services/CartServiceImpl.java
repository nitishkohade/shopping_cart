package com.myshoppingcart.shoppingcart.services;

import com.myshoppingcart.shoppingcart.dao.CartEventRepository;
import com.myshoppingcart.shoppingcart.dao.CartRepository;
import com.myshoppingcart.shoppingcart.enums.CartEventType;
import com.myshoppingcart.shoppingcart.exceptions.CartNotFoundException;
import com.myshoppingcart.shoppingcart.exceptions.InvalidCartItemQuantityException;
import com.myshoppingcart.shoppingcart.models.Cart;
import com.myshoppingcart.shoppingcart.models.CartEvent;
import com.myshoppingcart.shoppingcart.models.CartItem;
import com.myshoppingcart.shoppingcart.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartEventRepository cartEventRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, CartEventRepository cartEventRepository) {
        this.cartRepository = cartRepository;
        this.cartEventRepository = cartEventRepository;
    }


    @Override
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart getCart(Integer id) {
        Cart cart = cartRepository.findById(id);
        if(cart == null) {
            throw new CartNotFoundException(String.format("Cart id %d Not Found", id));
        }

        return cart;
    }

    @Override
    public Cart addItemToCart(Integer cartId, Item item) {
        Cart cart = getCart(cartId);
        CartItem cartItem = new CartItem(item.getSku(), item, 1);
        cart.getCartItems().add(cartItem);

        // Logging the cart event
        CartEvent event = new CartEvent(CartEventType.ADD_ITEM, cartId, cartItem.getId(), cartItem.getQuantity());
        cartEventRepository.save(event);

        return cartRepository.save(cart);
    }

    @Override
    public CartItem findCartItemInCart(Integer cartId, Integer itemId) {
        Cart cart = getCart(cartId);
        return cart.getCartItems().stream()
                .filter(cartItem -> cartItem.getId().equals(itemId))
                .findFirst().orElse(null);
    }

    @Override
    public Cart updateItemQuantity(Integer cartId, Integer itemId, Integer quantity) {
        if (quantity < 1 || quantity > 100) {
            throw new InvalidCartItemQuantityException("Quantity must be between 1 and 100 both inclusive.");
        }

        Cart cart = getCart(cartId);
        CartItem cartItem = findCartItemInCart(cartId, itemId);

        if(cartItem != null) {
            cartItem.setQuantity(quantity);
            // Log the event
            CartEvent event = new CartEvent(CartEventType.UPDATE_QUANTITY, cartId, itemId, quantity);
            cartEventRepository.save(event);
        }



        return cartRepository.save(cart);
    }

    @Override
    public Cart removeItemFromCart(Integer cartId, Integer itemId) {
        Cart cart = getCart(cartId);
        boolean itemRemoved = cart.getCartItems().removeIf(cartItem -> cartItem.getId().equals(itemId));

        if (itemRemoved) {
            // Log the event
            CartEvent event = new CartEvent(CartEventType.REMOVE_ITEM, cartId, itemId, null);
            cartEventRepository.save(event);

            cartRepository.save(cart);
        }

        return cart;
    }

    @Override
    public Boolean emptyCart(Integer cartId) {
        Cart cart = getCart(cartId);
        return cartRepository.delete(cart.getId());
    }
}
