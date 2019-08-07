package com.bozhen.animoapplication.main.model.room;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;


@Entity(tableName = "object_in_plans", foreignKeys = @ForeignKey(entity = Plans.class,parentColumns = "id",childColumns = "id_plan", onDelete = CASCADE))
public class ObjectInPlans {
    @ColumnInfo(name = "id_object_in_plans")
    @PrimaryKey(autoGenerate = true)
    private long id;

    private long id_object;

    private String type_object;

    private long id_plan;

    private String plan_time_start;

    private String plan_time_end;

    private String plan_location_start;

    private String plan_location_end;

    public ObjectInPlans(long id, long id_object, String type_object, long id_plan, String plan_time_start, String plan_time_end, String plan_location_start, String plan_location_end) {
        this.id = id;
        this.id_object = id_object;
        this.type_object = type_object;
        this.id_plan = id_plan;
        this.plan_time_start = plan_time_start;
        this.plan_time_end = plan_time_end;
        this.plan_location_start = plan_location_start;
        this.plan_location_end = plan_location_end;
    }

    public String getPlan_location_start() {
        return plan_location_start;
    }

    public void setPlan_location_start(String plan_location_start) {
        this.plan_location_start = plan_location_start;
    }

    public String getPlan_location_end() {
        return plan_location_end;
    }

    public void setPlan_location_end(String plan_location_end) {
        this.plan_location_end = plan_location_end;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_object() {
        return id_object;
    }

    public void setId_object(long id_object) {
        this.id_object = id_object;
    }

    public String getType_object() {
        return type_object;
    }

    public void setType_object(String type_object) {
        this.type_object = type_object;
    }

    public long getId_plan() {
        return id_plan;
    }

    public void setId_plan(long id_plan) {
        this.id_plan = id_plan;
    }

    public String getPlan_time_start() {
        return plan_time_start;
    }

    public void setPlan_time_start(String plan_time_start) {
        this.plan_time_start = plan_time_start;
    }

    public String getPlan_time_end() {
        return plan_time_end;
    }

    public void setPlan_time_end(String plan_time_end) {
        this.plan_time_end = plan_time_end;
    }
}
