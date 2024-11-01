package com.example.FoodApp.Request;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.List;

@Data
public class AddCartItemRequest {
    private ObjectId foodId;
    private int quantity;
    private List<String> ingredients;



}
