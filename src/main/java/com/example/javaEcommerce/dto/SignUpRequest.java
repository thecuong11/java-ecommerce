package com.example.javaEcommerce.dto;

import io.micrometer.common.util.StringUtils;
import lombok.Data;

@Data
public class SignUpRequest {
    private String name;
    private String password;
    private String email;

    public void validate() throws Exception{
        if (StringUtils.isBlank(name) || name.length() < 8){
            throw new Exception("Name invalid");
        }
        if (StringUtils.isBlank(email) || email.length() < 8){
            throw new Exception("Email invalid");
        }
        if (StringUtils.isBlank(password) || password.length() < 8){
            throw new Exception("Password invalid");
        }

    }
}
