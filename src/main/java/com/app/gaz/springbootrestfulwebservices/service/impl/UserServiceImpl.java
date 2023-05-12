package com.app.gaz.springbootrestfulwebservices.service.impl;

import com.app.gaz.springbootrestfulwebservices.dto.UserDto;
import com.app.gaz.springbootrestfulwebservices.entity.User;
import com.app.gaz.springbootrestfulwebservices.mapper.UserMapper;
import com.app.gaz.springbootrestfulwebservices.repository.UserRepository;
import com.app.gaz.springbootrestfulwebservices.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    public UserDto createUser(UserDto userDto) {

        /** 1. Convert UserDto into User JPA Entity
         *   User user = UserMapper.mapToUser(userDto);
         * -----------------------------------------------------------------------------------
         * Vamos a utilar la API de mapa modelmapper para convertir UserDto en una
         *  entidad JPA de USER.   ORIGIN     DESTINIO            */
        User user = modelMapper.map(userDto, User.class);



        User savedUser = userRepository.save(user);

        /** 2. Convert User JPA entity to UserDto
         *  UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
         *  -----------------------------------------------------------------------------------
         *  Vamos a utilarel método de mapa de puntos modelmapper para convertir la entidad JPA
         *  de usuario en UserDto.               ORIGIN     DESTINIO             */
        UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);


        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        //return UserMapper.mapToUserDto(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<User> getByEmail(String email) {

        return userRepository.getByEmail("%" + email + "%");
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> users = userRepository.findAll();

        /**
         * return users.stream().map(UserMapper::mapToUserDto)
         .collect(Collectors.toList());
         */
        return users.stream().map((user -> modelMapper.map(user, UserDto.class)))
                .collect(Collectors.toList());

    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User exstingUser = userRepository.findById(userDto.getId()).get();// save if.IsNull because: class Optional<T>

        BeanUtils.copyProperties(userDto, exstingUser);
        User updateUser = userRepository.save(exstingUser);
        // return UserMapper.mapToUserDto(updateUser);
        return modelMapper.map(updateUser, UserDto.class);

    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}


/**
 * manejado la ID atraves de lombok @AllArgsConstructor
 *
 * @Autowired public UserServiceImpl(ModelMapper modelMapper) {
 * this.modelMapper = modelMapper;
 * }
 * @Autowired public UserServiceImpl(UserRepository userRepository) {
 * this.userRepository = userRepository;
 * }
 */