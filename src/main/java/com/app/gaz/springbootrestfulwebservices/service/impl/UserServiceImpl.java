package com.app.gaz.springbootrestfulwebservices.service.impl;

import com.app.gaz.springbootrestfulwebservices.entity.User;
import com.app.gaz.springbootrestfulwebservices.repository.UserRepository;
import com.app.gaz.springbootrestfulwebservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        System.out.println("First Name:" + user.getFirstName());
        System.out.println("Last Name:" + user.getLastName());
        System.out.println("Email:" + user.getEmail());

        return userRepository.save(user);//<--***
    }
}
/** 4
 * 1.- Implementemos la interfaz UserService y su(s) método(s).
 * 2.- Inyectemos UserRepository en la clase UserServiceImpl atraves del constructor.
 * 3.- Así que vamos a crear una variable de instancia que es la variable de instancia UserRepository
 * 4.- Vamos a usar la inyección de dependencias basada en constructor para insertar UserRepository en UserServiceImpl.
 * 5.- Necesitamos crear el constrctur con argumento(s), si es un solo argumento se puede omitir el  @Autowired
 *    y usar Lombok, si solo se tiene un constructor parametrizado.
 * 6.- Necesitamos implementar el método createUser(), para llama al UserRepository, que contiene los metodos Jpa,
 *     para el CRUD(save() en este caso).
 * 7.- userRepository.save(user) --> solo se pasa el objeto user, que Guardará el objeto de entidad jpa de usuario en la DB.
 * 8.- Guardará el objeto de entidad jpa de usuario en la base de datos "to packet controller)
 */