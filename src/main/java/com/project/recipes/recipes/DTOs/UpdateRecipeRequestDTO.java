package com.project.recipes.recipes.DTOs;

import com.project.recipes.recipes.models.RecipeTagEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateRecipeRequestDTO(
        @NotBlank(message = "Title cannot be empty") String title,
        @NotBlank(message = "Description cannot be empty") String description,
        @NotBlank(message = "Ingredients cannot be empty") String ingredients,
        @NotBlank(message = "Preparation cannot be empty") String preparation,
        @NotNull(message = "Tag cannot be null") RecipeTagEnum tag) {
}
