package com.uds.master_isok.utils.validation;

import com.uds.master_isok.exceptions.InvalidSortFieldException;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Component
public class PaginationValidator {
    private static final List<String> ALLOWED_SORT_FIELDS =
            List.of("firstName", "lastName", "email", "createdAt");

    public void validateParameters(
            @Min(value = 0, message = "Page index must not be less than zero") int page,
            @Min(value = 1, message = "Page size must be at least 1")
            @Max(value = 100, message = "Page size must be at most 100") int size,
            @NotNull(message = "Sort criteria cannot be null") String[] sort
    ) {
        validateSortFields(sort);
    }

    private void validateSortFields(String[] sortFields) {
        if (sortFields == null) return;
        for (String field : sortFields) {
            String cleanField = field.replace("-", "").trim();
            if (!ALLOWED_SORT_FIELDS.contains(cleanField)) {
                throw new InvalidSortFieldException(
                        "Invalid sort field: " + cleanField +
                        ". Allowed fields: " + ALLOWED_SORT_FIELDS
                );
            }
        }
    }
}
