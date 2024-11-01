package com.example.FoodApp.Repository;

import com.example.FoodApp.Entity.CartItem;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartItemRepository extends MongoRepository<CartItem, ObjectId> {
}
