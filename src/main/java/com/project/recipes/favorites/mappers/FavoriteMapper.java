package com.project.recipes.favorites.mappers;

import com.project.recipes.favorites.DTOs.FavoriteResponseDTO;
import com.project.recipes.favorites.models.FavoriteModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FavoriteMapper {
    public List<FavoriteResponseDTO> toFavoriteResponseList(List<FavoriteModel> favoriteModelList){
        return favoriteModelList.stream().map(this::toFavoriteResponse).toList();
    }

    public FavoriteResponseDTO toFavoriteResponse(FavoriteModel model){
        return FavoriteResponseDTO
                .builder()
                .id(model.getId())
                .user(model.getUser())
                .recipe(model.getRecipe())
                .build();
    }
}
