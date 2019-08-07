package com.bozhen.animoapplication.main.model.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "posts")
public class Posts {
    @PrimaryKey(autoGenerate = true)
    private long id;

    private String name;

    private String who;

    public Posts(long id, String name, String who) {
        this.id = id;
        this.name = name;
        this.who = who;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    @NonNull
    @Override
    public String toString() {
        return this.name;
    }
}

