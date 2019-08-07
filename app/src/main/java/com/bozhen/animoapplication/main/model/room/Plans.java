package com.bozhen.animoapplication.main.model.room;



import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "Plans")
public class Plans {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String date;

    private long activity_id;

    private long id_user;

    private String report;

    private String note;

    public Plans(long id, String date, long activity_id, long id_user, String report, String note) {
        this.id = id;
        this.date = date;
        this.activity_id = activity_id;
        this.id_user = id_user;
        this.report = report;
        this.note = note;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(long activity_id) {
        this.activity_id = activity_id;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
