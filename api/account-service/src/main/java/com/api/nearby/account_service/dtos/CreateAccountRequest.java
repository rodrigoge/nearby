package com.api.nearby.account_service.dtos;

import com.api.nearby.account_service.db.ProfileTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CreateAccountRequest(
        @NotBlank(message = "Name is required")
        String name,

        @Pattern(
                regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,10}$",
                message = "Password must be between 6 and 10 characters long, including at least one letter, one number, and one special character."
        )
        String password,

        @NotBlank(message = "E-mail is required")
        @Pattern(
                regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
                message = "Invalid e-mail"
        )
        String email,

        @NotNull(message = "Profile type is required")
        ProfileTypeEnum profileType) {
}
