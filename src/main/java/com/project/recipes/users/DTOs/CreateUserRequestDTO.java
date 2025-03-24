package com.project.recipes.users.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserRequestDTO(
        @NotBlank(message = "Name cannot be empty") String name,
        @NotBlank(message = "Email cannot be empty") @Email(message = "Invalid email") String email,
        @NotBlank(message = "Password cannot be empty") String password) {
}
