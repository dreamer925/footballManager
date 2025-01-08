package com.dreamer925.footballManager.exceptions;

public class NotEnoughFoundsException extends RuntimeException {
    public NotEnoughFoundsException() {
        super("There are not enough founds on an account to complete the operation.");
    }
}
