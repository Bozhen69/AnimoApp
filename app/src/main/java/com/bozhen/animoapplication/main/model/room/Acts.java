package com.bozhen.animoapplication.main.model.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Acts")
public class Acts {

    @ColumnInfo(name = "id_Acts")
    @PrimaryKey(autoGenerate = true)
    private  long id;

    private long id_plan;

    private long id_pharmacy;

    private String note;

    public Acts(long id, long id_plan, long id_pharmacy, String note) {
        this.id = id;
        this.id_plan = id_plan;
        this.id_pharmacy = id_pharmacy;
        this.note = note;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_plan() {
        return id_plan;
    }

    public void setId_plan(long id_plan) {
        this.id_plan = id_plan;
    }

    public long getId_pharmacy() {
        return id_pharmacy;
    }

    public void setId_pharmacy(long id_pharmacy) {
        this.id_pharmacy = id_pharmacy;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
