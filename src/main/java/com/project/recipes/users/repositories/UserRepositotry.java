package com.project.recipes.users.repositories;

import com.project.recipes.users.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositotry extends JpaRepository<UserModel, Integer> {
    UserModel findByName(String name);
    UserModel findByEmail(String email);
}
