package com.project.recipes.recipes.DTOs;

import jakarta.validation.constraints.NotBlank;

public record UpdateRecipeRequestDTO(
        @NotBlank(message = "Title cannot be empty") String title,
        @NotBlank(message = "Description cannot be empty") String description,
        @NotBlank(message = "Ingredients cannot be empty") String ingredients,
        @NotBlank(message = "Preparation cannot be empty") String preparation) {
}
