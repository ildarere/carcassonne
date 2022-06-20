package com.example.carcassonne.domain.user;

import com.example.carcassonne.data.user.FriendsRepository;
import com.example.carcassonne.data.user.UserDataRepository;
import com.example.carcassonne.data.user.UserRepository;
import com.example.carcassonne.domain.model.Friends;
import com.example.carcassonne.domain.model.Role;
import com.example.carcassonne.domain.model.User;
import com.example.carcassonne.domain.model.UserData;
import com.example.carcassonne.web.form.user.UserForm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceDomain implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDataRepository userDataRepository;

    @Autowired
    FriendsRepository friendsRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passEncoder;

    @Override
    public List<User> getList() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);

        return users;
    }

    @Override
    public List<Friends> findFriendsById(int id){
       return friendsRepository.findFriendsById(id);
    }

    @Override
    public List<UserData> findFriendsListById(int id) {
        List<Friends> fr =findFriendsById(id);
        List<UserData> users = new ArrayList<>();
        for (Friends i: fr) {
            if(i.getFirstFriend()==id){
               users.add(findDataById((long) i.getSecondFriend())) ;
            }else if(i.getSecondFriend()==id){
                users.add(findDataById((long) i.getFirstFriend())) ;
            }
        }
        return users;
    }

    @Override
    public int countOfFriends(int id) {
        return friendsRepository.countOfFriends(id);
    }

    @Override
    public boolean isUserWithEmailExist(String email) {
        return userRepository.countByEmail(email) != 0 ? true : false;
    }


    @Override
    public Optional<User> findByEmailAndEnabledTrue(String email) {
        return userRepository.findByEmailAndEnabledTrue(email);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    @Override
    public Optional<UserData> findDataByEmail(String email) {
        return userDataRepository.findDataByEmail(email);
    }

    @Override
    public List<UserData> findByNameContaining(String name) {
        name = "%"+name+"%";
        return userDataRepository.findByNameContaining(name);
    }

    @Override
    public UserData findDataById(Long id) {
        return userDataRepository.findDataById(id);
    }

    @Override
    public void update(@Valid UserForm userForm) {
        User u = new User();

        BeanUtils.copyProperties(userForm, u, "password");


        u.setPassword(passEncoder.encode(userForm.getPassword()));
        u.setRoles(Role.USER.toString());

        userRepository.save(u);
        UserData um = new UserData(true, u.getId(), u.getName());
        System.out.println(um.toString());
        userDataRepository.save(um);
    }

    @Override
    public void addFriend(int firstId, int secondId) {
        friendsRepository.addFriend(firstId, secondId);
    }

    @Override
    public boolean isUserWithIdExist(int id) {
        return userRepository.countById(id) != 0 ? true : false;
    }

    @Override
    public long getIdByEmail(String email) {
        return userRepository.getIdByEmail(email);
    }

    @Override
    public void setEnabledTrue(long id) {
        userRepository.EnabledTrue(id);
    }

    @Override
    public void setEnabledFalse(int id) {
        userRepository.EnabledFalse(id);
    }
}