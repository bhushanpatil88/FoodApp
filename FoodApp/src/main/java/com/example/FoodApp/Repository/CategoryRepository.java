package com.example.FoodApp.Repository;

import com.example.FoodApp.Entity.Category;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, ObjectId> {
    List<Category> findByRestaurantId(ObjectId id);
}
