package com.example.photobook.Controller;

import com.example.photobook.Model.UserModel;
import com.example.photobook.Response.Response;
import com.example.photobook.Service.UserService;
import com.example.photobook.Validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Photobooking")

public class UserController {
    @Autowired
    private UserService userService;


    // Signup API
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserModel userModel) {

        try {
            userService.registerUser(userModel);
            return Response.generate("Registered Sucessfully", null, HttpStatus.OK);
        } catch (Validation validation) {
            return Response.generate(validation.getError(), "Failed", HttpStatus.BAD_REQUEST);
        }
    }

    // Login API
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserModel userModel) {

        try {
            userService.authenticateUser(userModel.getEmail(), userModel.getPassword());
            return Response.generate("login Sucessfully", null, HttpStatus.OK);
        } catch (Validation validation) {
            return Response.generate(validation.getError(), "Failed", HttpStatus.BAD_REQUEST);
        }

    }
}