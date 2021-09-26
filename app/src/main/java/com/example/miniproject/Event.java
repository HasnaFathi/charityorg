package com.example.miniproject;

public class Event {

    String name, date, location, email;

    Event(){

    }

    public Event(String name, String date, String location, String email) {
        this.name = name;
        this.date = date;
        this.location = location;
        this.email = email;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

}
