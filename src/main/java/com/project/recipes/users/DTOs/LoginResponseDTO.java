package com.project.recipes.users.DTOs;

public record LoginResponseDTO(String token, String email, String name, int id) {
}
