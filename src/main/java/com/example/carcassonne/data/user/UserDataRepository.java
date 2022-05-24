package com.example.carcassonne.data.user;

import com.example.carcassonne.domain.model.UserData;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDataRepository extends CrudRepository<UserData, Long> {



}
