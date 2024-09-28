package com.example.FoodApp.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    private ObjectId id;

    private Food food;
    private int quantity;
    private Long totalPrice;
    private List<String> ingredients = new ArrayList<>();
}
