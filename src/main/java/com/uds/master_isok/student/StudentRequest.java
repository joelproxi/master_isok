package com.uds.master_isok.student;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record StudentRequest(
        @NotEmpty(message = "First name must not be empty")
        @Length(min = 3, message = "First name must be at least 3 characters long")
        String firstName,

        @NotEmpty(message = "Last name must not be empty")
        @Length(min = 3, message = "Last name must be at least 3 characters long")
        String lastName,

        @NotEmpty(message = "Email must not be empty")
        @Email(message = "Email should be valid")
        String email,

        String dateOfBirth,
        String idNumber
) {
}
