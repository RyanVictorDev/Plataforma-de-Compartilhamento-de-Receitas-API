package com.project.recipes.users.repositories;

import com.project.recipes.users.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    UserDetails findByName(String name);
//    UserDetails findByEmail(String email);
    UserModel findByEmail(String email);
}
