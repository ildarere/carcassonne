package com.example.carcassonne.data.user;

import com.example.carcassonne.domain.model.User;

import com.example.carcassonne.domain.model.UserData;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    long countByEmail(String email);
    @Query("select id from user where email like :email")
    long getIdByEmail(String email);
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndEnabledTrue(String email);
    @Query("update user " +
            " set enabled = 1" +
            " where id = :id ")
    @Modifying
    void EnabledTrue(long id);

    int countById(int id);

    @Query("update user " +
            " set enabled = 0" +
            " where id = :id ")
    @Modifying
    void EnabledFalse(int id);
}
