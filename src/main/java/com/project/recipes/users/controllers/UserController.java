package com.project.recipes.users.controllers;

import com.project.recipes.users.DTOs.CreateUserRequestDTO;
import com.project.recipes.users.DTOs.UpdateUserRequestDTO;
import com.project.recipes.users.DTOs.UserResponseDTO;
import com.project.recipes.users.mappers.UserMapper;
import com.project.recipes.users.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {
    @Autowired
    UserServices userServices;

    @Autowired
    UserMapper userMapper;

    @PostMapping("/user")
    public ResponseEntity<Void> create(@RequestBody @Valid CreateUserRequestDTO data){
        return userServices.create(data);
    }

    @GetMapping("/user")
    public ResponseEntity<Object> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(userMapper.toUserResponseList(userServices.getAll()));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable(value = "id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(userMapper.toUserResponse(userServices.getById(id).get()));
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") int id, @RequestBody @Valid UpdateUserRequestDTO data){
        return userServices.update(id, data);
    }
}
