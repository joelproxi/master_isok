package com.uds.master_isok.utils.payload;

import org.springframework.data.domain.Sort;

public record SearchCriteria(
        int page,
        int size,
        String sortBy,
        Sort.Direction sortDirection,
        String search
) {}