package com.example.javaEcommerce.dto;

import lombok.Data;

@Data
public class LoginRequest {

    private String password;
    private String email;

}
