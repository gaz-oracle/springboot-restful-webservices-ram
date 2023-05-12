package com.app.gaz.springbootrestfulwebservices.service.impl;

import com.app.gaz.springbootrestfulwebservices.dto.UserDto;
import com.app.gaz.springbootrestfulwebservices.entity.User;
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
        /**
         * 1. Convert UserDto into User JPA Entity
         * 2. Guardemos este objeto de entidad de usuario en la base de datos,
         *     mediante el método save() de UserRepository.
         * 3. Este método createUser tiene que devolver el objeto UserDto
         * 4. Así que vamos a convertir este objeto de entidad JPA de usuario en objeto UserDto.
         * 5. la API REST básicamente espera que el usuario guardado en una respuesta Y
         *    este usuario guardado contiene la clave principal, Es por eso que necesitamos
         *    convertir esta entidad User en UserDto y necesitamos regresar como respuesta de este metodo.
         * */

        /** 1. Convert UserDto into User JPA Entity */
        User user = new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
        );
        User savedUser = userRepository.save(user);

        /** 5. Convert User JPA entity to UserDto */
        UserDto savedUserDto = new UserDto(
                savedUser.getId(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getEmail()
        );
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
