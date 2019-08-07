package com.bozhen.animoapplication.main.model.room;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "specialty")
public class Specialty {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String name;

    private long id_statistics;

    private long active;

    private long order;

    public Specialty(long id, String name, long id_statistics, long active, long order) {
        this.id = id;
        this.name = name;
        this.id_statistics = id_statistics;
        this.active = active;
        this.order = order;
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

    public long getId_statistics() {
        return id_statistics;
    }

    public void setId_statistics(long id_statistics) {
        this.id_statistics = id_statistics;
    }

    public long getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public long getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @NonNull
    @Override
    public String toString() {
        return this.name;
    }
}

