package com.dreamer925.footballManager.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException() {
        super("Entity was not found.");
    }
}
