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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ShopRepository shopRepository;


    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final static List<ShopModel> LIST_USERS = new ArrayList<>();

    public CustomUserDetailsService() {
        ShopModel s1 = new ShopModel();
        s1.setId("id1");
        s1.setEmail("test1@gmail.com");
        s1.setPassword(passwordEncoder.encode("password1"));
        LIST_USERS.add(s1);

        ShopModel s2 = new ShopModel();
        s2.setId("id2");
        s2.setEmail("test2@gmail.com");
        s2.setPassword(passwordEncoder.encode("password2"));
        LIST_USERS.add(s2);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Optional<ShopModel> shopModel = shopRepository.findFirstByEmail(email);
        Optional<ShopModel> shopModel = LIST_USERS.stream().filter(user -> user.getEmail().equals(email)).findFirst();

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
