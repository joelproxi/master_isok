package com.uds.master_isok.utils.payload;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Sort;

public record SearchCriteria(
        @PositiveOrZero @DefaultValue("0") int page,
        @Positive @Max(100) @DefaultValue("20") int size,
        @DefaultValue("fullName") String sortBy,
        @DefaultValue("ASC") Sort.Direction sortDirection,
        String search
) {}