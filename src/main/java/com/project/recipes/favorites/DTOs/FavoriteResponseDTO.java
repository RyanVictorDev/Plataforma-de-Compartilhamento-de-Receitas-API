package com.project.recipes.favorites.DTOs;

import com.project.recipes.recipes.models.RecipeModel;
import com.project.recipes.users.models.UserModel;
import lombok.Builder;

@Builder
public record FavoriteResponseDTO(
        int id,
        UserModel user,
        RecipeModel recipe) {
}
