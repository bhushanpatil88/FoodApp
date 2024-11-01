package com.example.FoodApp.Service;

import com.example.FoodApp.Entity.IngredientCategory;
import com.example.FoodApp.Entity.IngredientItem;
import com.example.FoodApp.Entity.Restaurant;
import com.example.FoodApp.Repository.IngredientCategoryRepository;
import com.example.FoodApp.Repository.IngredientItemRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class IngredientService {

    @Autowired
    private IngredientItemRepository ingredientItemRepository;

    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    private RestaurantService restaurantService;

    public IngredientCategory createIngredientCategory(String name, ObjectId restaurantId) throws Exception{
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory category = new IngredientCategory();
        category.setRestaurant(restaurant);
        category.setName(name);
        return ingredientCategoryRepository.save(category);
    }

    public IngredientCategory findIngredientCategoryById(ObjectId Id) throws Exception{
        Optional<IngredientCategory> category = ingredientCategoryRepository.findById(Id);
        if(category.isEmpty()){
            throw new Exception("Ingredient Category not found");
        }
        return category.get();
    }

    public List<IngredientCategory> findIngredientCategoryByRestaurantId(ObjectId id) throws Exception{
        return ingredientCategoryRepository.findByRestaurantId(id);
    }

    public IngredientItem createIngredientItem(ObjectId restaurantId, String ingredientName, ObjectId categoryId) throws Exception{
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory category = findIngredientCategoryById(categoryId);
        IngredientItem item = new IngredientItem();
        item.setRestaurant(restaurant);
        item.setName(ingredientName);
        item.setIngredientCategory(category);
        category.getIngredients().add(item);
        return ingredientItemRepository.save(item);

    }

    public List<IngredientItem> findRestaurantsIngredients(ObjectId restaurantId){
        return ingredientItemRepository.findByRestaurantId(restaurantId);
    }

    public IngredientItem updateStock(ObjectId id) throws Exception{
        Optional<IngredientItem> ingredientItem = ingredientItemRepository.findById(id);
        if(ingredientItem.isEmpty()){
            throw new Exception("Ingredient not found");
        }
        IngredientItem newIngredientItem = ingredientItem.get();
        newIngredientItem.setInStoke(!newIngredientItem.isInStoke());
        return ingredientItemRepository.save(newIngredientItem);
    }
}
