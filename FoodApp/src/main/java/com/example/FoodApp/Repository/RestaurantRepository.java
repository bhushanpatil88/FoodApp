package com.example.FoodApp.Repository;

import com.example.FoodApp.Entity.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RestaurantRepository extends MongoRepository<Restaurant,Object> {
    @Query("{ '$or': [ { 'name': { '$regex': ?0, '$options': 'i' } }, { 'cuisineType': { '$regex': ?0, '$options': 'i' } } ] }")
    List<Restaurant> findBySearchQuery(String query);

    Restaurant findbyOwnerId(Object ownerId);
}
