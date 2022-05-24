package com.example.carcassonne.domain.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table("user_information")
public class UserData implements Persistable<Long> {

    @Id
    private  Long id;

    @Transient
    private  boolean isNew;

    private  String name;
    private int rating ;
    private int gamesCount ;
    private int wins ;



    public UserData(boolean isNew, Long id, String name) {
        this.name = name;
        this.id = id;
        this.isNew = isNew;
        this.rating = 0;
        this.gamesCount = 0;
        this.wins = 0;
    }
    public UserData(Long id, String name, int rating, int gamesCount, int wins) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.gamesCount = gamesCount;
        this.wins = wins;
        isNew= false;
    }


    public UserData() {
        name = null;
        id = null;
        isNew = false;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
