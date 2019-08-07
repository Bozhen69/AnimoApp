package com.bozhen.animoapplication.main.model.room;



import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "users_regions",
        primaryKeys = { "user_id" , "region_id"},
        foreignKeys = {
                @ForeignKey(entity = Users.class,
                        parentColumns = "id",
                        childColumns = "user_id"
                        ),
                @ForeignKey(entity = Regions.class,
                        parentColumns = "id",
                        childColumns = "region_id")
        })
    public class UsersRegions {
        public final long user_id;
        public final long region_id;
        public UsersRegions(long user_id, long region_id) {
            this.user_id = user_id;
            this.region_id = region_id;
        }
}
