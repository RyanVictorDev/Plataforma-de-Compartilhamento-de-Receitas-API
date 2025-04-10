package com.project.recipes.recipes.repositories;

import com.project.recipes.recipes.models.RecipeModel;
import com.project.recipes.recipes.models.RecipeTagEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeModel, Integer> {
    @Query("SELECT u FROM RecipeModel u " +
            "JOIN u.user s " +
            "WHERE (" +
            "LOWER(REPLACE(u.title, ' ', '')) LIKE LOWER(CONCAT('%', REPLACE(:searchTerm, ' ', ''), '%')) " +
            "OR LOWER(REPLACE(u.description, ' ', '')) LIKE LOWER(CONCAT('%', REPLACE(:searchTerm, ' ', ''), '%')) " +
            "OR LOWER(REPLACE(u.ingredients, ' ', '')) LIKE LOWER(CONCAT('%', REPLACE(:searchTerm, ' ', ''), '%')) " +
            "OR LOWER(REPLACE(u.preparation, ' ', '')) LIKE LOWER(CONCAT('%', REPLACE(:searchTerm, ' ', ''), '%')) " +
            "OR LOWER(REPLACE(s.name, ' ', '')) LIKE LOWER(CONCAT('%', REPLACE(:searchTerm, ' ', ''), '%'))" +
            ") AND u.isDeleted = false AND u.tag = :tag")
    Page<RecipeModel> findAllBySearch(@Param("searchTerm") String searchTerm, RecipeTagEnum tag, Pageable pageable);

    Page<RecipeModel> findAllByIsDeletedFalseAndTag(RecipeTagEnum tag, Pageable pageable);

    @Query("SELECT u FROM RecipeModel u " +
            "JOIN u.user s " +
            "WHERE (" +
            "LOWER(REPLACE(u.title, ' ', '')) LIKE LOWER(CONCAT('%', REPLACE(:searchTerm, ' ', ''), '%')) " +
            "OR LOWER(REPLACE(u.description, ' ', '')) LIKE LOWER(CONCAT('%', REPLACE(:searchTerm, ' ', ''), '%')) " +
            "OR LOWER(REPLACE(u.ingredients, ' ', '')) LIKE LOWER(CONCAT('%', REPLACE(:searchTerm, ' ', ''), '%')) " +
            "OR LOWER(REPLACE(u.preparation, ' ', '')) LIKE LOWER(CONCAT('%', REPLACE(:searchTerm, ' ', ''), '%')) " +
            "OR LOWER(REPLACE(s.name, ' ', '')) LIKE LOWER(CONCAT('%', REPLACE(:searchTerm, ' ', ''), '%'))" +
            ") AND u.isDeleted = false")
    Page<RecipeModel> searchWithoutTag(@Param("searchTerm") String searchTerm, Pageable pageable);

    Page<RecipeModel> findAllByIsDeletedFalse(Pageable pageable);
    List<RecipeModel> findAllByIsDeletedFalse(Sort sort);
    List<RecipeModel> findAllByIsDeletedFalseAndUserId(int id, Sort sort);
    RecipeModel findByIdAndIsDeletedFalse(int id);
    List<RecipeModel> findAllByTitle(String title);
    List<RecipeModel> findAllByUserIdAndIsDeletedFalse(int id);
    boolean existsByUserIdAndTitleAndDescriptionAndIngredientsAndPreparationAndTagAndIsDeletedFalse(int userId, String title, String description, String ingredients, String preparation, RecipeTagEnum tag);
}
