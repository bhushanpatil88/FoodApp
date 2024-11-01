package com.example.FoodApp.Request;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class IngredientItemRequest {
    private String name;
    private ObjectId categoryId;
    private ObjectId restaurantId;
}
