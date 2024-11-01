package com.example.FoodApp.Service;

import com.example.FoodApp.Entity.Cart;
import com.example.FoodApp.Entity.CartItem;
import com.example.FoodApp.Entity.Food;
import com.example.FoodApp.Entity.User;
import com.example.FoodApp.Repository.CartItemRepository;
import com.example.FoodApp.Repository.CartRepository;
import com.example.FoodApp.Repository.FoodRepository;
import com.example.FoodApp.Request.AddCartItemRequest;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private FoodService foodService;

    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.findFoodById(req.getFoodId());
        Cart cart = cartRepository.findByCustomerId(user.getId());

        for(CartItem cartItem: cart.getItems()){
            if(cartItem.getFood().equals(food)){
                int newQuantity = cartItem.getQuantity() + req.getQuantity();
                return updateCartItemQuantity(cartItem.getId(), newQuantity);
            }
        }
        CartItem cartItem = new CartItem();
        cartItem.setFood(food);
        cartItem.setCart(cart);
        cartItem.setQuantity(req.getQuantity());
        cartItem.setIngredients(req.getIngredients());
        cartItem.setTotalPrice(req.getQuantity() * food.getPrice());
        CartItem savedCartItem = cartItemRepository.save(cartItem);
        cart.getItems().add(savedCartItem);
        return savedCartItem;
    }

    public CartItem updateCartItemQuantity(ObjectId cartItemId, int Quantity) throws Exception {
        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
        if(cartItem.isEmpty()){
            throw new Exception("Cart Item not found");
        }
        CartItem item = cartItem.get();
        item.setQuantity(Quantity);
        item.setTotalPrice(item.getFood().getPrice() * Quantity);
        return cartItemRepository.save(item);
    }

    public Cart removeItemFromCart(ObjectId cartItemId, String jwt)throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartRepository.findByCustomerId(user.getId());
        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
        if(cartItem.isEmpty()){
            throw new Exception("Cart Item not found");
        }
        CartItem item = cartItem.get();
        cart.getItems().remove(item);
        return cartRepository.save(cart);
    }

    public Long calculateCartTotal(Cart cart) throws Exception{
        Long total = 0L;
        for(CartItem cartItem: cart.getItems()){
            total += cartItem.getFood().getPrice() * cartItem.getQuantity();
        }
        return total;
    }

    public Cart findCartById(Long cartId)throws Exception{
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if(optionalCart.isEmpty()){
            throw new Exception("Cart not found with id" + cartId);
        }
        return optionalCart.get();
    }

    public Cart findCartByUser(String jwt)throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        return cartRepository.findByCustomerId(user.getId());
    }

    public Cart clearCart(String jwt)throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = findCartByUser(jwt);
        cart.getItems().clear();
        return cartRepository.save(cart);
    }
}
