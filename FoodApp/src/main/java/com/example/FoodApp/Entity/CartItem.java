package com.example.FoodApp.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class CartItem {
    @Id
    private ObjectId id;
    @JsonIgnore
    private Cart cart;

    private Food food;
    private int quantity;
    private List<String> ingredients = new ArrayList<>();
    private Long totalPrice;
}
