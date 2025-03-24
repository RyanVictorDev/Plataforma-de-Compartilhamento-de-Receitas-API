package com.project.recipes.users.validations;

import com.project.recipes.exceptions.CustomValidationException;
import com.project.recipes.users.DTOs.CreateUserRequestDTO;
import com.project.recipes.users.DTOs.UpdateUserRequestDTO;
import com.project.recipes.users.models.UserModel;
import com.project.recipes.users.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserValidations {

    @Autowired
    UserRepository userRepository;

    public void create(CreateUserRequestDTO data){
        validateName(data);
        validateEmail(data);
    }

    public void update(UpdateUserRequestDTO data, int id){
        validateUpdatedName(data, id);
        validateUpdatedEmail(data, id);
    }

    private void validateName(CreateUserRequestDTO data){
        if (data.name().isEmpty()) throw new CustomValidationException("Username cannot by empty");

        Optional<UserDetails> response = Optional.ofNullable(userRepository.findByName(data.name()));
        if (!response.isEmpty()) throw new CustomValidationException("Username already used");
    }

    private void validateUpdatedName(UpdateUserRequestDTO data, int id){
        if (data.name().isEmpty()) throw new CustomValidationException("Username cannot by empty");

        Optional<UserModel> user = Optional.ofNullable(userRepository.findById(id)).get();

        if (user.get().getName() != data.name()){
            Optional<UserDetails> response = Optional.ofNullable(userRepository.findByName(data.name()));
            if (!response.isEmpty()) throw new CustomValidationException("Username already used");
        }
    }

    private void validateEmail(CreateUserRequestDTO data){
        if (data.email().isEmpty()) throw new CustomValidationException("Email cannot be empty");

        Optional<UserDetails> response = Optional.ofNullable(userRepository.findByEmail(data.email()));
        if (!response.isEmpty()) throw new CustomValidationException("Email already used");
    }

    private void validateUpdatedEmail(UpdateUserRequestDTO data, int id){
        if (data.email().isEmpty()) throw new CustomValidationException("Email cannot by empty");

        Optional<UserModel> user = Optional.ofNullable(userRepository.findById(id)).get();

        if (user.get().getEmail() != data.email()){
            Optional<UserDetails> response = Optional.ofNullable(userRepository.findByEmail(data.email()));
            if (!response.isEmpty()) throw new CustomValidationException("Email already used");
        }
    }
}
