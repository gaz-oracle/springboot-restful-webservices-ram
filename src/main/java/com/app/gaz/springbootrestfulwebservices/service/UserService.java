package com.app.gaz.springbootrestfulwebservices.service;

import com.app.gaz.springbootrestfulwebservices.dto.UserDto;
import com.app.gaz.springbootrestfulwebservices.entity.User;
import com.app.gaz.springbootrestfulwebservices.exception.EmailAlredyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserDto createUser(UserDto userDto) throws EmailAlredyExistsException;

    UserDto getUserById(Long userId);

    UserDto updateUser(UserDto userDto);

    void deleteUser(Long userId);

    List<UserDto> getAllUsers();

    /** GALAXY - CUSTOMIZABLE */
    List<User> getByEmail(String email);

}
