package com.example.FoodApp.Request;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class UpdateCartItemRequest {
    private ObjectId cartItemId;
    private int quantity;
}
