package com.example.javaEcommerce.repositoryes;

import com.example.javaEcommerce.models.KeyTokenModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface KeyTokenRepository extends MongoRepository<KeyTokenModel, String> {

}
