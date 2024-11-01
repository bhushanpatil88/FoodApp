package com.example.FoodApp.Service;

import com.example.FoodApp.Entity.Category;
import com.example.FoodApp.Entity.Food;
import com.example.FoodApp.Entity.Restaurant;
import com.example.FoodApp.Repository.FoodRepository;
import com.example.FoodApp.Request.CreateFoodRequest;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;
    public Food createFood(CreateFoodRequest createFoodRequest, Category category, Restaurant restaurant){
        Food food = new Food();
        food.setFoodCategory(category);
        food.setRestaurant(restaurant);
        food.setDescription(createFoodRequest.getDescription());
        food.setImages(createFoodRequest.getImages());
        food.setName(createFoodRequest.getName());
        food.setPrice(createFoodRequest.getPrice());
        food.setIngredients(createFoodRequest.getIngredients());
        food.setSeasonal(createFoodRequest.isSeasonal());
        food.setVegetarian(createFoodRequest.isVegetarian());

        Food savedFood = foodRepository.save(food);
        restaurant.getFoods().add(savedFood);
        return foodRepository.save(savedFood);
    }

    public void deleteFood(ObjectId foodId) throws  Exception{
        Food food = findFoodById(foodId);
        food.setRestaurant(null);
        foodRepository.delete(food);
    }

    public List<Food> getRestaurantFood(ObjectId restaurantId,boolean isVegetarian,
                                        boolean isNonVeg, boolean isSeasonal,
                                        String foodCategory){
        return foodRepository.findByRestaurantId(restaurantId);
    }
    public List<Food> searchFood(String keyword){
        return null;
    }
    public Food findFoodById(ObjectId id)throws  Exception{
        return null;
    }
    public Food updateAvailabilityStatus(ObjectId foodId)throws Exception{
        return null;
    }
}
