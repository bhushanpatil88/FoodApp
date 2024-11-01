package com.example.FoodApp.Request;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class IngredientCategoryRequest {
    private String name;
    private ObjectId restaurantId;
}
