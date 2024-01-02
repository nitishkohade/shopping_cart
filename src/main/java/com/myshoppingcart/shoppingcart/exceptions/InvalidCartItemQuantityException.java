package com.myshoppingcart.shoppingcart.exceptions;

public class InvalidCartItemQuantityException extends RuntimeException {
    public InvalidCartItemQuantityException(String message) {
        super(message);
    }

    public InvalidCartItemQuantityException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCartItemQuantityException(Throwable cause) {
        super(cause);
    }
}
