package com.example.FoodApp.Repository;

import com.example.FoodApp.Entity.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address, Object> {
}
