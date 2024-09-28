package com.example.FoodApp.dto;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

@Data
public class RestaurantDto {
    private ObjectId id;
    private String title;
    private List<String> images = new ArrayList<>();
    private String description;

}
