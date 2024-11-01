package com.example.FoodApp.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Food {
    @Id
    private ObjectId id;
    private String name;
    private String description;
    private Long price;

    private Category foodCategory;
    private List<String> images = new ArrayList<>();
    private boolean available;
    private Restaurant restaurant;
    private boolean isVegetarian;
    private boolean isSeasonal;

    @DBRef
    private List<IngredientItem> ingredients = new ArrayList<>();

    private Date createdAt;

}
