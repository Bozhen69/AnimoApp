package com.bozhen.animoapplication.main.model.room;

import java.util.Arrays;
import java.util.List;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

public class RegionsWithUsers {
    private String first_name;
    private String surname;
    private String patronymic;
    private String email;
    private String date;
    @TypeConverters(RegionsListConverter.class)
    private List<String> name;

    public RegionsWithUsers(String first_name, String surname, String patronymic, List<String> name,String email, String date) {
        this.first_name = first_name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.name = name;
        this.email = email;
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public List<String> getName() {
        return name;
    }

    public String getNames() {
        return RegionsListConverter.fromRegions(name);
    }

    public void setName(List<String> name) {
        this.name = name;
    }
}
class RegionsListConverter{
    @TypeConverter
    public static String fromRegions(List<String> regions) {
        String out="";
        for(int i=0;i<regions.size();i++){
            out= out + regions.get(i);
            if(i!=regions.size()-1)
                out+=", ";
        }
        return out;
    }

    @TypeConverter
    public static List<String> toRegions(String data) {
        return Arrays.asList(data.split(","));
    }
}