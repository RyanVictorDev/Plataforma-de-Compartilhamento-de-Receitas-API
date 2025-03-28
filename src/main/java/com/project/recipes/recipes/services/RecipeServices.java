package com.project.recipes.recipes.services;

import com.project.recipes.recipes.DTOs.CreateRecipeRequestDTO;
import com.project.recipes.recipes.DTOs.UpdateRecipeRequestDTO;
import com.project.recipes.recipes.models.RecipeModel;
import com.project.recipes.recipes.repositories.RecipeRepository;
import com.project.recipes.recipes.validations.RecipesValidations;
import com.project.recipes.users.models.UserModel;
import com.project.recipes.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeServices {
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipesValidations recipesValidations;

    public ResponseEntity<Void> create(CreateRecipeRequestDTO data){

        recipesValidations.create(data);

        Optional<UserModel> user = userRepository.findById(data.userId());
        RecipeModel recipe = new RecipeModel(data.title(), data.description(), data.ingredients(), data.preparation(), user.get(), data.tag());

        recipeRepository.save(recipe);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public List<RecipeModel> getAll(){
        return recipeRepository.findAllByIsDeletedFalse();
    }

    public Optional<RecipeModel> getById(int id){
        return Optional.ofNullable(recipeRepository.findByIdAndIsDeletedFalse(id));
    }

    public ResponseEntity<Object> update(int id, UpdateRecipeRequestDTO data){
        Optional<RecipeModel> response = recipeRepository.findById(id);
        if (response.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recipe not found");

        recipesValidations.update(id, data);

        RecipeModel recipe = response.get();

        recipe.setTitle(data.title());
        recipe.setDescription(data.description());
        recipe.setIngredients(data.ingredients());
        recipe.setPreparation(data.preparation());
        recipe.setTag(data.tag());

        recipeRepository.save(recipe);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    public ResponseEntity<Object> delete (int id){
        Optional<RecipeModel> response = recipeRepository.findById(id);
        if (response.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recipe not found");

        RecipeModel recipe = response.get();

        recipe.setDeleted(true);

        recipeRepository.save(recipe);

        return ResponseEntity.status(HttpStatus.OK).body("Recipe deleted");
    }
}
