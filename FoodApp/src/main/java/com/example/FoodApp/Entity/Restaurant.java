package com.example.FoodApp.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    private ObjectId id;

    private String name;
    private User owner;
    private String cuisineType;
    private String description;
    private Address address;
    private ContactInfo contactInfo;
    private String openingHours;

    @DBRef
    private List<Order> orders = new ArrayList<>();

    private List<String>images;
    private LocalDateTime registrationDate;

    private boolean open;

    @DBRef
    @JsonIgnore
    private List<Food> foods = new ArrayList<>();
}
