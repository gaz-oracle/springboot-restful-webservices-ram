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
import java.util.stream.Collectors;

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
    public UserDto getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public List<User> getByEmail(String email) {

        return userRepository.getByEmail("%" + email + "%");
    }

    @Override
    public List<UserDto> getAllUsers() {

       List<User> users= userRepository.findAll();

        return users.stream().map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User exstingUser = userRepository.findById(userDto.getId()).get();// save if.IsNull because: class Optional<T>

        BeanUtils.copyProperties(userDto, exstingUser);
        User updateUser = userRepository.save(exstingUser);

        return UserMapper.mapToUserDto(updateUser);

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
