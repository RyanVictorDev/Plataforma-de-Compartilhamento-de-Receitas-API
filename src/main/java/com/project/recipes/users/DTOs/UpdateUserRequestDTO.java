package com.project.recipes.users.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateUserRequestDTO(
        @NotBlank(message = "The name cannot be empty") String name,
        @NotBlank(message = "Email cannot be empty") @Email(message = "Invalid email") String email
) {
}
