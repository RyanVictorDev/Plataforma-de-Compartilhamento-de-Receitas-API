package com.project.recipes.recipes.validations;

import com.project.recipes.exceptions.CustomValidationException;
import com.project.recipes.recipes.DTOs.CreateRecipeRequestDTO;
import com.project.recipes.recipes.models.RecipeModel;
import com.project.recipes.recipes.repositories.RecipeRepository;
import com.project.recipes.users.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Component
public class RecipesValidations {
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    public void create(CreateRecipeRequestDTO data){
        // TODO
    }
}
