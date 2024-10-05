package com.example.javaEcommerce.repositoryes;

import com.example.javaEcommerce.models.ShopModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ShopRepository extends MongoRepository<ShopModel, String> {
    Optional<ShopModel> findFirstByEmail(String email);
}
