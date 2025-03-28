package com.project.recipes.recipes.mappers;

import com.project.recipes.recipes.DTOs.RecipeResponseDTO;
import com.project.recipes.recipes.models.RecipeModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecipeMapper {
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
                .user(model.getUser())
                .tag(model.getTag())
                .build();
    }
}
