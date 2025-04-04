package com.project.recipes.recipes.controller;

import com.project.recipes.recipes.DTOs.CreateRecipeRequestDTO;
import com.project.recipes.recipes.DTOs.RecipeResponseDTO;
import com.project.recipes.recipes.DTOs.UpdateRecipeRequestDTO;
import com.project.recipes.recipes.mappers.RecipeMapper;
import com.project.recipes.recipes.services.RecipeServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RecipeController {
    @Autowired
    private RecipeMapper recipeMapper;

    @Autowired
    private RecipeServices recipeServices;

    @PostMapping("/recipe")
    public ResponseEntity<Void> create(@RequestBody @Valid CreateRecipeRequestDTO data){
        return recipeServices.create(data);
    }

    @GetMapping("/recipe")
    public ResponseEntity<Object> getAll(@RequestParam(required = false) Integer page){
        if (page != null) return ResponseEntity.status(HttpStatus.OK).body(recipeServices.getAll("", page).map(recipeMapper::toRecipeResponse));
        else return ResponseEntity.status(HttpStatus.OK).body(recipeMapper.toRecipeResponseList(recipeServices.getAllWithoutPagination()));
    }

    @GetMapping("/recipe/{id}")
    public ResponseEntity<RecipeResponseDTO> getById(@PathVariable(value = "id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(recipeMapper.toRecipeResponse(recipeServices.getById(id).get()));
    }

    @PutMapping("/recipe/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") int id, @RequestBody @Valid UpdateRecipeRequestDTO data){
        return ResponseEntity.status(HttpStatus.OK).body(recipeServices.update(id, data));
    }

    @DeleteMapping("/recipe/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(recipeServices.delete(id));
    }
}
