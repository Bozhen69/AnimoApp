package com.bozhen.animoapplication.main.model.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "outvisit_activity")
public class OutVisitActivity  {
    @ColumnInfo(name = "id_outvisit_activity")
    @PrimaryKey(autoGenerate = true)
    private long id;

    private long is_work;

    private String name;

    public OutVisitActivity(long id, long is_work, String name) {
        this.id = id;
        this.is_work = is_work;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIs_work() {
        return is_work;
    }

    public void setIs_work(long is_work) {
        this.is_work = is_work;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return this.name;
    }
}
