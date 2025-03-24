package com.project.recipes.users.DTOs;

import lombok.Builder;

@Builder
public record UserResponseDTO(
        int id,
        String name,
        String email) {
}
