package com.app.gaz.springbootrestfulwebservices.controller;

import com.app.gaz.springbootrestfulwebservices.entity.User;
import com.app.gaz.springbootrestfulwebservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Build create User REST API:
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        // INYECT BODY TO BEAN AND LATER A METHOD JPA AND DB AND RETURN REQUEST
      User savedUser =  userService.createUser(user);//<--***
      // ONLY RETORN SOME RESPONSE TO CLIENT:
      return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
}


/** 5º
 * 1.- para desarrollar los servicios web RESTful usando spring mvc, tenemos que usar @RestController a nivel de clase.
 * 2.- @RestController es una combinación de anotaciónes: @Controller + @ResponseBody
 * 3.- Dentro es este @RestController, se pueden definir el resto de api(endpoints).
 * 4.- Hay que inyectar la dependencia de UserService, creando una variable de instancia UserService con identificador userService.
 * 5.- vamos a usar la inyección de dependencias basada en constructores para inyectar la dependencia.
 *
 * 6.- Solo necesitamos crear un método  de tipo ResponseEntity<User>  createUser(@RequestBody User user) y
 *     necesitamos usar la anotación para hacer que ese método sea un end point.
 * 7.- Spring proporciona la clase ResponseEntity, que podemos usar para crear una respuesta HTTP completa y
 *      Envia esa respuesta de vuelta al cliente
 * 8.-  El nombre de esta clase es createUser, que se le pasa el Parametro de La Entidad User
 * 9.-  Dentro de un método createUser(), llamemos al objeto userService, Tiene un método createUser() y, a continuación,
 *      pasa el objeto user como argumento de método  Y este método createUser() devuelve un objeto User <<--
 * 10.- Damos el nombre del objeto como savedUser.
 * 11.- hemos llamado al método createUser() y este método createUser() guarda internamente este objeto user,
 *      en una base de datos y devuelve el código de estado saveUser y HTTP al cliente. <<---
 * 12.- necesitamos hacer este método como una API de descanso mediante el uso de anotaciones de SB.
 * 13. @PostMapping manejará la solicitud HTTP post request.
 * 14.- @RequestBody anotación.
 * 15.- @RequestBody anotación convertirá el JSON en un objeto Java USER,básicamente el cliente envía al usuario como una solicitud json
 *
 *
 *
 */
