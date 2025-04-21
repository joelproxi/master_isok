package com.uds.master_isok.utils;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


public  class PersonRequest {
    @NotEmpty(message = "First name must not be empty")
    @Length(min = 3)
    private String firstName;

    @NotEmpty(message = "Last name must not be empty")
    @Length(min = 3, message = "last name must be grater than 3")
    private String lastName;

    @NotEmpty(message = "Email name must not be empty")
    @Email
    @Length(min = 3)
    private String email;
}
