package com.app.gaz.springbootrestfulwebservices.service.impl;

import com.app.gaz.springbootrestfulwebservices.dto.UserDto;
import com.app.gaz.springbootrestfulwebservices.entity.User;
import com.app.gaz.springbootrestfulwebservices.mapper.UserMapper;
import com.app.gaz.springbootrestfulwebservices.repository.UserRepository;
import com.app.gaz.springbootrestfulwebservices.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {

        /** 1. Convert UserDto into User JPA Entity */
        User user = UserMapper.mapToUser(userDto);

        User savedUser = userRepository.save(user);

        /** 2. Convert User JPA entity to UserDto */
        UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);

        return savedUserDto;
    }

    @Override
    public User getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.get();
    }

    @Override
    public List<User> getByEmail(String email) {

        return userRepository.getByEmail("%" + email + "%");
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        User exstingUser = userRepository.findById(user.getId()).get();// save if.IsNull because: class Optional<T>

        BeanUtils.copyProperties(user, exstingUser);
        User updateUser = userRepository.save(exstingUser);

        return updateUser;

        /** PARA NO PASAR PARAMETRO POR PARAMETRO SE USA EL : BeanUtils.copyProperties
         *
         *  Se utiliza cuando se ve a actulizar ciertos datos:
         *  exstingUser.setFirstName(user.getFirstName());
         *         exstingUser.setLastName(user.getLastName());
         *         exstingUser.setEmail(user.getEmail());
         *         User updateUser = userRepository.save(exstingUser);
         */


    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
