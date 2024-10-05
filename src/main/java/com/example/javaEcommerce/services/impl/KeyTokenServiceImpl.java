package com.example.javaEcommerce.services.impl;

import com.example.javaEcommerce.models.KeyTokenModel;
import com.example.javaEcommerce.services.KeyTokenService;

import java.util.Optional;

public class KeyTokenServiceImpl implements KeyTokenService {

    @Override
    public void createKeyToken(String userId, String publicKey) {
        KeyTokenModel kToken = new KeyTokenModel();
        kToken.setId(userId);
        kToken.setPublicKey(publicKey);
    }
}
