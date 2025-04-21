package com.uds.master_isok.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Collections;
import java.util.Map;

public record ErrorResponse(
        String errorType,
        String message,
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        Map<String, String> details
) {
    public ErrorResponse(String errorType, String message) {
        this(errorType, message, Collections.emptyMap());
    }

    public ErrorResponse(String errorType, Map<String, String> details) {
        this(errorType, "Validation error", details);
    }
}