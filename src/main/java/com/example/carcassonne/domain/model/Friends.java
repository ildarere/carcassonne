package com.example.carcassonne.domain.model;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("friends")
public class Friends {
    @Column("first_id")
    private int firstFriend;

    @Column("second_id")
    private int secondFriend;

    public Friends(int firstFriend, int secondFriend) {
        this.firstFriend = firstFriend;
        this.secondFriend = secondFriend;
    }
    public int getFirstFriend() {
        return firstFriend;
    }

    public void setFirstFriend(int firstFriend) {
        this.firstFriend = firstFriend;
    }

    public int getSecondFriend() {
        return secondFriend;
    }

    public void setSecondFriend(int secondFriend) {
        this.secondFriend = secondFriend;
    }
}
