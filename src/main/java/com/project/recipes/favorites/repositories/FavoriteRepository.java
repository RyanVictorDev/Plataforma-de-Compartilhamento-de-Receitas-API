package com.project.recipes.favorites.repositories;

import com.project.recipes.favorites.models.FavoriteModel;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteModel, Integer> {
    List<FavoriteModel> findAllByUserId(int id, Sort sort);
    List<FavoriteModel> findAllByRecipeId(int id, Sort sort);

}
