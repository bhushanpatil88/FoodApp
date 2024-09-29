package com.example.FoodApp.Service;

import com.example.FoodApp.Entity.User;
import com.example.FoodApp.Repository.UserRepository;
import com.example.FoodApp.config.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserRepository userRepository;

    public User findUserByJwtToken(String jwt) throws Exception{
        String email = jwtProvider.getEmailFromToken(jwt);
        User user = findUserByEmail(email);
        return user;
    }

    public User findUserByEmail(String email) throws  Exception{
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new Exception("User not found");
        }
        return user;
    }
}
