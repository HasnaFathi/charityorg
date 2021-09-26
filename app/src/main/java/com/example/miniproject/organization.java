package com.example.miniproject;

public class organization {
    private String id;
    private String name;
    private String contact;
    private String location;
    private String email;
    private String des;
    private String date;


    organization()
    {

    }

    public organization(String id, String name, String contact, String location, String email, String des, String date) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.location = location;
        this.email = email;
        this.des = des;
        this.date = date;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}

