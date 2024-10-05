package com.example.javaEcommerce.services.impl;

import com.example.javaEcommerce.enums.SesionStatus;
import com.example.javaEcommerce.utils.JwtUtils;
import com.example.javaEcommerce.common.Constant;
import com.example.javaEcommerce.dto.CustomUserDetails;
import com.example.javaEcommerce.dto.LoginRequest;
import com.example.javaEcommerce.dto.LoginResponse;
import com.example.javaEcommerce.dto.SignUpRequest;
import com.example.javaEcommerce.enums.RoleShop;
import com.example.javaEcommerce.models.ShopModel;
import com.example.javaEcommerce.repositoryes.ShopRepository;
import com.example.javaEcommerce.services.AccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class AccessServiceImpl implements AccessService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void signUp(SignUpRequest signUpRequest) {
        Optional<ShopModel> shopModel = shopRepository.findFirstByEmail(signUpRequest.getEmail());
        if (shopModel.isPresent()) {
            throw new RuntimeException("Shop already registered");
        }

        String passwordHash = passwordEncoder.encode(signUpRequest.getPassword());

        ShopModel newShop = new ShopModel();
        newShop.setEmail(signUpRequest.getEmail());
        newShop.setPassword(passwordHash);
        newShop.setName(signUpRequest.getName());
        newShop.setRoles(List.of(RoleShop.SHOP.name()));
        shopRepository.save(newShop);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(), loginRequest.getPassword());

        Authentication authenticated = authenticationManager.authenticate(authentication);
        CustomUserDetails userDetails = (CustomUserDetails) authenticated.getPrincipal();
        ShopModel shop = userDetails.getShop();

        Map<String, Object> payloadJwt = new HashMap<>();
        payloadJwt.put(Constant.PAYLOAD_USER_ID, shop.getId());
        payloadJwt.put(Constant.PAYLOAD_EMAIL, shop.getEmail());

        String accessToken = JwtUtils.generateToken(payloadJwt, Constant.TOKEN_EXPIRE_TIME);
        String refreshToken = JwtUtils.generateToken(payloadJwt, Constant.REFRESH_TOKEN_EXPIRE_TIME);

        return new LoginResponse(shop.getId(),shop.getName(),shop.getEmail(),accessToken,refreshToken);

    }
}
