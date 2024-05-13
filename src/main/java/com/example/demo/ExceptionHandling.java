package com.example.demo;

public class ExceptionHandling extends RuntimeException {
    public ExceptionHandling(String resource, String field, Object value) {
        super(String.format("%s not found with %s : '%s'", resource, field, value));
    }
}

