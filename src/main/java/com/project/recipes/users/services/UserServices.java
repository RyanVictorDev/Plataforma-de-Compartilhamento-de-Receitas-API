package com.project.recipes.users.services;

import com.project.recipes.users.DTOs.CreateUserRequestDTO;
import com.project.recipes.users.DTOs.UpdateUserRequestDTO;
import com.project.recipes.users.models.UserModel;
import com.project.recipes.users.repositories.UserRepository;
import com.project.recipes.users.validations.UserValidations;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserValidations userValidations;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public ResponseEntity<Void> create(@Valid CreateUserRequestDTO data){
        String encryptedPassword = passwordEncoder.encode(data.password());

        userValidations.create(data);

        UserModel user = new UserModel(data.name(), data.email(), encryptedPassword);
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public List<UserModel> getAll (){
        return userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public Optional<UserModel> getById(int id) {
        return userRepository.findById(id);
    }

    public ResponseEntity<Object> update (int id, UpdateUserRequestDTO data){
        Optional<UserModel> response = userRepository.findById(id);
        if (response.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");

        userValidations.update(data, id);

        UserModel userModel = response.get();

        userModel.setName(data.name());
        userModel.setEmail(data.email());

        userRepository.save(userModel);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }
}
