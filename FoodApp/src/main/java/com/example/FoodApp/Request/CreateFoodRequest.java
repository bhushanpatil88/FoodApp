package com.example.FoodApp.Request;

import com.example.FoodApp.Entity.Category;
import com.example.FoodApp.Entity.Ingredient;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Data
public class CreateFoodRequest {
    private String name;
    private String description;
    private Long price;
    private Category category;
    private List<String> images;

    private ObjectId restaurantId;
    private boolean vegetarian;
    private boolean seasonal;
    @DBRef
    private List<Ingredient> ingredients;

}
