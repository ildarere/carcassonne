package com.example.carcassonne.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("rooms")
public class Room {
    @Id
    private int roomId;
    @Transient
    private  boolean isNew;
    @Column("max_size")
    private int maxSize;
    @Column("ready")
    private int isReady;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column("name")
    private String name;

    public Room(int roomId, boolean isNew, int maxSize, int isReady, String name) {
        this.roomId = roomId;
        this.isNew = isNew;
        this.maxSize = maxSize;
        this.isReady = isReady;
        this.name = name;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
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
}
