package com.app.gaz.springbootrestfulwebservices.service.impl;

import com.app.gaz.springbootrestfulwebservices.entity.User;
import com.app.gaz.springbootrestfulwebservices.repository.UserRepository;
import com.app.gaz.springbootrestfulwebservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    /**
     * getUserById : Recupera el objeto User por ID.
     * Y la API restante llamará internamente a este método getUserById().
     */

    @Override
    public User getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * 1º obtengamos el objeto User existente de la base de datos y luego actualizaremos ese objeto User.
     * 2º Y de nuevo, guardaremos ese objeto User actualizado en la base de datos.
     * 3º Así que llamemos a User.getId() y este método findById() devuelve opcional de tipo User,
     * Así que llamemos a un método get().
     * 4º Ahora, que obtuvimos el objeto exstingUser de la tabla de la base de datos,A continuación, actualizaremos
     * este usuario existente y, a continuación, lo guardaremos de nuevo en la tabla de la base de datos
     * 5º Aquí puedes ver que el método updateUser() tiene un objeto User como argumento de método, y este objeto User
     * Contiene toda la información actualizada enviada por el cliente.
     * 6º A continuación, lo que haremos, actualizaremos toda la información de este objeto User en este User existente.
     * 7º el tipo de return de esta clase Updateuser es User, por lo tanto, este método debe devolver el objeto User actulizado
     * <p>
     * obtuvimos el usuario existente por ID y luego hemos actualizado este usuario existente con nombre, apellido, correo electrónico,
     * y luego hemos guardado este usuario existente en una base de datos y con nombre, apellido, correo electrónico, y luego hemos
     * guardado este usuario existente en una base de datos y a continuación, este método devuelve el objeto User actualizado
     */
    @Override
    public User updateUser(User user) {
        User exstingUser = userRepository.findById(user.getId()).get();
        exstingUser.setFirstName(user.getFirstName());
        exstingUser.setLastName(user.getLastName());
        exstingUser.setEmail(user.getEmail());
        User updateUser = userRepository.save(exstingUser);
        return updateUser;
    }

    /**
     * La API de REST llama internamente a este método de eliminación
     * de usuario.
     * @param userId
     */

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
/**
 * 4
 * 1.- Implementemos la interfaz UserService y su(s) método(s).
 * 2.- Inyectemos UserRepository en la clase UserServiceImpl atraves del constructor.
 * 3.- Así que vamos a crear una variable de instancia que es la variable de instancia UserRepository
 * 4.- Vamos a usar la inyección de dependencias basada en constructor para insertar UserRepository en UserServiceImpl.
 * 5.- Necesitamos crear el constrctur con argumento(s), si es un solo argumento se puede omitir el  @Autowired
 * y usar Lombok, si solo se tiene un constructor parametrizado.
 * 6.- Necesitamos implementar el método createUser(), para llama al UserRepository, que contiene los metodos Jpa,
 * para el CRUD(save() en este caso).
 * 7.- userRepository.save(user) --> solo se pasa el objeto user, que Guardará el objeto de entidad jpa de usuario en la DB.
 * 8.- Guardará el objeto de entidad jpa de usuario en la base de datos "to packet controller)
 */