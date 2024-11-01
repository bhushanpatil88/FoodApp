package com.example.FoodApp.Service;

import com.example.FoodApp.Entity.Category;
import com.example.FoodApp.Entity.Restaurant;
import com.example.FoodApp.Repository.CategoryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatergoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RestaurantService restaurantService;

    public Category createCategory(String name, ObjectId userId) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantByUserId(userId);
        Category category = new Category();
        category.setName(name);
        category.setRestaurant(restaurant);
        return categoryRepository.save(category);
    }

    public List<Category> findCategoryByRestaurantId(ObjectId userId)throws Exception{
        Restaurant restaurant = restaurantService.getRestaurantByUserId(userId);
        return categoryRepository.findByRestaurantId(restaurant.getId());
    }

    public Category findCategoryById(ObjectId id)throws Exception{
        Optional<Category> category = categoryRepository.findById(id) ;
        if(category.isEmpty()){
            throw new Exception("Category not found");
        }
        return category.get();
    }

}
