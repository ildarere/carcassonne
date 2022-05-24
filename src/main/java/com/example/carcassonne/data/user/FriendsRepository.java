package com.example.carcassonne.data.user;

import com.example.carcassonne.domain.model.Friends;
import com.example.carcassonne.domain.model.UserData;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FriendsRepository extends CrudRepository<Friends, Long> {
    @Query(" select * from friends where friends.first_id = :id or friends.second_id = :id")
    List<Friends> findById(int id);


}
