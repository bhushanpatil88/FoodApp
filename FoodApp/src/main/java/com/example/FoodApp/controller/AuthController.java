package com.example.FoodApp.controller;

import com.example.FoodApp.Entity.Cart;
import com.example.FoodApp.Entity.User;
import com.example.FoodApp.Repository.CartRepository;
import com.example.FoodApp.Repository.UserRepository;
import com.example.FoodApp.Response.AuthResponse;
import com.example.FoodApp.Service.UserDetailsServiceImpl;
import com.example.FoodApp.config.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private CartRepository cartRepository;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {
        User userinDB = userRepository.findByEmail(user.getEmail());
        if(userinDB != null){
            throw new Exception("Email already used by another account");
        }

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setFullname(user.getFullname());
        newUser.setRoles(user.getRoles());
        userRepository.save(newUser);

        Cart cart = new Cart();
        cart.setCustomer(newUser);
        cartRepository.save(cart);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Register successful");
        authResponse.setRole(user.getRoles());

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }
}
