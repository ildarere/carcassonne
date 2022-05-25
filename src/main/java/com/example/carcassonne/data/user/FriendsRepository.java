package com.example.carcassonne.data.user;

import com.example.carcassonne.domain.model.Friends;
import com.example.carcassonne.domain.model.UserData;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FriendsRepository extends CrudRepository<Friends, Long> {
    @Query("INSERT INTO friends(first_id, second_id) Values(:firstId, :secondId)")
    @Modifying
    void addFriend(int firstId, int secondId);

    @Query(" select * from friends where friends.first_id = :id or friends.second_id = :id")
    List<Friends> findFriendsById(int id);
    @Query(" select COUNT(*) as count from friends where friends.first_id = :id or friends.second_id = :id")
    int countOfFriends(int id);

}
