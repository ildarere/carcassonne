package com.example.carcassonne.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Table("chat")
public class СhatMessage implements Serializable {
    private static final long serialVersionUID = 6216344084865363418L;
    @Id
    private int id;
    private String message;
    private int authorId;
    private int roomId;
    private String authorName;

    public СhatMessage(int id, String message, int authorId, int roomId, String authorName) {
        this.id = id;
        this.message = message;
        this.authorId = authorId;
        this.roomId = roomId;
        this.authorName = authorName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "СhatMessage{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", authorId=" + authorId +
                ", roomId=" + roomId +
                ", authorName='" + authorName + '\'' +
                '}';
    }
}
