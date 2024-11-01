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
import java.util.Optional;
import java.util.stream.Collectors;

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
        List<Food> foods =  foodRepository.findByRestaurantId(restaurantId);
        if(isVegetarian){
            foods = filterByVegetarian(foods, isVegetarian);
        }
        if(isNonVeg){
            foods = filterByNonVeg(foods,isNonVeg);
        }
        if(isSeasonal){
            foods = filterBySeasonal(foods,isSeasonal);
        }
        if(foodCategory!=null && !foodCategory.equals("")){
            foods = filterByCategory(foods, foodCategory);
        }

        return foods;
    }

    private List<Food> filterByNonVeg(List<Food> foods, boolean isNonVeg) {
        return foods.stream().filter(food -> !food.isVegetarian()).collect(Collectors.toList());
    }

    private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
        return  foods.stream().filter(food -> food.isSeasonal()==isSeasonal).collect(Collectors.toList());
    }

    private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
        return foods.stream().filter(food -> {
            if(food.getFoodCategory()!=null){
                return food.getFoodCategory().getName().equals(foodCategory);
            }
            return false;
        }).collect(Collectors.toList());
    }

    private List<Food> filterByVegetarian(List<Food> foods, boolean isVegetarian) {
        return foods.stream().filter(food -> food.isVegetarian()==isVegetarian).collect(Collectors.toList());
    }

    public List<Food> searchFood(String keyword){
        return foodRepository.searchFood(keyword);
    }
    public Food findFoodById(ObjectId id)throws  Exception{
        Optional<Food> food = foodRepository.findById(id);
        if(food.isEmpty()){
            throw new Exception("Food not exist...");
        }
        return food.get();
    }
    public Food updateAvailabilityStatus(ObjectId foodId)throws Exception{
        Food food = findFoodById(foodId);
        food.setAvailable(!food.isAvailable());
        return foodRepository.save(food);
    }
}
