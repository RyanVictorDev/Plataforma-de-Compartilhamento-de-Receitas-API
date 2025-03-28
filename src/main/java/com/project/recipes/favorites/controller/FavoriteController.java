package com.project.recipes.favorites.controller;

import com.project.recipes.favorites.DTOs.CreateFavoriteRequesteDTO;
import com.project.recipes.favorites.mappers.FavoriteMapper;
import com.project.recipes.favorites.services.FavoriteServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FavoriteController {
    @Autowired
    private FavoriteMapper favoriteMapper;

    @Autowired
    private FavoriteServices favoriteServices;

    @PostMapping("/favorite")
    public ResponseEntity<Void> Favorite(@RequestBody @Valid CreateFavoriteRequesteDTO data){
        return favoriteServices.create(data);
    }

    @GetMapping("/favorite/user/{id}")
    public ResponseEntity<Object> getAllByUser(@PathVariable(value = "id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(favoriteMapper.toFavoriteResponseList(favoriteServices.getAllByUser(id)));
    }

    @GetMapping("/favorite/recipe/{id}")
    public ResponseEntity<Object> getAllByRecipe(@PathVariable(value = "id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(favoriteMapper.toFavoriteResponseList(favoriteServices.getAllByRecipe(id)));
    }

    @DeleteMapping("/favorite/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") int id){
        return favoriteServices.delete(id);
    }
}
