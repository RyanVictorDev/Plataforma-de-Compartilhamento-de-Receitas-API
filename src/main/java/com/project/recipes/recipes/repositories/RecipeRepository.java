package com.project.recipes.recipes.repositories;

import com.project.recipes.recipes.models.RecipeModel;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeModel, Integer> {
    List<RecipeModel> findAllByIsDeletedFalse(Sort sort);
    RecipeModel findByIdAndIsDeletedFalse(int id);
    List<RecipeModel> findAllByTitle(String title);
    List<RecipeModel> findAllByUserId(int id);
}
