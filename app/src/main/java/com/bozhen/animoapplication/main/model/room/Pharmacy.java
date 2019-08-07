package com.bozhen.animoapplication.main.model.room;


import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "pharmacy")
public class Pharmacy {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String name;

    private long networking;

    private long contract;

    private String surname;

    private String first_name;

    private String patronymic;

    private long id_post;

    private String address;

    private String city;

    private String email;

    private String phone;

    private long id_region;

    private String category;

    private String note;

    private long active;

    private String created;

    private String updated;

    public Pharmacy(long id, String name, long networking, long contract, String surname, String first_name, String patronymic, long id_post, String address, String city, String email, String phone, long id_region, String category, String note, long active, String created, String updated) {
        this.id = id;
        this.name = name;
        this.networking = networking;
        this.contract = contract;
        this.surname = surname;
        this.first_name = first_name;
        this.patronymic = patronymic;
        this.id_post = id_post;
        this.address = address;
        this.city = city;
        this.email = email;
        this.phone = phone;
        this.id_region = id_region;
        this.category = category;
        this.note = note;
        this.active = active;
        this.created = created;
        this.updated = updated;
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

    public long getNetworking() {
        return networking;
    }

    public void setNetworking(long networking) {
        this.networking = networking;
    }

    public long getContract() {
        return contract;
    }

    public void setContract(long contract) {
        this.contract = contract;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public long getId_post() {
        return id_post;
    }

    public void setId_post(long id_post) {
        this.id_post = id_post;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getId_region() {
        return id_region;
    }

    public void setId_region(long id_region) {
        this.id_region = id_region;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getActive() {
        return active;
    }

    public void setActive(long active) {
        this.active = active;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
