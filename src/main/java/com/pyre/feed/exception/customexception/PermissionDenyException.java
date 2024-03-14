package com.pyre.feed.exception.customexception;

public class PermissionDenyException extends RuntimeException{
    public PermissionDenyException(String message) {
        super(message);
    }
}
