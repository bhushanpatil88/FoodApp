package com.example.FoodApp.Repository;

import com.example.FoodApp.Entity.IngredientCategory;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IngredientCategoryRepository extends MongoRepository<IngredientCategory, ObjectId> {
    List<IngredientCategory> findByRestaurantId(ObjectId id);
}
