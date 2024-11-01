package com.example.FoodApp.controller;

import com.example.FoodApp.Entity.Food;
import com.example.FoodApp.Entity.User;
import com.example.FoodApp.Service.FoodService;
import com.example.FoodApp.Service.RestaurantService;
import com.example.FoodApp.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;


    @GetMapping("/search")
    private ResponseEntity<List<Food>> searchFood(@RequestParam String name,
                                            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Food> foods = foodService.searchFood(name);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    private ResponseEntity<List<Food>> getRestaurantFood(@RequestParam boolean vegetarian,
                                                  @RequestParam boolean seasonal,
                                                  @RequestParam boolean nonVeg,
                                                  @RequestParam(required = false) String foodCategory,
                                                  @PathVariable ObjectId id,
                                                  @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Food> foods = foodService.getRestaurantFood(id,vegetarian,nonVeg,seasonal,foodCategory);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
}
