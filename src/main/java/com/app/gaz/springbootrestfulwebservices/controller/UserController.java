package com.app.gaz.springbootrestfulwebservices.controller;

import com.app.gaz.springbootrestfulwebservices.dto.UserDto;
import com.app.gaz.springbootrestfulwebservices.entity.User;
import com.app.gaz.springbootrestfulwebservices.exception.ErrorDetails;
import com.app.gaz.springbootrestfulwebservices.exception.ResourceNotFoundException;
import com.app.gaz.springbootrestfulwebservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto savedUser = userService.createUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) {
        UserDto user = userService.getUserById(userId); //<<--**
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/email")
    public List<User> getByEmail(@RequestParam String email) {
        return userService.getByEmail(email);
    }

    @GetMapping("/emails")
    public ResponseEntity<List<User>> getUserByEmails(@RequestParam String email) {
        List<User> users = userService.getByEmail(email);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId,
                                           @RequestBody UserDto  user) {
        user.setId(userId);
        UserDto updateUser = userService.updateUser(user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("User Delete Ok", HttpStatus.OK);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                        WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "USUARIO_NO_ENCONTRADO"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    /**
     * Podemos escribir el controlador de excepciones globales para controlar excepciones específicas y
     * globales en un solo lugar.
     * ejemplo: de como manejar las excepciones específicas con respecto a un controlador: "handleResourceNotFoundException"
     * ej,queremos controlar las excepciones solo relacionadas con este UserController.
     * podemos escribir esa lógica de manejo de errores dentro de este controlador de usuario.
     * A continuación necesitamos pasar dos parámetros a este método:
     * 1.El parámetro Post es el tipo de excepción que queremos controlar.
     * 2.El segundo parámetro es la solicitud web
     *
     * usamos la anotación @ExceptionHandle (del controlador de excepciones) para manejar una excepción específica y
     * devolver la Respuesta de error PERSONALIZDA al cliente.
     */

}


