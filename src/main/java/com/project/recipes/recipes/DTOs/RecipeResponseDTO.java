package com.project.recipes.recipes.DTOs;

import com.project.recipes.recipes.models.RecipeTagEnum;
import com.project.recipes.users.DTOs.UserResponseDTO;
import lombok.Builder;

@Builder
public record RecipeResponseDTO(
        int id,
        String title,
        String description,
        String ingredients,
        String preparation,
        UserResponseDTO user,
        RecipeTagEnum tag) {
}
