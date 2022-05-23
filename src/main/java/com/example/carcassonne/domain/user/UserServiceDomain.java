package com.example.carcassonne.domain.user;

import com.example.carcassonne.data.user.UserRepository;
import com.example.carcassonne.domain.model.Role;
import com.example.carcassonne.domain.model.User;
import com.example.carcassonne.web.form.user.UserForm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceDomain implements UserService {

    @Autowired
    UserRepository userRepository;
    
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

//	@Override
//	public void update(@Valid UserForm userForm) {
//		User u = new User();
//		BeanUtils.copyProperties(userForm, u, "password");
//		u.setPassword(passEncoder.encode(userForm.getPassword()));
//		u.setRoles(Role.USER.toString());
//
//		userRepository.save(u);
//	}
}