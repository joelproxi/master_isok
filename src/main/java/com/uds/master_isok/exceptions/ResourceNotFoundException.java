package com.uds.master_isok.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    private String resource;
    private String fieldName;
    private Object fieldValue;

    public ResourceNotFoundException(String resource, String fieldName, Object fieldValue) {
        super(String.format("%s with %s : %s not found", resource, fieldName, fieldValue));
        this.resource = resource;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}