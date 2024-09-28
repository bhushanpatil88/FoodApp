package com.example.FoodApp.Repository;

import com.example.FoodApp.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Object> {
    public User findByEmail(String email);

}
