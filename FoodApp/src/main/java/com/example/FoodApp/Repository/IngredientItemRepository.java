package com.example.FoodApp.Repository;

import com.example.FoodApp.Entity.IngredientItem;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IngredientItemRepository extends MongoRepository<IngredientItem, ObjectId> {
    List<IngredientItem> findByRestaurantId(ObjectId id);
}
