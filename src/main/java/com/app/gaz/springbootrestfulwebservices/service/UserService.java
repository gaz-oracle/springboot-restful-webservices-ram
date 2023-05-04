package com.app.gaz.springbootrestfulwebservices.service;

import com.app.gaz.springbootrestfulwebservices.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User createUser(User user);

}
