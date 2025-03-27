package com.project.recipes.favorites.DTOs;

import jakarta.validation.constraints.NotNull;

public record CreateFavoriteRequesteDTO(
        @NotNull(message = "User cannot be null") int userId,
        @NotNull(message = "Recipe cannot be null") int recipeId
) {
}
