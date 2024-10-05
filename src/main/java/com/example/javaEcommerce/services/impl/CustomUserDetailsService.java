package com.example.javaEcommerce.services.impl;

import com.example.javaEcommerce.dto.CustomUserDetails;
import com.example.javaEcommerce.models.ShopModel;
import com.example.javaEcommerce.repositoryes.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ShopRepository shopRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<ShopModel> shopModel = shopRepository.findFirstByEmail(email);
        if (shopModel.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Not found user : %s", email));
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        for (String role : shopModel.get().getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        }

        return new CustomUserDetails(shopModel.get(), authorities);
    }
}
