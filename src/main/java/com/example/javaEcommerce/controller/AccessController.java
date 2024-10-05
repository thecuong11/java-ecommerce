package com.example.javaEcommerce.controller;

import com.example.javaEcommerce.dto.LoginRequest;
import com.example.javaEcommerce.dto.ResponseDto;
import com.example.javaEcommerce.dto.SignUpRequest;
import com.example.javaEcommerce.services.impl.AccessServiceImpl;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api")
public class AccessController {

    @Autowired
    private AccessServiceImpl accessService;

    @SneakyThrows
    @PostMapping("/shop/signup")
    public ResponseDto<?> signUp(@RequestBody SignUpRequest signUpRequest) {

        signUpRequest.validate();
        accessService.signUp(signUpRequest);
        return new ResponseDto<>("signup successful");
    }

    @PostMapping("/shop/login")
    public ResponseDto<?> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("ok");
        return new ResponseDto<>(accessService.login(loginRequest));
    }

    @GetMapping("/fb/list")
    public ResponseDto<?> get() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        return new ResponseDto<>("Hello World");
    }

}
