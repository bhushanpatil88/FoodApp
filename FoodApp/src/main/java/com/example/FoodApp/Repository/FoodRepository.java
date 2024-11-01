package com.example.FoodApp.Repository;

import com.example.FoodApp.Entity.Food;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodRepository extends MongoRepository<Food, ObjectId> {
    List<Food> findByRestaurantId(ObjectId restaurantId);

    @Query("{ $or: [ { 'name': { $regex: ?0, $options: 'i' } }, { 'foodCategory.name': { $regex: ?0, $options: 'i' } } ] }")
    List<Food> searchFood(String keyword);
}
