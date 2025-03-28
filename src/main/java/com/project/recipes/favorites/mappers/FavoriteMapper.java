package com.project.recipes.favorites.mappers;

import com.project.recipes.favorites.DTOs.FavoriteResponseDTO;
import com.project.recipes.favorites.models.FavoriteModel;
import com.project.recipes.recipes.mappers.RecipeMapper;
import com.project.recipes.users.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FavoriteMapper {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RecipeMapper recipeMapper;

    public List<FavoriteResponseDTO> toFavoriteResponseList(List<FavoriteModel> favoriteModelList){
        return favoriteModelList.stream().map(this::toFavoriteResponse).toList();
    }

    public FavoriteResponseDTO toFavoriteResponse(FavoriteModel model){
        return FavoriteResponseDTO
                .builder()
                .id(model.getId())
                .user(userMapper.toUserResponse(model.getUser()))
                .recipe(recipeMapper.toRecipeResponse(model.getRecipe()))
                .build();
    }
}
