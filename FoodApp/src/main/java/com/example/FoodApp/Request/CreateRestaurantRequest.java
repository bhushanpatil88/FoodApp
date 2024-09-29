package com.example.FoodApp.Request;

import com.example.FoodApp.Entity.Address;
import com.example.FoodApp.Entity.ContactInfo;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.List;

@Data
public class CreateRestaurantRequest {
    private ObjectId id;
    private String name;
    private String description;
    private String cuisineType;
    private Address address;
    private ContactInfo contactInfo;
    private String openingHours;
    private List<String> images;
}
