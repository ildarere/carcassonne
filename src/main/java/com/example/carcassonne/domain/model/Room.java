package com.example.carcassonne.domain.model;

import org.apache.catalina.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Table("rooms")
public class Room  implements Serializable {
    private static final long serialVersionUID = 6216344084865363418L;
    @Id
    private int id;

    @Column("max_size")
    private int maxSize;

    @Column("ready")
    private int isReady;

    @Column("name")
    private String name;

    private int usersInRoom;

    public Room() {
        super();
    }

    public Room(int id, int maxSize, int isReady, String name) {
        this.id = id;
        this.maxSize = maxSize;
        this.isReady = isReady;
        this.name = name;
    }

    public int getUsersInRoom() {
        return usersInRoom;
    }

    public void setUsersInRoom(int usersInRoom) {
        this.usersInRoom = usersInRoom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +

                ", maxSize=" + maxSize +
                ", isReady=" + isReady +
                ", name='" + name + '\'' +
                               '}';
    }
}
