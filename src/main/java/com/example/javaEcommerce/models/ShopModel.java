package com.example.javaEcommerce.models;

import com.example.javaEcommerce.enums.SesionStatus;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "Shops" )
public class ShopModel {
    @Id
    private String id;

    private String name;

    @Indexed(unique = true)
    private String email;

    private String password;

    private String status = SesionStatus.INACTIVE.toString();

    private Boolean verified = false;

    private List<String> roles = new ArrayList<>();
}
