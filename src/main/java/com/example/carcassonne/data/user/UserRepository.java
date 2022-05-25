package com.example.carcassonne.data.user;

import com.example.carcassonne.domain.model.User;

import com.example.carcassonne.domain.model.UserData;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    long countByEmail(String email);

    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndEnabledTrue(String email);


    int countById(int id);
}
