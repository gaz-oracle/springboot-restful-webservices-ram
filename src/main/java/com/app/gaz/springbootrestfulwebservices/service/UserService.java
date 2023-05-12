package com.app.gaz.springbootrestfulwebservices.service;

import com.app.gaz.springbootrestfulwebservices.dto.UserDto;
import com.app.gaz.springbootrestfulwebservices.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserDto createUser(UserDto userDto);

    User getUserById(Long userId);

    List<User> getByEmail(String email);

    List<User> getAllUsers();

    User updateUser(User user);

    void deleteUser(Long userId);

}
