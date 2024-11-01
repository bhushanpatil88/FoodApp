package com.example.FoodApp.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientItem {

    @Id
    private ObjectId id;

    private String name;

    private IngredientCategory ingredientCategory;
    @JsonIgnore
    private Restaurant restaurant;
    private boolean inStoke = true;

}
