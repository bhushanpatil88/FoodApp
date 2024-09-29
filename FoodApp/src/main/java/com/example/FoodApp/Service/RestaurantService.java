package com.example.FoodApp.Service;

import com.example.FoodApp.Entity.Address;
import com.example.FoodApp.Repository.AddressRepository;
import com.example.FoodApp.Repository.RestaurantRepository;
import com.example.FoodApp.Repository.UserRepository;
import com.example.FoodApp.Request.CreateRestaurantRequest;
import com.example.FoodApp.Entity.Restaurant;
import com.example.FoodApp.Entity.User;
import com.example.FoodApp.dto.RestaurantDto;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;



    public Restaurant createRestaurant(CreateRestaurantRequest req, User user){
        Address address = addressRepository.save(req.getAddress());

        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setContactInfo(req.getContactInfo());
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setDescription(req.getDescription());
        restaurant.setName(req.getName());
        restaurant.setImages(req.getImages());
        restaurant.setOpeningHours(req.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);

        return restaurantRepository.save(restaurant);
    }

    public Restaurant updateRestaurant(ObjectId restaurantId, CreateRestaurantRequest updateRestaurant) throws Exception{
        Restaurant restaurant = findRestaurantById(restaurantId);
        if(restaurant.getCuisineType()!=null){
            restaurant.setCuisineType(updateRestaurant.getCuisineType());
        }
        if(restaurant.getDescription()!=null){
            restaurant.setDescription(updateRestaurant.getDescription());
        }
        if(restaurant.getName()!=null){
            restaurant.setName(updateRestaurant.getName());
        }

        return restaurantRepository.save(restaurant);
    }

    public void deleteRestaurant(ObjectId restaurantId)throws Exception{
        Restaurant restaurant = findRestaurantById(restaurantId);
        restaurantRepository.delete(restaurant);
    }

    public List<Restaurant> getAllRestaurant(){
        return restaurantRepository.findAll();
    }

    public List<Restaurant>searchRestaurant(String keyword){
         return restaurantRepository.findBySearchQuery(keyword);
    }

    public Restaurant findRestaurantById(ObjectId id) throws Exception{
        Optional<Restaurant> opt = restaurantRepository.findById(id);
        if(opt.isEmpty()){
            throw new Exception("Restaurant not found with id : " + id);
        }
        return opt.get();
    }

    public Restaurant getRestaurantByUserId(ObjectId userId)throws Exception{
        Restaurant restaurant = restaurantRepository.findbyOwnerId(userId);
        if(restaurant == null){
            throw new Exception("Restaurant not found with owner id : " + userId);
        }
        
        return restaurant;
    }
    public RestaurantDto addToFavorites(ObjectId restId, User user)throws Exception{
        Restaurant restaurant = findRestaurantById(restId);
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setDescription(restaurant.getDescription());
        restaurantDto.setImages(restaurant.getImages());
        restaurantDto.setTitle(restaurant.getName());
        restaurantDto.setId(restId);

        if(user.getFavorites().contains(restaurantDto)){
            user.getFavorites().remove(restaurantDto);
        }
        else user.getFavorites().add(restaurantDto);

        userRepository.save(user);

        return restaurantDto;
    }

    public Restaurant updateRestaurantStatus(ObjectId restId) throws Exception {
        Restaurant restaurant = findRestaurantById(restId);
        restaurant.setOpen(!restaurant.isOpen());

        return restaurantRepository.save(restaurant);
    }
}