package com.app.gaz.springbootrestfulwebservices.repository;

import com.app.gaz.springbootrestfulwebservices.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //JPQL: Java Persistence Query Language @Query
    @Query("select m from User m where upper(m.email) like upper(:email)")
    List<User> getByEmail(@Param("email") String email);

    /**
     *SELECT
     *     employee_id,
     *     first_name
     * FROM
     *     employees
     * WHERE
     *     UPPER(first_name) = 'BRUCE';
     */
}

