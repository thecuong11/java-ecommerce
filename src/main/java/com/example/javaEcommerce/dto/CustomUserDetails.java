package com.example.javaEcommerce.dto;

import com.example.javaEcommerce.models.ShopModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Getter
@Setter
public class CustomUserDetails implements UserDetails {

    private final ShopModel shop;

    private final Set<GrantedAuthority> customAuthorities;

    public CustomUserDetails(ShopModel shop, Set<GrantedAuthority> customAuthorities) {
        this.shop = shop;
        this.customAuthorities = customAuthorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.customAuthorities;
    }

    @Override
    public String getPassword() {
        return shop.getPassword();
    }

    @Override
    public String getUsername() {
        return shop.getEmail();
    }
}
