package com.bozhen.animoapplication.main.model.room;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "preparationPharmacyList")
public class PreparationPharmacyList {


    @ColumnInfo(name = "id_preparationPharmacies")
    @PrimaryKey(autoGenerate = true)
    private long id;
    private long id_preparat;
    private long id_form_release;
    private String name;



    public PreparationPharmacyList(long id, long id_preparat, long id_form_release, String name) {
        this.id = id;
        this.id_preparat = id_preparat;
        this.id_form_release = id_form_release;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_preparat() {
        return id_preparat;
    }

    public void setId_preparat(long id_preparat) {
        this.id_preparat = id_preparat;
    }

    public long getId_form_release() {
        return id_form_release;
    }

    public void setId_form_release(long id_form_release) {
        this.id_form_release = id_form_release;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
