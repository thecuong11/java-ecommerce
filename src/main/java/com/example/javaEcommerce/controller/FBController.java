package com.example.javaEcommerce.controller;

import com.example.javaEcommerce.dto.ResponseDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/api")
public class FBController {
    @GetMapping("/get")
    public ResponseDto<?> get() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        return new ResponseDto<>("Hello World");
    }
}
