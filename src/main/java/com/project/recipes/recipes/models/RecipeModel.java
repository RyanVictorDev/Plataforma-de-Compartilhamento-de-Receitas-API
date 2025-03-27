package com.project.recipes.recipes.models;

import com.project.recipes.users.models.UserModel;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tb_recipes")
public class RecipeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 150, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String ingredients;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String preparation;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserModel user;

    @Column(nullable = false)
    private boolean isDeleted;

    public RecipeModel(String title, String description, String ingredients, String preparation, UserModel user){
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
        this.preparation = preparation;
        this.user = user;
        this.isDeleted = false;
    }

}
