package com.project.recipes.favorites.services;

import com.project.recipes.favorites.DTOs.CreateFavoriteRequesteDTO;
import com.project.recipes.favorites.models.FavoriteModel;
import com.project.recipes.favorites.repositories.FavoriteRepository;
import com.project.recipes.recipes.models.RecipeModel;
import com.project.recipes.recipes.repositories.RecipeRepository;
import com.project.recipes.users.models.UserModel;
import com.project.recipes.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteServices {
    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    public ResponseEntity<Void> create(CreateFavoriteRequesteDTO data){
        Optional<UserModel> user = Optional.of(userRepository.findById(data.userId()).get());
        Optional<RecipeModel> recipe = Optional.of(recipeRepository.findById(data.recipeId()).get());

        FavoriteModel favorite = new FavoriteModel(user.get(), recipe.get());

        favoriteRepository.save(favorite);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public List<FavoriteModel> getAll(){
        return favoriteRepository.findAll();
    }

    public List<FavoriteModel> getAllByUser(int id){
        return favoriteRepository.findAllByUserId(id);
    }

    public List<FavoriteModel> getAllByRecipe(int id){
        return favoriteRepository.findAllByRecipeId(id);
    }

    public ResponseEntity<Void> delete(int id){
        FavoriteModel favorite = favoriteRepository.findById(id).get();

        favoriteRepository.delete(favorite);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
