package com.bozhen.animoapplication.main.model.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "pharmacyNetworkPreparations")
public class PharmacyNetworkPreparations {
    @ColumnInfo(name = "id_PharmacyNetworkPreparations")
    @PrimaryKey(autoGenerate = true)
    private long id;

    private long id_network;

    private long id_preparation;

    public PharmacyNetworkPreparations(long id, long id_network, long id_preparation) {
        this.id = id;
        this.id_network = id_network;
        this.id_preparation = id_preparation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_network() {
        return id_network;
    }

    public void setId_network(long id_network) {
        this.id_network = id_network;
    }

    public long getId_preparation() {
        return id_preparation;
    }

    public void setId_preparation(long id_preparation) {
        this.id_preparation = id_preparation;
    }
}
