package com.example.FoodApp.controller;

import com.example.FoodApp.Entity.Food;
import com.example.FoodApp.Entity.Restaurant;
import com.example.FoodApp.Entity.User;
import com.example.FoodApp.Request.CreateFoodRequest;
import com.example.FoodApp.Response.MessageResponse;
import com.example.FoodApp.Service.FoodService;
import com.example.FoodApp.Service.RestaurantService;
import com.example.FoodApp.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;


    @PostMapping
    private ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req,
                                                @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.findRestaurantById(req.getRestaurantId());
        Food food = foodService.createFood(req, req.getCategory(), restaurant);
        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<MessageResponse> deleteFood(@PathVariable ObjectId id,
                                                       @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        foodService.deleteFood(id);
        MessageResponse res = new MessageResponse();
        res.setMessage("Food Deleted Successfully");
        return new ResponseEntity<>(res,HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Food> updateFoodAvailability(@PathVariable ObjectId id,
                                                       @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.updateAvailabilityStatus(id);
        return new ResponseEntity<>(food,HttpStatus.NO_CONTENT);
    }
}
