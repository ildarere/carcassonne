package com.example.carcassonne.domain.user;

import com.example.carcassonne.domain.model.Friends;
import com.example.carcassonne.domain.model.User;
import com.example.carcassonne.domain.model.UserData;
import com.example.carcassonne.web.form.user.UserForm;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getList();
    Optional<User> findByEmailAndEnabledTrue(String email);
    Optional<User> findByEmail(String email);
    List<UserData> findByNameContaining(String name);
    UserData findDataById(Long id);
    List<Friends> findFriendsById(int id);
    List<UserData> findFriendsListById(int id);
    int countOfFriends(int id);
    boolean isUserWithEmailExist(String email);
	void update(@Valid UserForm userForm);
}
