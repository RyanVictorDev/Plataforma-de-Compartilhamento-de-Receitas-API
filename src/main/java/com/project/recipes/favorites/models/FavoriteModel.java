package com.project.recipes.favorites.models;

import com.project.recipes.recipes.models.RecipeModel;
import com.project.recipes.users.models.UserModel;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tb_favorites")
public class FavoriteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "recipeId", nullable = false)
    private RecipeModel recipe;

    public FavoriteModel(UserModel user, RecipeModel recipe){
        this.user = user;
        this.recipe = recipe;
    }
}
