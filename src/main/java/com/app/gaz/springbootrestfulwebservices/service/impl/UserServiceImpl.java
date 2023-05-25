package com.app.gaz.springbootrestfulwebservices.service.impl;

import com.app.gaz.springbootrestfulwebservices.dto.UserDto;
import com.app.gaz.springbootrestfulwebservices.entity.User;
import com.app.gaz.springbootrestfulwebservices.exception.EmailAlredyExistsException;
import com.app.gaz.springbootrestfulwebservices.exception.ResourceNotFoundException;
import com.app.gaz.springbootrestfulwebservices.mapper.AutoUserMapper;
import com.app.gaz.springbootrestfulwebservices.repository.UserRepository;
import com.app.gaz.springbootrestfulwebservices.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) throws EmailAlredyExistsException {

        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if(optionalUser.isPresent()){
            throw new EmailAlredyExistsException("Email ya Existe para el Usario");
        }

        User user = AutoUserMapper.MAPPER.mapTouser(userDto);

        User savedUser = userRepository.save(user);

        UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);

        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );

        return AutoUserMapper.MAPPER.mapToUserDto(user);
    }

    @Override
    public List<User> getByEmail(String email) {

        return userRepository.getByEmail("%" + email + "%");
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> users = userRepository.findAll();

        return users.stream().map((user -> AutoUserMapper.MAPPER.mapToUserDto(user)))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User exstingUser = userRepository.findById(userDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userDto.getId())
        );

        BeanUtils.copyProperties(userDto, exstingUser);
        User updateUser = userRepository.save(exstingUser);
        return AutoUserMapper.MAPPER.mapToUserDto(updateUser);
    }

    @Override
    public void deleteUser(Long userId) {
        User exstingUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );

        userRepository.deleteById(userId);
    }
    /**
     * Dentro de este método, podemos escribir la lógica para verificar si el correo electrónico
     * del usuario ya existe en la base de datos.
     */
}

