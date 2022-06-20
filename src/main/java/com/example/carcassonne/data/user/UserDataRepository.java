package com.example.carcassonne.data.user;

import com.example.carcassonne.domain.model.User;
import com.example.carcassonne.domain.model.UserData;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserDataRepository extends CrudRepository<UserData, Long> {

    @Query("select * from user_information where user_information.id = :id")
    UserData findDataById(Long id);
    @Query("select * from user_information where name like :name")
    List<UserData> findByNameContaining(String name);
    @Query("select user_information.id, user_information.rating, user_information.games_count, user_information.wins, user_information.name from user " +
            "     left join user_information on user.id = user_information.id " +
            "     where email like :email")
    Optional<UserData> findDataByEmail(String email);
}
