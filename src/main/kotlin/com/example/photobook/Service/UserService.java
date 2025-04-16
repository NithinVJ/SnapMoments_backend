package com.example.photobook.Service;

import com.example.photobook.Entity.UserEntity;
import com.example.photobook.Model.UserModel;
import com.example.photobook.Repository.UserRepository;
import com.example.photobook.Validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // matches the @Bean type

    public void registerUser(UserModel userModel) {


        if (userRepository.findByEmail(userModel.getEmail()).isPresent()) {
            List<String> error = new ArrayList<>();
            throw new Validation(error, "User with this email already exists.");
        }


        UserEntity user = new UserEntity();
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setEmail(userModel.getEmail());
        user.setPassword(passwordEncoder.encode(userModel.getPassword())); // Encrypt password
        user.setAccountType(userModel.getAccountType());

        userRepository.save(user);

    }

// Login
    public UserEntity authenticateUser(String email, String password) {
            List<String>err=new ArrayList<>();
            Optional<UserEntity> userOpt = userRepository.findByEmail(email);
            if (userOpt.isPresent()) {
                UserEntity user = userOpt.get();
                if (passwordEncoder.matches(password, user.getPassword())) {
                    return user;
                }
            }
            throw new Validation(err,"Invalid email or password.");
    }

}
















//public void registerUser(UserModel userModel) {
//
//    // Signup
//
//    if (userRepository.findByEmail(userModel.getEmail()).isPresent()) {
//        List<String>error=new ArrayList<>();
//        throw new Validation(error,"User with this email already exists.");
//    }
//    if (userModel.getFirstName() == null || userModel.getLastName() == null || userModel.getEmail() == null) {
//        throw new Validation(new ArrayList<>(), "First name, last name, and email cannot be null.");
//    }
//    UserEntity user = new UserEntity();
//    user.setFirstName(userModel.getFirstName());
//    user.setLastName(userModel.getLastName());
//    user.setEmail(userModel.getEmail());
//    user.setPassword(passwordEncoder.encode(userModel.getPassword())); // Encrypt password
//    user.setAccountType(userModel.getAccountType());
//
//    userRepository.save(user);
//}