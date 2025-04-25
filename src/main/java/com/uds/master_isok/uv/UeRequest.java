package com.uds.master_isok.uv;

import com.uds.master_isok.uv.UE.Semester;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UeRequest(
    @NotBlank(message = "The title is required")
    @Size(max = 100, message = "The title must not exceed 100 characters")
    String title,

    @NotBlank(message = "The description is required")
    String description,

    @NotNull(message = "The credits are required")
    @Min(value = 1, message = "Credits must be at least 1")
    Integer credits,

    @NotNull(message = "The semester is required")
    Semester semester,

    @NotBlank(message = "The code is required")
    String code
    
) {

    public boolean hasValidTitle() {
        return title != null && !title.isBlank();
    }
    public boolean hasValidDescription() {
        return description != null && !description.isBlank();
    }   
}