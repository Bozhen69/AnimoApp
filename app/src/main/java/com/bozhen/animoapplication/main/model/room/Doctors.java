package com.bozhen.animoapplication.main.model.room;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "doctors")
public class Doctors {
        private String note;

        private String bithday;

        private String city;

        private long id_post;

        private String created;

        private long id_region;

        private long rating;

        private long active;

        private String patronymic;

        private String phone;

        private String surname;

        private long id_hospital;

        private String address_hospital;

        @PrimaryKey(autoGenerate = true)
        private long id;

        private long id_spec;

        private String first_name;

        private long ol;

        private String updated;

        private String email;

        private String hospital_name;

    public Doctors(String note, String bithday, String city, long id_post, String created, long id_region, long rating, long active, String patronymic, String phone, String surname, long id_hospital, String address_hospital, long id, long id_spec, String first_name, long ol, String updated, String email, String hospital_name) {
        this.note = note;
        this.bithday = bithday;
        this.city = city;
        this.id_post = id_post;
        this.created = created;
        this.id_region = id_region;
        this.rating = rating;
        this.active = active;
        this.patronymic = patronymic;
        this.phone = phone;
        this.surname = surname;
        this.id_hospital = id_hospital;
        this.address_hospital = address_hospital;
        this.id = id;
        this.id_spec = id_spec;
        this.first_name = first_name;
        this.ol = ol;
        this.updated = updated;
        this.email = email;
        this.hospital_name = hospital_name;
    }
    @Ignore
    public Doctors(){ }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getBithday() {
        return bithday;
    }

    public void setBithday(String bithday) {
        this.bithday = bithday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getId_post() {
        return id_post;
    }

    public void setId_post(long id_post) {
        this.id_post = id_post;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public long getId_region() {
        return id_region;
    }

    public void setId_region(long id_region) {
        this.id_region = id_region;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public long getActive() {
        return active;
    }

    public void setActive(long active) {
        this.active = active;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public long getId_hospital() {
        return id_hospital;
    }

    public void setId_hospital(long id_hospital) {
        this.id_hospital = id_hospital;
    }

    public String getAddress_hospital() {
        return address_hospital;
    }

    public void setAddress_hospital(String address_hospital) {
        this.address_hospital = address_hospital;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_spec() {
        return id_spec;
    }

    public void setId_spec(long id_spec) {
        this.id_spec = id_spec;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public long getOl() {
        return ol;
    }

    public void setOl(long ol) {
        this.ol = ol;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }
}
