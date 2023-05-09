package com.app.gaz.springbootrestfulwebservices.repository;

import com.app.gaz.springbootrestfulwebservices.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//2
@Repository
public interface UserRepository
        extends JpaRepository<User,
        Long> {
}

/** 2
 *  1.-crearemos un repositorio JpaRepository de datos de SB para esta entidad JPA de usuario,
 *  de modo que obtendrá crud métodos para realizar operaciones de base de datos en este usuario Entity JPA
 *  2.- Extendamos esta interfaz UserRepository desde la interfaz JpaRepository desde la libreria Spring Data Repository
 *  3.- este JpaRepository es una interfaz genérica y tenemos que pasar dos parámetros 1er parámetro como
 *      un tipo de entidad y un segundo parámetro como tipo de clave principal.
 *  4.- Esta interfaz UserRepository obtendrá un método crud para realizar operaciones de base de datos crud para este usuario JPA.
 *  5.- para que No tengamos que proporcionar implementación para esta interfaz UserRepository.
 *  6.- JPA proporcionará una implementación para la interfaz UserRepository e implementará todos los métodos
 *      de esta interfaz JpaRepository.
 *  7.- @Transactional anotación: ignifica que todos los métodos implementados por una clase SimpleJpaRepository son transaccionales de forma predeterminada.
 */
