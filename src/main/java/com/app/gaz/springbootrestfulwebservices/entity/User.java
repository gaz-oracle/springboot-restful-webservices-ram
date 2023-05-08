package com.app.gaz.springbootrestfulwebservices.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;
}
/** 1
 * 1.- Crear una entidad JPA, llamada User.
 * 2.- Definamos las variables de instancia para esta clase User
 * 3.- Vamos a convertir esta clase User en una entidad JPA mediante anotaciones JPA: @Entity<--
 * 4.- @Entity especifica esta clase como una entidad JPA
 * 5.- Class ==> @Entity == Entidad JPA
 */

/** https://docs.oracle.com/javaee/6/tutorial/doc/bnbqa.html
 * Entidades
 *
 * Una entidad es un OBJECTO de dominio de persistencia ligero.
 * Típicamente, una entidad representa un tabla en una base de datos relacional,
 * y cada instancia de entidad corresponde a una fila en esa Tabla.
 *
 *  El artefacto de programación principal de una entidad es la entidad clase, aunque las
 * entidades pueden usar clases auxiliares.
 *
 * El estado persistente de una entidad se representa a través de campos persistentes o propiedades
 * persistentes. Estos campos o propiedades usan anotaciones de mapeo relacional/objeto para mapear
 * el entidades y relaciones de entidad con los datos relacionales en el almacén de datos subyacente
 */