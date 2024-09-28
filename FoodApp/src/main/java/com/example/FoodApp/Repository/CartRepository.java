package com.example.FoodApp.Repository;

import com.example.FoodApp.Entity.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart, Object> {
}
