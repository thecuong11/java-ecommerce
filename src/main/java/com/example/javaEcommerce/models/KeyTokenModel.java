package com.example.javaEcommerce.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "Keys" )
public class KeyTokenModel {
    @Id
    private String id;

    private String user;

    private String publicKey;

    private String privateKey;

    private List<String> refreshToken = new ArrayList<String>();
}
