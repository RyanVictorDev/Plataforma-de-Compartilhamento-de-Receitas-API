package com.project.recipes.users.validations;

import com.project.recipes.users.DTOs.CreateUserRequestDTO;
import com.project.recipes.users.DTOs.UserResponseDTO;
import com.project.recipes.users.models.UserModel;
import com.project.recipes.users.repositories.UserRepositotry;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserValidations {

    @Autowired
    UserRepositotry userRepositotry;

    public void create(CreateUserRequestDTO data){

    }

//    private void validateName(CreateUserRequestDTO data){
//        Optional<UserModel> response = Optional.ofNullable(userRepositotry.findByName(data.email()));
//        if (!response.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//    }
}
