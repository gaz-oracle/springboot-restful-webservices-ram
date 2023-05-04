package com.app.gaz.springbootrestfulwebservices.controller;

import com.app.gaz.springbootrestfulwebservices.entity.User;
import com.app.gaz.springbootrestfulwebservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Build create User REST API:
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
      User savedUser =  userService.createUser(user);
      return new ResponseEntity<>(savedUser, HttpStatus.CREATED); //8:09
    }

}
