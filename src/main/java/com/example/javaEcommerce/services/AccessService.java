package com.example.javaEcommerce.services;

import com.example.javaEcommerce.dto.LoginRequest;
import com.example.javaEcommerce.dto.LoginResponse;
import com.example.javaEcommerce.dto.SignUpRequest;

public interface AccessService {
    void signUp(SignUpRequest signUpRequest);

    LoginResponse login(LoginRequest loginRequest);
}
