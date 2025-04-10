package com.project.recipes.recipes.validations;

import com.project.recipes.exceptions.CustomValidationException;
import com.project.recipes.recipes.DTOs.CreateRecipeRequestDTO;
import com.project.recipes.recipes.DTOs.UpdateRecipeRequestDTO;
import com.project.recipes.recipes.models.RecipeModel;
import com.project.recipes.recipes.repositories.RecipeRepository;
import com.project.recipes.users.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Component
public class RecipesValidations {
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    public void create(CreateRecipeRequestDTO data){
        validateRecipe(data);
    }

    public void update(int id, UpdateRecipeRequestDTO data){
        validateRecipeUpdate(id, data);
    }

    private void validateRecipe(CreateRecipeRequestDTO data){
        boolean exists = recipeRepository.existsByUserIdAndTitleAndDescriptionAndIngredientsAndPreparationAndTagAndIsDeletedFalse(
                data.userId(), data.title(), data.description(), data.ingredients(), data.preparation(), data.tag());

        if (exists) {
            throw new CustomValidationException("Receita já existente em seu perfil");
        }
    }

    private void validateRecipeUpdate(int id, UpdateRecipeRequestDTO data){
        RecipeModel recipe = recipeRepository.findById(id).get();

        boolean exists = recipeRepository.existsByUserIdAndTitleAndDescriptionAndIngredientsAndPreparationAndTagAndIsDeletedFalse(
                recipe.getUser().getId(), data.title(), data.description(), data.ingredients(), data.preparation(), data.tag());

        if (exists) {
            throw new CustomValidationException("Receita já existente em seu perfil");
        }
    }
}
