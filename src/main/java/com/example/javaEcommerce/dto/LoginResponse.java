package com.example.javaEcommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String Id;

    private String Name;

    private String Email;

    private String accessToken;

    private String refreshToken;

}
