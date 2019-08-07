package com.bozhen.animoapplication.main.model.room;




import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class Users
{
    @PrimaryKey(autoGenerate = true)
    private long id;

    private String date;

    private String id_direction;

    private String avatar;

    private String login;

    private String password;

    private String patronymic;

    private String phone;

    private String surname;

    private Long old_id;

    private Long activation;

    private String first_name;

    private String email;

    public Users(long id, String date, String id_direction, String avatar, String login, String password, String patronymic, String phone, String surname, long old_id, long activation, String first_name, String email) {
        this.id = id;
        this.date = date;
        this.id_direction = id_direction;
        this.avatar = avatar;
        this.login = login;
        this.password = password;
        this.patronymic = patronymic;
        this.phone = phone;
        this.surname = surname;
        this.old_id = old_id;
        this.activation = activation;
        this.first_name = first_name;
        this.email = email;
    }

    @Ignore
    public Users(){ }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId_direction() {
        return id_direction;
    }

    public void setId_direction(String id_direction) {
        this.id_direction = id_direction;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Long getOld_id() {
        return old_id;
    }

    public void setOld_id(Long old_id) {
        this.old_id = old_id;
    }

    public Long getActivation() {
        return activation;
    }

    public void setActivation(Long activation) {
        this.activation = activation;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
