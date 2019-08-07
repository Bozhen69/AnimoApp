package com.bozhen.animoapplication.main.model.room;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pharmacyNetwork")
public class PharmacyNetwork {


    @ColumnInfo(name = "id_PharmacyNetwork")
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private int active;
    private long order;


    public PharmacyNetwork(long id, String name, int active, long order) {
        this.id = id;
        this.name = name;
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

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public long getOrder() {
        return order;
    }

    public void setOrder(long order) {
        this.order = order;
    }
}
