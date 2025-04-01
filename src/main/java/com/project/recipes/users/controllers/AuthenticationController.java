package com.project.recipes.users.controllers;

import com.project.recipes.users.DTOs.AuthenticationDTO;
import com.project.recipes.users.DTOs.LoginResponseDTO;
import com.project.recipes.users.models.UserModel;
import com.project.recipes.users.repositories.UserRepository;
import com.project.recipes.users.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserModel) auth.getPrincipal());

        UserModel user = repository.findByEmail(data.email());

        return ResponseEntity.ok(new LoginResponseDTO(token, user.getEmail(), user.getName(), user.getId()));
    }
}