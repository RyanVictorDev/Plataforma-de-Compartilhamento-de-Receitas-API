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
        List<RecipeModel> recipes = recipeRepository.findAllByUserIdAndIsDeletedFalse(data.userId());

        for (RecipeModel recipe : recipes){
            if (recipe.getTitle().equals(data.title()) &&
                    recipe.getDescription().equals(data.description()) &&
                    recipe.getIngredients().equals(data.ingredients()) &&
                    recipe.getTag().equals(data.tag()) &&
                    recipe.getPreparation().equals(data.preparation())) {
                throw new CustomValidationException("Receita já existente em seu perfil");
            }
        }
    }

    private void validateRecipeUpdate(int id, UpdateRecipeRequestDTO data){
        RecipeModel recipeModel = recipeRepository.findById(id).get();
        List<RecipeModel> recipes = recipeRepository.findAllByUserIdAndIsDeletedFalse(recipeModel.getUser().getId());

        for (RecipeModel recipe : recipes){
            if (recipe.getTitle().equals(data.title()) &&
                    recipe.getDescription().equals(data.description()) &&
                    recipe.getIngredients().equals(data.ingredients()) &&
                    recipe.getTag().equals(data.tag()) &&
                    recipe.getPreparation().equals(data.preparation())) {
                throw new CustomValidationException("Receita já existente em seu perfil");
            }
        }
    }
}
