package com.app.gaz.springbootrestfulwebservices.mapper;

import com.app.gaz.springbootrestfulwebservices.dto.UserDto;
import com.app.gaz.springbootrestfulwebservices.entity.User;

public class UserMapper {
    /**
     * 1. Dentro de una clase UserMapper, vamos a crear dos métodos estáticos para convertir la entidad User en UserDto y
     * UserDto a entidad User.
     * 2.un método estático que es el método mapToUserDto Asignará la entidad JPA del usuario a UserDto
     */

    /**
     * 2
     * Convert User JPA Entity into UserDto
     */
    public static UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
        return userDto;
    }

    /**
     * convert UserDto into User JPA Entity
     */
    public static User mapToUser(UserDto userDto) {
        User user = new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
        );
        return user;
    }
}
