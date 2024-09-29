package com.example.FoodApp.Entity;

import com.example.FoodApp.dto.RestaurantDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private ObjectId id;

    private String fullName;
    private String email;

    @JsonIgnore
    private String password;
    private USER_ROLE roles;

    @DBRef
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();

    private List<RestaurantDto>favorites = new ArrayList<>();

    @DBRef
    @JsonIgnore
    private List<Address> addresses = new ArrayList<>();
}
