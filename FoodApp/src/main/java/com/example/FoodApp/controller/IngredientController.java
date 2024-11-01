package com.example.FoodApp.controller;

import com.example.FoodApp.Entity.IngredientCategory;
import com.example.FoodApp.Entity.IngredientItem;
import com.example.FoodApp.Request.IngredientCategoryRequest;
import com.example.FoodApp.Request.IngredientItemRequest;
import com.example.FoodApp.Service.IngredientService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {
    @Autowired
    private IngredientService ingredientService;

    @PostMapping("/category")
    public ResponseEntity<IngredientCategory> createIngredientCategory(
            @RequestBody IngredientCategoryRequest req
            ) throws Exception {
        IngredientCategory item = ingredientService.createIngredientCategory(req.getName(),req.getRestaurantId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<IngredientItem> createIngredientCategory(
            @RequestBody IngredientItemRequest req
    ) throws Exception {
        IngredientItem item = ingredientService.createIngredientItem(req.getRestaurantId(),req.getName(), req.getCategoryId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<IngredientItem> updateIngredientStock(
            @PathVariable ObjectId id
            )throws  Exception{
        IngredientItem item = ingredientService.updateStock(id);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @GetMapping("restaurant/{id}")
    public ResponseEntity<List<IngredientItem>> getRestaurantIngredients(
            @PathVariable ObjectId id
    )throws  Exception{
        List<IngredientItem> items = ingredientService.findRestaurantsIngredients(id);
        return new ResponseEntity<>(items, HttpStatus.CREATED);
    }

    @GetMapping("restaurant/{id}/category")
    public ResponseEntity<List<IngredientCategory>> getRestaurantIngredientCategory(
            @PathVariable ObjectId id
    )throws  Exception{
        List<IngredientCategory> items = ingredientService.findIngredientCategoryByRestaurantId(id);
        return new ResponseEntity<>(items, HttpStatus.CREATED);
    }
}
