package com.app.gaz.springbootrestfulwebservices.service;

import com.app.gaz.springbootrestfulwebservices.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User createUser(User user);

    User getUserById(Long userId);

}
/** 3
 * Definamos el método en una interfaz UserService, Así que demos el tipo de retorno
 * del método como la @Entity User y demos el nombre del método como createUser() y pasemos User
 * como Instancia de entidad como argumento de método
 * @param user
 * @return
 */