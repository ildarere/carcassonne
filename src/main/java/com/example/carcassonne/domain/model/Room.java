package com.example.carcassonne.domain.model;

import org.apache.catalina.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;

@Table("rooms")
public class Room {
    @Id
    private int id;
    @Transient
    private  boolean isNew;
    @Column("max_size")
    private int maxSize;
    @Column("ready")
    private int isReady;
    @Column("name")
    private String name;

    private List<UserData> userList = new ArrayList<>();

    public Room(int id, boolean isNew, int maxSize, int isReady, String name) {
        this.id = id;
        this.isNew = isNew;
        this.maxSize = maxSize;
        this.isReady = isReady;
        this.name = name;
    }

    public void addUser(UserData user){
        userList.add(user);
    }
    public UserData getUserById(int id){
        return (UserData) userList.stream().filter(userData -> userData.getId()==id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getIsReady() {
        return isReady;
    }

    public void setIsReady(int isReady) {
        this.isReady = isReady;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
