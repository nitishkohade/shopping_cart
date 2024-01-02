package com.myshoppingcart.shoppingcart.exceptions;

public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(String message) {
        super(message);
    }

    public CartNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CartNotFoundException(Throwable cause) {
        super(cause);
    }

}
