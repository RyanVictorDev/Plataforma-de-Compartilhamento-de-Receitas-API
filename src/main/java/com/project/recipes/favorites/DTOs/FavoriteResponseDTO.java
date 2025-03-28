package com.project.recipes.favorites.DTOs;

import com.project.recipes.recipes.DTOs.RecipeResponseDTO;
import com.project.recipes.users.DTOs.UserResponseDTO;
import lombok.Builder;

@Builder
public record FavoriteResponseDTO(
        int id,
        UserResponseDTO user,
        RecipeResponseDTO recipe) {
}
