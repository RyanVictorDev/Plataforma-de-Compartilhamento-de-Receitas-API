package com.project.recipes.recipes.mappers;

import com.project.recipes.recipes.DTOs.RecipeResponseDTO;
import com.project.recipes.recipes.models.RecipeModel;
import com.project.recipes.users.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecipeMapper {
    @Autowired
    private UserMapper userMapper;

    public List<RecipeResponseDTO> toRecipeResponseList(List<RecipeModel> recipeModelList){
        return recipeModelList.stream().map(this::toRecipeResponse).toList();
    }

    public RecipeResponseDTO toRecipeResponse(RecipeModel model){
        return RecipeResponseDTO
                .builder()
                .id(model.getId())
                .title(model.getTitle())
                .description(model.getDescription())
                .ingredients(model.getIngredients())
                .preparation(model.getPreparation())
                .user(userMapper.toUserResponse(model.getUser()))
                .tag(model.getTag())
                .build();
    }
}
