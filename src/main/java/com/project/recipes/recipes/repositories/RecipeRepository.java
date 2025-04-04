package com.project.recipes.recipes.repositories;

import com.project.recipes.recipes.models.RecipeModel;
import com.project.recipes.recipes.models.RecipeTagEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeModel, Integer> {
    Page<RecipeModel> findAllByIsDeletedFalseAndTag(Pageable pageable, RecipeTagEnum tag);
    Page<RecipeModel> findAllByIsDeletedFalse(Pageable pageable);
    List<RecipeModel> findAllByIsDeletedFalse(Sort sort);
    List<RecipeModel> findAllByIsDeletedFalseAndUserId(int id, Sort sort);
    RecipeModel findByIdAndIsDeletedFalse(int id);
    List<RecipeModel> findAllByTitle(String title);
    List<RecipeModel> findAllByUserId(int id);
}
